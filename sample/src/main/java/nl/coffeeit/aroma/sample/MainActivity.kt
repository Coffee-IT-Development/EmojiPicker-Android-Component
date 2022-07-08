@file:OptIn(ExperimentalMaterialApi::class)

package nl.coffeeit.aroma.sample

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import nl.coffeeit.aroma.DEFAULT_CORNER_RADIUS
import nl.coffeeit.aroma.DEFAULT_SCRIM_COLOR
import nl.coffeeit.aroma.bottomsheet.Accessory
import nl.coffeeit.aroma.bottomsheet.BottomSheetWithContent
import nl.coffeeit.aroma.emojipicker.presentation.ui.emoji.EmojiBottomSheet
import nl.coffeeit.aroma.sample.demo_components.BottomSheetCard
import nl.coffeeit.aroma.sample.demo_components.EmojiPickerCard

class MainActivity : AppCompatActivity() {
    private var emojiBottomSheetDialogFragment: EmojiBottomSheet? = null
    private var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initiateEmojiPicker()

        setContent {
            ComponentsDemoScreen {
                openEmojiPicker()
            }
        }
    }

    private fun initiateEmojiPicker() {
        emojiBottomSheetDialogFragment = EmojiBottomSheet.newInstance({ emoji ->
            toast?.cancel()
            toast = Toast.makeText(this, "Selected emoji: ${emoji.emoji}", Toast.LENGTH_SHORT)
            toast?.show()
        })
    }

    private fun openEmojiPicker() {
        emojiBottomSheetDialogFragment?.show(supportFragmentManager, EmojiBottomSheet.TAG)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ComponentsDemoScreen(
    actionEmojiPicker: () -> Unit
) {
    var roundedCornerValue by remember { mutableStateOf(DEFAULT_CORNER_RADIUS) }
    var backgroundColor by remember { mutableStateOf(Color(DEFAULT_SCRIM_COLOR)) }
    var width by remember { mutableStateOf(0f) }
    var bottomPadding by remember { mutableStateOf(0f) }
    var accessory by remember { mutableStateOf(Accessory.NONE) }

    BottomSheetWithContent(
        { state, scope ->
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "Aroma Components")
                        }
                    )
                }
            ) {
                Column(
                    Modifier
                        .padding(16.dp)
                ) {

                    Spacer(modifier = Modifier.height(32.dp))

                    BottomSheetCard(scope = scope, state = state,
                        updateRoundedCornerValue = { roundedCornerValue = it },
                        updateColorValue = { backgroundColor = it },
                        updateWidth = { width = it },
                        updateBottomPadding = { bottomPadding = it },
                        updateAccessory = { accessory = it })

                    Spacer(modifier = Modifier.height(32.dp))

                    EmojiPickerCard {
                        actionEmojiPicker()
                    }
                }

                BackHandler(
                    enabled = (state.currentValue != ModalBottomSheetValue.Hidden),
                    onBack = {
                        scope.launch { state.hide() }
                    }
                )
            }
        },
        sheetContent = {
            LazyColumn {
                items(25) {
                    ListItem(
                        text = { Text("Item $it") },
                        icon = {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = "Localized description"
                            )
                        }
                    )
                }
            }
        },
        cornerShape = RoundedCornerShape(roundedCornerValue.dp),
        scrimColor = backgroundColor,
        width = width,
        bottomPadding = bottomPadding,
        accessory = accessory
    )
}


@Composable
@Preview(showBackground = true)
fun ModalBottomSheetHolderPreview() {
    ComponentsDemoScreen {

    }
}
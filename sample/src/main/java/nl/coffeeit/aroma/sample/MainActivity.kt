@file:OptIn(ExperimentalMaterialApi::class)

package nl.coffeeit.aroma.sample

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
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
import nl.coffeeit.aroma.bottomsheet.BottomSheetWithContent
import nl.coffeeit.aroma.sample.demo_components.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComponentsDemoScreen {
                startActivity(Intent(this, ActiveDevelopmentActivity::class.java))
            }
        }
    }
}

@Composable
fun ComponentsDemoScreen(
    actionActiveDev: () -> Unit
) {
    var roundedCornerValue by remember { mutableStateOf(0) }
    var backgroundColor by remember { mutableStateOf(Color(0x60000000)) }

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

                    ActiveDevelopmentCard { actionActiveDev() }

                    Spacer(modifier = Modifier.height(32.dp))

                    BottomSheetCard(scope = scope, state = state, { roundedCornerValue = it }, {
                        backgroundColor = it
                    })

                    Spacer(modifier = Modifier.height(32.dp))

                    EmojiPickerCard()
                }

                BackHandler(
                    enabled = (state.currentValue ==
                            ModalBottomSheetValue.HalfExpanded
                            || state.currentValue ==
                            ModalBottomSheetValue.Expanded),
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
        scrimColor = backgroundColor
    )
}


@Composable
@Preview(showBackground = true)
fun ModalBottomSheetHolderPreview() {
    ComponentsDemoScreen {

    }
}


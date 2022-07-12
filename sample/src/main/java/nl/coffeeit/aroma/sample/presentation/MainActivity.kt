
package nl.coffeeit.aroma.sample.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nl.coffeeit.aroma.emojipicker.presentation.ui.emoji.EmojiBottomSheet
import nl.coffeeit.aroma.sample.presentation.bottom_sheet.BottomSheetActivity
import nl.coffeeit.aroma.sample.presentation.pincode.PincodeActivity

class MainActivity : AppCompatActivity() {
    private var emojiBottomSheetDialogFragment: EmojiBottomSheet? = null
    private var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initiateEmojiPicker()
        setContent {
            ComponentsDemoScreen(
                { openBottomSheetActivity() },
                { openEmojiPicker() },
                { openPincodeActivity() })
        }
    }

    private fun initiateEmojiPicker() {
        emojiBottomSheetDialogFragment = EmojiBottomSheet.newInstance({ emoji ->
            toast?.cancel()
            toast = Toast.makeText(this, "Selected emoji: ${emoji.emoji}", Toast.LENGTH_SHORT)
            toast?.show()
        })
    }

    private fun openBottomSheetActivity() {
        startActivity(Intent(this, BottomSheetActivity::class.java))
    }

    private fun openEmojiPicker() {
        emojiBottomSheetDialogFragment?.show(supportFragmentManager, EmojiBottomSheet.TAG)
    }

    private fun openPincodeActivity() {
        startActivity(Intent(this, PincodeActivity::class.java))
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ComponentsDemoScreen(
    actionBottomSheet: () -> Unit = { },
    actionEmojiPicker: () -> Unit = { },
    actionPincode: () -> Unit = { }
) {
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
            modifier = Modifier
                .padding(16.dp)
        ) {
            Button(modifier = Modifier
                .fillMaxWidth(),
                onClick = {
                    actionBottomSheet.invoke()
                }
            ) {
                Text(text = "Bottom Sheet")
            }

            Button(modifier = Modifier
                .fillMaxWidth(),
                onClick = {
                    actionEmojiPicker.invoke()
                }
            ) {
                Text(text = "Emoji Picker")
            }

            Button(modifier = Modifier
                .fillMaxWidth(),
                onClick = {
                    actionPincode.invoke()
                }
            ) {
                Text(text = "Pincode")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ModalBottomSheetHolderPreview() {
    ComponentsDemoScreen()
}
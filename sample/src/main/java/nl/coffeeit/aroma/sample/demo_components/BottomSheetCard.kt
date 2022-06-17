package nl.coffeeit.aroma.sample.demo_components

import android.graphics.Color.parseColor
import android.text.TextUtils
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
@ExperimentalMaterialApi
fun BottomSheetCard(
    scope: CoroutineScope,
    state: ModalBottomSheetState,
    updateRoundedCornerValue: (Int) -> Unit,
    updateColorValue: (Color) -> Unit,
    updateCloseButtonChecked: (Boolean) -> Unit,
    updateIsRaised: (Boolean) -> Unit
) {
    Card(
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {

            var cornerRadius by remember { mutableStateOf("")}
            var colorValue by remember { mutableStateOf("")}
            var colorValueError by remember { mutableStateOf(false) }
            var hasCloseButton by remember { mutableStateOf(false) }
            var isRaised by remember { mutableStateOf(false) }

            Row(
                modifier = Modifier.wrapContentHeight()
            ) {
                OutlinedTextField(
                    value = cornerRadius,
                    onValueChange = {
                        cornerRadius = it
                        if (it.isNotBlank() && TextUtils.isDigitsOnly(it)) {
                            updateRoundedCornerValue(it.toInt())
                        }
                    },
                    label = { Text("Corner radius") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth(0.40f)
                        .wrapContentHeight()
                )

                Spacer(modifier = Modifier.width(32.dp))

                Column(
                    modifier = Modifier.wrapContentHeight(),
                ) {
                    Text(text = "Close button?")
                    Checkbox(checked = hasCloseButton, onCheckedChange = {
                        hasCloseButton = it
                        updateCloseButtonChecked(it)
                    } )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column(
                    modifier = Modifier.wrapContentHeight()
                ) {
                    Text(text = "Raised?")
                    Checkbox(checked = isRaised, onCheckedChange = {
                        isRaised = it
                        updateIsRaised(it)
                    })
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = colorValue,
                onValueChange = {
                    colorValue = it
                    colorValueError = try {
                        val color = Color(parseColor("#$it"))
                        updateColorValue(color)
                        false
                    } catch (e: IllegalArgumentException) {
                        true
                    }
                },
                label = { Text("Scrim color") },
                isError = colorValueError,
                singleLine = true,
                placeholder = {
                    Text("AARRGGBB")
                },
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth(0.40f)
                    .wrapContentHeight()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(modifier = Modifier
                .fillMaxWidth(),
                onClick = {
                    scope.launch {
                        state.show()
                    }
                }
            ) {
                Text(text = "Bottom Sheet")
            }
        }
    }
}
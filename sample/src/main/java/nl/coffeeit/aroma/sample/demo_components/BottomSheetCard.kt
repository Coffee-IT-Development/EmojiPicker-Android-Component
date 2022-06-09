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
    updateColorValue: (Color) -> Unit
) {
    Card(
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            var cornerRadius by remember { mutableStateOf("0")}
            var colorValue by remember { mutableStateOf("")}
            var colorValueError by remember { mutableStateOf(false) }

            OutlinedTextField(
                value = cornerRadius,
                onValueChange = {
                    cornerRadius = it
                    if (it.isNotBlank() && TextUtils.isDigitsOnly(it)) {
                        updateRoundedCornerValue(it.toInt())
                    }
                },
                label = { Text("Rounded corner radius") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth(0.50f)
                    .wrapContentHeight()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = colorValue,
                onValueChange = {
                    colorValue = it
                    try {
                        val color = Color(parseColor("#$it"))
                        updateColorValue(color)
                        colorValueError = false

                    } catch (e: IllegalArgumentException) {
                        colorValueError = true
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
                    .fillMaxWidth(0.50f)
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
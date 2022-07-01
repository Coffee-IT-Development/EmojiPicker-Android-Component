package nl.coffeeit.aroma.sample.demo_components

import android.graphics.Color.parseColor
import android.text.TextUtils
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import nl.coffeeit.aroma.DEFAULT_CORNER_RADIUS
import nl.coffeeit.aroma.bottomsheet.Accessory

@Composable
@ExperimentalMaterialApi
fun BottomSheetCard(
    scope: CoroutineScope,
    state: ModalBottomSheetState,
    updateRoundedCornerValue: (Int) -> Unit,
    updateColorValue: (Color) -> Unit,
    updateWidth: (Float) -> Unit,
    updateBottomPadding: (Float) -> Unit,
    updateAccessory: (Accessory) -> Unit
) {
    Card(
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {

            var cornerRadius by remember { mutableStateOf(DEFAULT_CORNER_RADIUS.toString()) }
            var colorValue by remember { mutableStateOf("99000000") }
            var colorValueError by remember { mutableStateOf(false) }
            var width by remember { mutableStateOf("0") }
            var bottomPadding by remember { mutableStateOf("0") }

            var dropdownExpanded by remember { mutableStateOf(false) }

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

                OutlinedTextField(
                    value = width,
                    onValueChange = {
                        width = it
                        if (it.isNotBlank() && TextUtils.isDigitsOnly(it)) {
                            updateWidth(it.toFloat())
                        }
                    },
                    label = { Text("Width") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth(0.40f)
                        .wrapContentHeight())
            }


            Spacer(modifier = Modifier.height(16.dp))

            Row {
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

                Spacer(modifier = Modifier.width(32.dp))

                OutlinedTextField(
                    value = bottomPadding,
                    onValueChange = {
                        bottomPadding = it
                        if (it.isNotBlank() && TextUtils.isDigitsOnly(it)) {
                            updateBottomPadding(it.toFloat())
                        }
                    },
                    label = { Text("Bottom padding") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth(0.40f)
                        .wrapContentHeight())
            }

            Spacer(modifier = Modifier.height(16.dp))


            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text ="Accessory")
                Box(
                    Modifier.wrapContentSize()
                ) {
                    IconButton(onClick = { dropdownExpanded = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Dropdown")
                    }
                    DropdownMenu(
                        expanded = dropdownExpanded,
                        onDismissRequest = { dropdownExpanded = false }) {
                        DropdownMenuItem(onClick = {
                            updateAccessory(Accessory.NONE)
                            dropdownExpanded = false
                        }) {
                            Text("None")
                        }
                        Divider()
                        DropdownMenuItem(onClick = {
                            updateAccessory(Accessory.GRABBER)
                            dropdownExpanded = false
                        }) {
                            Text("Grabber")
                        }
                        Divider()
                        DropdownMenuItem(onClick = {
                            updateAccessory(Accessory.CLOSE_BUTTON)
                            dropdownExpanded = false
                        }) {
                            Text("Close button")
                        }
                    }
                }

            }

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
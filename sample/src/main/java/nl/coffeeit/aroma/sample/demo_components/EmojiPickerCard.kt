package nl.coffeeit.aroma.sample.demo_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EmojiPickerCard() {
    Card(
        elevation = 4.dp
    ) {
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
            onClick = {

            }) {
            Text(text = "Emoji Picker")
        }
    }
}
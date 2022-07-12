package nl.coffeeit.aroma.sample.demo_components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.coffeeit.aroma.pincode.ui.theme.PincodeView

@Composable
fun PincodeCard(
    action: () -> Unit
) {
    Card(
        elevation = 4.dp
    ) {
        PincodeView {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Text(text = "Pincode")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}
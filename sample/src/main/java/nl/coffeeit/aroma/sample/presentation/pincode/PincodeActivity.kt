package nl.coffeeit.aroma.sample.presentation.pincode

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nl.coffeeit.aroma.pincode.presentation.PincodeView

class PincodeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PincodeScreen()
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PincodeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Pincode")
                }
            )
        }
    ) {

        Column {
            Column(
                Modifier
                    .background(Color(0xFF332940))
                    .padding(16.dp)
            ) {
                Text(
                    text = "SocialBlox",
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(16.dp))

                PincodeView(
                    6,
                    inputCornerShape = RoundedCornerShape(8.dp),
                    inputColors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF87888A),
                        unfocusedBorderColor = Color(0xFF36343D),
                        backgroundColor = Color(0xFF36343D)
                    ),
                    inputSpacing = 16.dp
                )
            }

            Column(
                Modifier
                    .background(Color(0xFF8058D4))
                    .padding(16.dp)
            ) {
                Text(
                    text = "Babymanager",
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(16.dp))

                PincodeView(
                    6,
                    inputCornerShape = RoundedCornerShape(8.dp),
                    inputColors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        backgroundColor = Color.White,
                    ),
                    inputSpacing = 13.75.dp
                )
            }

            Column(
                Modifier
                    .background(Color(0xFFFF00FF))
                    .padding(16.dp)
            ) {
                Text(
                    text = "Random",
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(16.dp))

                PincodeView(
                    8,
                    inputCornerShape = RoundedCornerShape(0.dp),
                    inputColors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.White,
                        backgroundColor = Color.Cyan,
                    ),
                    inputSpacing = 8.dp
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ModalPincodeScreenPreview() {
    PincodeScreen()
}
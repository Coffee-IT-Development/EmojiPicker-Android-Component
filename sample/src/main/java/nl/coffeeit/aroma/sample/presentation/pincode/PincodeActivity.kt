package nl.coffeeit.aroma.sample.presentation.pincode

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nl.coffeeit.aroma.pincode.presentation.PincodeView

class PincodeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PincodeScreen {
                finish()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PincodeScreen(
    actionBack: () -> Unit = { }
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Pincode")
                }
            )
        }
    ) {

        Column(
            Modifier.verticalScroll(rememberScrollState())
        ) {
            Column(
                Modifier
                    .background(Color(0xFF332940))
                    .padding(vertical = 32.dp)
            ) {
                Text(
                    text = "SocialBlox",
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                PincodeView(
                    lengthOfCode = 6,
                    inputCornerShape = RoundedCornerShape(8.dp),
                    inputColors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF87888A),
                        unfocusedBorderColor = Color(0xFF36343D),
                        backgroundColor = Color(0xFF36343D),
                        textColor = Color.White,
                        cursorColor = Color.Transparent,
                        errorCursorColor = Color.Transparent
                    ),
                    inputSpacing = 16.dp,
                    modifier = Modifier.padding(horizontal = 27.dp),
                    inputTextStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        color = Color.White
                    ),
                    autoFocusFirstInput = true,
                    actionBack = actionBack
                )

                Spacer(modifier = Modifier.height(32.dp))

                PincodeView(
                    lengthOfCode = 6,
                    inputCornerShape = RoundedCornerShape(8.dp),
                    inputColors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF87888A),
                        unfocusedBorderColor = Color(0xFF36343D),
                        backgroundColor = Color(0xFF36343D),
                        errorBorderColor = Color(0xFF36343D),
                        cursorColor = Color.Transparent,
                        errorCursorColor = Color.Transparent
                    ),
                    inputSpacing = 16.dp,
                    modifier = Modifier.padding(horizontal = 27.dp),
                    isError = true,
                    errorText = "An error occurred",
                    inputTextStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        color = Color.White
                    ),
                    inputErrorTextStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        color = Color(0xFFF7694A)
                    ),
                    errorLabelTextStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        color = Color.White
                    ),
                    pincode = "472692",
                    actionBack = actionBack
                )
            }

            Column(
                Modifier
                    .background(Color.White)
                    .padding(vertical = 32.dp)
            ) {
                Text(
                    text = "In-lite",
                    color = Color(0xFF2B3C46),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                PincodeView(
                    lengthOfCode = 6,
                    inputCornerShape = RoundedCornerShape(6.dp),
                    inputColors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFFF5F5F5),
                        unfocusedBorderColor = Color(0xFFF5F5F5),
                        backgroundColor = Color(0xFFF5F5F5),
                        cursorColor = Color.Transparent,
                        errorCursorColor = Color.Transparent
                    ),
                    inputSpacing = 8.dp,
                    modifier = Modifier.padding(horizontal = 30.dp),
                    actionBack = actionBack
                )

                Spacer(modifier = Modifier.height(32.dp))

                PincodeView(
                    lengthOfCode = 6,
                    inputCornerShape = RoundedCornerShape(6.dp),
                    inputColors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFFF5F5F5),
                        unfocusedBorderColor = Color(0xFFF5F5F5),
                        backgroundColor = Color(0xFFF5F5F5),
                        errorLabelColor = Color(0xFFF7694A),
                        errorBorderColor = Color(0xFFF7694A),
                        cursorColor = Color.Transparent,
                        errorCursorColor = Color.Transparent
                    ),
                    inputSpacing = 8.dp,
                    modifier = Modifier.padding(horizontal = 30.dp),
                    isError = true,
                    actionBack = actionBack
                )
            }

            Column(
                Modifier
                    .background(Color(0xFF8058D4))
                    .padding(vertical = 32.dp)
            ) {
                Text(
                    text = "Babymanager",
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                PincodeView(
                    lengthOfCode = 5,
                    inputCornerShape = RoundedCornerShape(8.dp),
                    inputColors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        backgroundColor = Color.White,
                        cursorColor = Color.Transparent,
                        errorCursorColor = Color.Transparent
                    ),
                    inputSpacing = 13.75.dp,
                    modifier = Modifier.padding(horizontal = 40.dp),
                    actionBack = actionBack
                )

                Spacer(modifier = Modifier.height(32.dp))

                PincodeView(
                    lengthOfCode = 5,
                    inputCornerShape = RoundedCornerShape(8.dp),
                    inputColors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        backgroundColor = Color.White,
                        cursorColor = Color.Transparent,
                        errorCursorColor = Color.Transparent
                    ),
                    inputSpacing = 13.75.dp,
                    modifier = Modifier.padding(horizontal = 40.dp),
                    isError = true,
                    actionBack = actionBack
                )
            }

            Column(
                Modifier
                    .background(Color.White)
                    .padding(vertical = 32.dp)
            ) {
                Text(
                    text = "Bagtag",
                    color = Color(0xFF1F325A),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                PincodeView(
                    lengthOfCode = 6,
                    inputCornerShape = RoundedCornerShape(8.dp),
                    inputColors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFFC5CDD9),
                        unfocusedBorderColor = Color(0xFFF5F5F5),
                        backgroundColor = Color(0xFFF5F5F5),
                        cursorColor = Color.Transparent,
                        errorCursorColor = Color.Transparent
                    ),
                    inputSpacing = 8.dp,
                    showDividerAfterInput = 3,
                    dividerColor = Color(0xFF1F325A),
                    modifier = Modifier.padding(horizontal = 24.dp),
                    onlyDigits = false,
                    actionBack = actionBack
                )

                Spacer(modifier = Modifier.height(32.dp))

                PincodeView(
                    lengthOfCode = 6,
                    inputCornerShape = RoundedCornerShape(8.dp),
                    inputColors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFFC5CDD9),
                        unfocusedBorderColor = Color(0xFFF5F5F5),
                        backgroundColor = Color(0xFFF5F5F5),
                        cursorColor = Color.Transparent,
                        errorCursorColor = Color.Transparent
                    ),
                    inputSpacing = 8.dp,
                    showDividerAfterInput = 3,
                    dividerColor = Color(0xFF1F325A),
                    modifier = Modifier.padding(horizontal = 24.dp),
                    isError = true,
                    errorText = "An error occurred",
                    actionBack = actionBack
                )
            }

            Column(
                Modifier
                    .background(Color(0xFFF8FBFF))
                    .padding(vertical = 32.dp)
            ) {
                Text(
                    text = "Workstead",
                    color = Color(0xFF195393),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                PincodeView(
                    lengthOfCode = 5,
                    inputWidth = 40.dp,
                    inputCornerShape = RoundedCornerShape(8.dp),
                    inputColors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFFA3BAD4),
                        unfocusedBorderColor = Color(0xFFA3BAD4),
                        backgroundColor = Color.White,
                        cursorColor = Color.Transparent,
                        errorCursorColor = Color.Transparent
                    ),
                    inputSpacing = 12.dp,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    actionBack = actionBack
                )

                Spacer(modifier = Modifier.height(32.dp))

                PincodeView(
                    lengthOfCode = 5,
                    inputWidth = 40.dp,
                    inputCornerShape = RoundedCornerShape(8.dp),
                    inputColors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFFA3BAD4),
                        unfocusedBorderColor = Color(0xFFA3BAD4),
                        backgroundColor = Color.White,
                        cursorColor = Color.Transparent,
                        errorCursorColor = Color.Transparent
                    ),
                    inputSpacing = 12.dp,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    isError = true,
                    actionBack = actionBack
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
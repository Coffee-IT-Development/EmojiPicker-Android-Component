package nl.coffeeit.aroma.sample.presentation.pincode

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import nl.coffeeit.aroma.sample.R
import nl.coffeeit.aroma.pincode.presentation.PincodeView

class PincodeActivity : ComponentActivity() {

    private var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PincodeScreen(onBack = { finish() }, onPincodeCompleted = { pincode ->
                toast?.cancel()
                val toast = Toast.makeText(this, "Entered pincode: $pincode", Toast.LENGTH_SHORT)
                toast?.show()
            }, onResend = {
                val toast = Toast.makeText(this, "Code resent", Toast.LENGTH_SHORT)
                toast?.show()
            })
        }
    }
}

private val SocialBloxFontFamily = FontFamily(
    Font(R.font.lato_regular, FontWeight.Normal),
    Font(R.font.lato_medium, FontWeight.Medium),
    Font(R.font.lato_semibold, FontWeight.SemiBold),
    Font(R.font.lato_bold, FontWeight.Bold),
    Font(R.font.lato_black, FontWeight.Black)
)

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PincodeScreen(
    onBack: () -> Unit = { },
    onPincodeCompleted: (String?) -> Unit = { },
    onResend: () -> Unit = { }
) {
    val isError = MutableLiveData<Boolean>()
    val pincode = MutableLiveData<String>()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Pincode")
                },
                actions = {
                    IconButton(onClick = {
                        pincode.postValue("472692")
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_pin),
                            contentDescription = "Set pincode"
                        )
                    }

                    IconButton(onClick = {
                        if (isError.value == true) {
                            isError.postValue(false)
                        } else {
                            isError.postValue(true)
                        }

                    }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_error),
                            contentDescription = "Toggle error"
                        )
                    }
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
                        errorBorderColor = Color(0xFF36343D),
                        textColor = Color.White,
                        cursorColor = Color.Transparent,
                        errorCursorColor = Color.Transparent
                    ),
                    inputSpacing = 16.dp,
                    errorText = "An error occurred",
                    modifier = Modifier.padding(horizontal = 27.dp),
                    inputTextStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    ),
                    inputErrorTextStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        color = Color(0xFFF7694A),
                        fontFamily = SocialBloxFontFamily,
                        fontWeight = FontWeight.Bold
                    ),
                    errorLabelTextStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontFamily = SocialBloxFontFamily,
                        fontWeight = FontWeight.Normal
                    ),
                    resendButtonTextStyle = TextStyle(
                        color = Color.White,
                        background = Color(0xFF36343D),
                        fontFamily = SocialBloxFontFamily,
                        fontWeight = FontWeight.Bold
                    ),
                    resendButtonDisabledTextStyle = TextStyle(
                        color = Color.White,
                        background = Color(0xFF757183),
                        fontFamily = SocialBloxFontFamily,
                        fontWeight = FontWeight.Bold
                    ),
                    autoFocusFirstInput = true,
                    pincodeLiveData = pincode,
                    isErrorLiveData = isError,
                    resetPincodeLiveData = { pincode.postValue("") },
                    onBack = onBack,
                    onPincodeCompleted = onPincodeCompleted,
                    enableResendButton = true,
                    onResend = onResend,
                    keyEventInErrorState = { isError.postValue(false) }
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
                        errorLabelColor = Color(0xFFF7694A),
                        errorBorderColor = Color(0xFFF7694A),
                        cursorColor = Color.Transparent,
                        errorCursorColor = Color.Transparent
                    ),
                    inputSpacing = 8.dp,
                    modifier = Modifier.padding(horizontal = 30.dp),
                    pincodeLiveData = pincode,
                    isErrorLiveData = isError,
                    onBack = onBack
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
                    pincodeLiveData = pincode,
                    isErrorLiveData = isError,
                    onBack = onBack
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
                    errorText = "An error occurred",
                    pincodeLiveData = pincode,
                    isErrorLiveData = isError,
                    onBack = onBack
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
                    pincodeLiveData = pincode,
                    isErrorLiveData = isError,
                    enableResendButton = true,
                    triggerResendOnInit = false,
                    onPincodeCompleted = {
                        // Pin code filled in
                    },
                    onResend = {
                        // Resend button clicked
                    }
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
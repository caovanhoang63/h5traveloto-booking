package com.example.h5traveloto_booking.auth.presentation.forgotpasswordstep

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.h5traveloto_booking.auth.presentation.forgotpassword.ForgotPasswordViewModel
import com.example.h5traveloto_booking.auth.presentation.signup.component.SignUpBottom
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton
import com.example.h5traveloto_booking.util.ui_shared_components.TextBoxSingle
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.h5traveloto_booking.theme.BorderStroke
import com.example.h5traveloto_booking.ui_shared_components.PopupRegisterSuccess

@Composable
fun PinForgot(
    navController: NavController,
    navLogin: NavController,
    viewModel: ForgotPasswordViewModel
){
    var isError by rememberSaveable { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 21.dp, end = 21.dp, top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ForgotHeader()
        Spacer(modifier = Modifier.height(62.dp))
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            TextFieldAutoFocusDemo(
                setPin = {
                    viewModel.setPin(it)
                }
            )
            Text(
                text = "Nhập mã PIN đã được gửi đến email của bạn",
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 16.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))

            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.checkPin(
                        onError = {
                            isError = it
                        },
                        navController
                    )
                },
                text = "Tiếp theo"
            )

        }
        if(isError){
            PopupRegisterSuccess(
                onDismissRequest = { isError = false},
                onConfirmation = { navController.popBackStack()
                                    isError = false
                                 },
                dialogTitle = "Mã PIN không đúng",
                dialogText = "Vui lòng yêu cầu gửi lại mã PIN.",
                textButton = "Đóng"
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        SignUpBottom(navController = navLogin)
    }
}

@Composable
fun TextFieldAutoFocusDemo(
    setPin: (pin: String) -> Unit
) {
    // State to hold the text for each TextField
    var text1 by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }
    var text3 by remember { mutableStateOf("") }
    var text4 by remember { mutableStateOf("") }

    // FocusRequester for each TextField
    val focusRequester1 = remember { FocusRequester() }
    val focusRequester2 = remember { FocusRequester() }
    val focusRequester3 = remember { FocusRequester() }
    val focusRequester4 = remember { FocusRequester() }

    val focusManager = LocalFocusManager.current

    Row(
        modifier = Modifier.fillMaxWidth().padding(50.dp,16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        PinInput(
            setText = {
                text1 = it
            },
            { focusRequester2.requestFocus() },
            { focusRequester1.requestFocus() },
            focusRequester1
        )
        Spacer(modifier = Modifier.height(8.dp))
        PinInput(
            setText = {
                text2 = it
            },
            { focusRequester3.requestFocus() },
            { focusRequester1.requestFocus() },
            focusRequester2
        )
        Spacer(modifier = Modifier.height(8.dp))
        PinInput(
            setText = {
                text3 = it
            },
            { focusRequester4.requestFocus() },
            { focusRequester2.requestFocus() },
            focusRequester3
        )
        Spacer(modifier = Modifier.height(8.dp))
        PinInput(
            setText = {
                text4 = it
            },
            enter = {
                Log.d("PIN", "$text1$text2$text3$text4")
                setPin("$text1$text2$text3$text4")
                focusManager.clearFocus()
            },
            backSpace = { focusRequester3.requestFocus() },
            myfocusRequester = focusRequester4
        )
    }
}

@Composable
fun PinInput(
    setText: (text: String) -> Unit,
    enter: () -> Unit,
    backSpace: () -> Unit,
    myfocusRequester: FocusRequester,
){
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
        onValueChange = {
            if (it.length <= 1) {
                if (it.isEmpty() && text.isNotEmpty()) {
                    backSpace()
                }
                text = it
                setText(it)
                if (it.isNotEmpty()) {
                    // Optionally clear focus if it's the last field
                    enter()
                }
            }
        },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .focusRequester(myfocusRequester)
            .width(44.dp),
        maxLines = 1,
    )
}

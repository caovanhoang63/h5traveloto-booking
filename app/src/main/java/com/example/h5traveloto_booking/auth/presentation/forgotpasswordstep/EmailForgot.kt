package com.example.h5traveloto_booking.auth.presentation.forgotpasswordstep

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.auth.presentation.forgotpassword.ForgotPasswordViewModel
import com.example.h5traveloto_booking.auth.presentation.signup.Validate
import com.example.h5traveloto_booking.auth.presentation.signup.ValidationError
import com.example.h5traveloto_booking.auth.presentation.signup.component.SignUpBottom
import com.example.h5traveloto_booking.auth.presentation.signup.isNotEmpty
import com.example.h5traveloto_booking.auth.presentation.signup.isValidEmail
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton
import com.example.h5traveloto_booking.util.ui_shared_components.TextBoxSingle

@Composable
fun EmailForgot(
    navController: NavController,
    navLogin: NavController,
    viewModel: ForgotPasswordViewModel
){
    var email by rememberSaveable { mutableStateOf("") }
    var messageEmailError by rememberSaveable { mutableStateOf("Vui lòng nhập Email!") }
    var isVisibleEmailError by rememberSaveable { mutableStateOf(false) }
    var isFocused by rememberSaveable { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 21.dp, end = 21.dp, top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ForgotHeader()
        Spacer(modifier = Modifier.height(62.dp))
        Column {
            //Email
            TextBoxSingle(
                modifier = Modifier.fillMaxWidth(),
                label = "Email",
                placeholder = "Nhập email của bạn",
                value = email,
                onValueChange = {
                    email = it
                    isVisibleEmailError = false
                    viewModel.setEmail(it)
                    messageEmailError = ValidationError(
                        it, listOf(
                            Validate(::isNotEmpty, "Vui lòng nhập Email!"),
                            Validate(::isValidEmail, "Email không hợp lệ")
                        )
                    )
                }
            )
            Spacer(modifier = Modifier.height(5.dp))
            //Text Error
            if (isVisibleEmailError && messageEmailError.isNotEmpty()) {
                Text(text = messageEmailError, color = Color.Red, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(30.dp))

            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if(messageEmailError.isNotEmpty())
                    {
                        isVisibleEmailError = true
                    } else {
                        Log.d("EmailForgot", "Email: ${viewModel.getEmail()}")
                        viewModel.sentMail()
                        navController.navigate(Screens.PinForgot.name)
                    }
                },
                text = "Tiếp theo"
            )

        }
        Spacer(modifier = Modifier.height(24.dp))
        SignUpBottom(navController = navLogin)
    }
}


@Composable
fun ForgotHeader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.onlylogo),
            contentDescription = "logo",
            modifier = Modifier
                .height(45.dp)
                .width(50.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Quên mật khẩu", fontSize = 30.sp, fontWeight = FontWeight.Bold)
    }
}
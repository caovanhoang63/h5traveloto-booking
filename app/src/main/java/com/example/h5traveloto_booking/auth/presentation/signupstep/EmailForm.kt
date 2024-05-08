package com.example.h5traveloto_booking.auth.presentation.signupstep

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.h5traveloto_booking.auth.presentation.signup.*
import com.example.h5traveloto_booking.auth.presentation.signup.component.SignUpBottom
import com.example.h5traveloto_booking.auth.presentation.signup.component.SignUpHeader
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.ui_shared_components.PopupRegisterSuccess
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton
import com.example.h5traveloto_booking.util.ui_shared_components.TextBox
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.util.ui_shared_components.TextBoxSingle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailForm(
    navController: NavController,
    navLogin: NavController,
    viewModel: SignUpViewModel
){
    var email by rememberSaveable { mutableStateOf("") }
    var messageEmailError by rememberSaveable { mutableStateOf("") }
    var isVisibleEmailError by rememberSaveable { mutableStateOf(false) }
    var isFocused by rememberSaveable { mutableStateOf(false) }


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 21.dp, end = 21.dp, top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SignUpHeader()
        Spacer(modifier = Modifier.height(62.dp))

        Column {
            //Email
            TextBoxSingle(
                modifier = Modifier.fillMaxWidth()
                    .onFocusChanged {
                        if (it.isFocused) {
                            isFocused = true
                        } else {
                            messageEmailError = ValidationError(
                                email, listOf(
                                    Validate(::isNotEmpty, "Vui lòng nhập Email!"),
                                    Validate(::isValidEmail, "Email không hợp lệ")
                                )
                            )
                            if (isFocused) {
                                isVisibleEmailError = true
                            }
                        }
                    },
                label = "Email",
                placeholder = "Enter your email",
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
                    if (messageEmailError.isNotEmpty()) {
                        isVisibleEmailError = true
                    } else {
                        viewModel.checkExisted(
                            Navigate = {
                                navController.navigate(Screens.NameForm.name)
                            },
                            Existed = {
                                messageEmailError = "Email đã tồn tại"
                                isVisibleEmailError = true
                            }
                        )
                    }
                },
                text = "Next"
            )
        }

        Spacer(modifier = Modifier.height(22.dp))
        SignUpBottom(navController = navLogin)
    }
}

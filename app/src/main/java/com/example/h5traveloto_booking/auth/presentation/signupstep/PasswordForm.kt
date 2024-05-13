package com.example.h5traveloto_booking.auth.presentation.signupstep

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.h5traveloto_booking.auth.presentation.signup.*
import com.example.h5traveloto_booking.auth.presentation.signup.component.SignUpBottom
import com.example.h5traveloto_booking.auth.presentation.signup.component.SignUpHeader
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.ui_shared_components.PasswordBox
import com.example.h5traveloto_booking.ui_shared_components.PasswordBoxSingle
import com.example.h5traveloto_booking.ui_shared_components.PopupRegisterSuccess
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordForm(
    navController: NavController,
    navLogin: NavController,
    viewModel: SignUpViewModel
) {
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var isSuccess by rememberSaveable { mutableStateOf(false) }

    var messagePasswordError by rememberSaveable { mutableStateOf("") }
    var isVisiblePasswordError by rememberSaveable { mutableStateOf(false) }
    var isFocusedPassword by rememberSaveable { mutableStateOf(false) }
    var messageConfirmPasswordError by rememberSaveable { mutableStateOf("") }
    var isVisibleConfirmPasswordError by rememberSaveable { mutableStateOf(false) }
    var isFocusedConfirmPassword by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 21.dp, end = 21.dp, top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SignUpHeader()
        Spacer(modifier = Modifier.height(62.dp))
        Column {

            //Password
            PasswordBoxSingle(
                modifier = Modifier.fillMaxWidth()
                    .onFocusChanged {
                        if(it.isFocused){
                            isFocusedPassword = true
                        }
                        else{
                            if(isFocusedPassword){
                                isVisiblePasswordError = true
                            }
                            messagePasswordError = ValidationError(password, listOf(
                                Validate(::isNotEmpty, "Vui lòng nhập trường này!"),
                                Validate(::isValidPassword, "Mật khẩu ít nhất 8 ký tự, bao gồm chữ cái, số và ký tự đặc biệt")
                            ))
                        }
                    },
                label= "Password",
                placeholder = "Enter your password",
                value = password,
                onValueChange = {
                    password = it
                    isVisiblePasswordError = false
                    messagePasswordError = ValidationError(it, listOf(
                        Validate(::isNotEmpty, "Vui lòng nhập trường này!"),
                        Validate(::isValidPassword, "Mật khẩu ít nhất 8 ký tự, bao gồm chữ cái, số và ký tự đặc biệt")
                    ))
                }
            )
            Spacer(modifier = Modifier.height(5.dp))
            //Text Error
            if(isVisiblePasswordError && messagePasswordError.isNotEmpty()){
                Text(text = messagePasswordError, color = Color.Red, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(30.dp))
            //Password Confirm
            PasswordBoxSingle(
                modifier = Modifier.fillMaxWidth()
                    .onFocusChanged {
                        if(it.isFocused){
                            isFocusedConfirmPassword = true
                        }
                        else{
                            if(isFocusedConfirmPassword){
                                isVisibleConfirmPasswordError = true
                            }
                            messageConfirmPasswordError = ValidationError(confirmPassword, listOf(
                                Validate(::isNotEmpty, "Vui lòng nhập trường này!"),
                                Validate({ isValidConfirmPassword(password, it) }, "Mật khẩu xác nhận phải trùng với mật khẩu")
                            ))
                        }
                    },
                label= "Password confirm",
                placeholder = "Enter your password",
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    isVisibleConfirmPasswordError = false
                    viewModel.setPassword(it)
                    messageConfirmPasswordError = ValidationError(it, listOf(
                        Validate(::isNotEmpty, "Vui lòng nhập trường này!"),
                        Validate({ isValidConfirmPassword(password, it) }, "Mật khẩu xác nhận phải trùng với mật khẩu")
                    ))
                }
            )
            Spacer(modifier = Modifier.height(5.dp))
            //Text Error
            if(isVisibleConfirmPasswordError && messageConfirmPasswordError.isNotEmpty()){
                Text(text = messageConfirmPasswordError, color = Color.Red, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(30.dp))
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if(messagePasswordError.isNotEmpty() || messageConfirmPasswordError.isNotEmpty()){
                        if(messagePasswordError.isNotEmpty()){
                            isVisiblePasswordError = true
                        }
                        if(messageConfirmPasswordError.isNotEmpty()){
                            isVisibleConfirmPasswordError = true
                        }
                    }
                    else{
                        viewModel.register(IsSuccess = { isSuccess = it })
                    }
                },
                text = "Sign up"
            )
        }
        if(!isSuccess){
            PopupRegisterSuccess(
                onDismissRequest = { },
                onConfirmation = {
                    navLogin.navigate(Screens.LoginScreen.name)
                 },
                dialogTitle = "Đăng ký thành công",
                dialogText = "Chúc mừng bạn đã tạo tài khoản thành công."
            )
        }

        Spacer(modifier = Modifier.height(22.dp))
        SignUpBottom(navController = navLogin)
    }
}
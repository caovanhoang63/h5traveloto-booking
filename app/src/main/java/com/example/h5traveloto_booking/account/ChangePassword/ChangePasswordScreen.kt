/*
package com.example.h5traveloto_booking.account.ChangePassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.account.personal_information.DeleteAccount
import com.example.h5traveloto_booking.account.personal_information.PersonalData
import com.example.h5traveloto_booking.account.personal_information.UpdateInformation.MyDropdownMenu
import com.example.h5traveloto_booking.theme.ScreenBackGround
import com.example.h5traveloto_booking.ui_shared_components.*
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton
import com.example.h5traveloto_booking.util.ui_shared_components.TextBoxSingle
import kotlin.reflect.jvm.internal.ReflectProperties.Val

@Composable
fun ChangePasswordScreen(navController: NavController,
                        viewModel: ChangePasswordViewModel = hiltViewModel()
)
{
    var currentPassword by rememberSaveable { mutableStateOf("") }
    var newPassword by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    var isSuccess by rememberSaveable { mutableStateOf(false) }
    val state by viewModel.PasswordResponse.collectAsState()
    var messagePasswordError by rememberSaveable { mutableStateOf("") }
    var isVisiblePasswordError by rememberSaveable { mutableStateOf(false) }
    var isFocusedPassword by rememberSaveable { mutableStateOf(false) }
    var messageConfirmPasswordError by rememberSaveable { mutableStateOf("") }
    var isVisibleConfirmPasswordError by rememberSaveable { mutableStateOf(false) }
    var isFocusedConfirmPassword by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBackGround),
        topBar = {
            Row (
                Modifier.padding(16.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                PrimaryIconButton(R.drawable.backarrow48, onClick = {navController.navigateUp()
//navController.popBackStack
},"", modifier = Modifier )
                XSpacer(70)
                BoldText(text = "Change Password",
                    //  fontWeight = FontWeight.Bold,
                    // fontSize = 20.sp)
                )
            }
        },
        content = {
                innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
            ) {
                YSpacer(8)
                PasswordBoxSingle(label = "Current Password",
                    value = currentPassword,
                    onValueChange = {
                        currentPassword = it
                        isVisiblePasswordError = false
                        messagePasswordError = ValidationError(currentPassword, listOf(
                            Validate(::isNotEmpty,"Vui lòng nhập mật khẩu hiện tại"),
                        )
                        )
                                    },
                    modifier = Modifier.padding(vertical = 16.dp, horizontal = 20.dp).fillMaxWidth()
                        .onFocusChanged {
                            if(it.isFocused) {
                                isFocusedPassword = true
                            }
                            else {
                                if (isFocusedPassword) {
                                    isVisiblePasswordError = true
                                }
                                messagePasswordError = ValidationError(currentPassword, listOf(
                                    Validate(::isNotEmpty,"Vui lòng nhập mật khẩu hiện tại"),
                                ))
                            }
                        },
                    placeholder = "")

                if (isVisiblePasswordError && messagePasswordError.isNotEmpty()) {
                    Text(text = messagePasswordError, color = Color.Red, fontSize = 16.sp, modifier = Modifier.padding( horizontal = 20.dp).fillMaxWidth())
                }
                YSpacer(8)
                PasswordBoxSingle(label = "New Password", value = newPassword, onValueChange = { newPassword = it },modifier = Modifier.padding(vertical = 16.dp, horizontal = 20.dp).fillMaxWidth(), placeholder = "")
                YSpacer(8)
                PasswordBoxSingle(label = "Confirm Password", value = confirmPassword, onValueChange = { confirmPassword = it },modifier = Modifier.padding(vertical = 16.dp, horizontal = 20.dp).fillMaxWidth(), placeholder = "")
                YSpacer(32)
                PrimaryButton(modifier = Modifier.fillMaxWidth().padding(vertical = 0.dp, horizontal = 20.dp),
                    text = "Done",
                    onClick = {
                        if(confirmPassword == newPassword) {
                            viewModel.changePassword(currentPassword, confirmPassword)
                        }
                    })

            }
        }
    )
}

data class Validate(
    val check: (String) -> Boolean,
    val errorMessage: String
)

fun ValidationError(value: String, validators: List<Validate>): String{
    for(validator in validators){
        if(!validator.check(value)){
            return validator.errorMessage
        }
    }
    return ""
}


fun isNotEmpty(value: String): Boolean {
    return value.isNotEmpty()
}

fun isValidPassword(value: String): Boolean {
    val passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}\$"
    return value.matches(passwordPattern.toRegex())
}

fun isValidConfirmPassword(password: String, confirmPassword: String): Boolean {
    return password == confirmPassword
}
*/

package com.example.h5traveloto_booking.account.ChangePassword

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.account.personal_information.DeleteAccount
import com.example.h5traveloto_booking.account.personal_information.PersonalData
import com.example.h5traveloto_booking.account.personal_information.UpdateInformation.MyDropdownMenu
import com.example.h5traveloto_booking.theme.ScreenBackGround
import com.example.h5traveloto_booking.ui_shared_components.*
import com.example.h5traveloto_booking.util.Result
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton
import com.example.h5traveloto_booking.util.ui_shared_components.TextBoxSingle
import kotlin.reflect.jvm.internal.ReflectProperties.Val

@Composable
fun ChangePasswordScreen(navController: NavController,
                         viewModel: ChangePasswordViewModel = hiltViewModel()
)
{
    var currentPassword by rememberSaveable { mutableStateOf("") }
    var newPassword by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    var isSuccess by rememberSaveable { mutableStateOf(false) }
    var messagePasswordError by rememberSaveable { mutableStateOf("") }
    var isVisiblePasswordError by rememberSaveable { mutableStateOf(false) }
    var isFocusedPassword by rememberSaveable { mutableStateOf(false) }
    var messageConfirmPasswordError by rememberSaveable { mutableStateOf("") }
    var isVisibleConfirmPasswordError by rememberSaveable { mutableStateOf(false) }
    var isFocusedConfirmPassword by rememberSaveable { mutableStateOf(false) }

    val state = viewModel.PasswordResponse.collectAsState().value
    var test by rememberSaveable { mutableStateOf("") }
    var isTest by rememberSaveable { mutableStateOf(true) }

    var showDialog by remember { mutableStateOf(false) }
    var showDialog2 by remember { mutableStateOf(true) }
    if (showDialog && showDialog2) {
        AlertDialog(
            onDismissRequest = {
                // Đóng hộp thoại khi người dùng chọn bất kỳ nơi nào trên màn hình
                showDialog2 = false
            },
            title = {
                Text(text = "Thành công")
            },
            text = {
                Text(text = "Đổi mật khẩu thành công")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog2 = false
                        navController.navigateUp()
                    }
                ) {
                    Text(text = "OK")
                }
            }
        )
    }
    when (state){
        is Result.Idle ->{
        }
        is Result.Loading->{
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator() // Hiển thị thanh tiến trình tải
            }
        }
        is Result.Success->{
            showDialog=true;
        }
        is Result.Error->{
            test = (state as Result.Error).error
            isTest = true
        }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBackGround),
        topBar = {
            Row (
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                PrimaryIconButton(R.drawable.backarrow48, onClick = {navController.navigateUp()
                },"", modifier = Modifier )
                XSpacer(70)
                BoldText(text = "Change Password",
                    //  fontWeight = FontWeight.Bold,
                    // fontSize = 20.sp)
                )
            }
        },
        content = {
                innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
            ) {
                YSpacer(8)
                PasswordBoxSingle(label = "Current Password",
                    value = currentPassword,
                    onValueChange = {
                        currentPassword = it
                        isVisiblePasswordError = false
                        messagePasswordError = ValidationError(currentPassword, listOf(
                            Validate(::isNotEmpty,"Vui lòng nhập mật khẩu hiện tại"),
                        )
                        )
                    },
                    modifier = Modifier
                        .padding(vertical = 16.dp, horizontal = 20.dp)
                        .fillMaxWidth()
                        .onFocusChanged {
                            if (it.isFocused) {
                                isFocusedPassword = true
                            } else {
                                if (isFocusedPassword) {
                                    isVisiblePasswordError = true
                                }
                                messagePasswordError = ValidationError(
                                    currentPassword, listOf(
                                        Validate(::isNotEmpty, "Vui lòng nhập mật khẩu hiện tại"),
                                    )
                                )
                            }
                        },
                    placeholder = "")

                if (isVisiblePasswordError && messagePasswordError.isNotEmpty()) {
                    Text(text = messagePasswordError, color = Color.Red, fontSize = 16.sp, modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth())
                }
                YSpacer(8)
                PasswordBoxSingle(label = "New Password", value = newPassword, onValueChange = { newPassword = it; isTest=false },modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 20.dp)
                    .fillMaxWidth(), placeholder = "")
                YSpacer(8)
                PasswordBoxSingle(label = "Confirm Password", value = confirmPassword, onValueChange = { confirmPassword = it;isTest=false  },modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 20.dp)
                    .fillMaxWidth(), placeholder = "")
                if(test.isNotEmpty()&&isTest){
                    Text(test, color = Color.Red, fontSize = 16.sp, modifier = Modifier.padding(horizontal = 20.dp))
                }
                YSpacer(32)
                PrimaryButton(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 0.dp, horizontal = 20.dp),
                    text = "Done",
                    onClick = {
                        viewModel.changePassword(currentPassword, confirmPassword)

                    })

            }
        }
    )
}

data class Validate(
    val check: (String) -> Boolean,
    val errorMessage: String
)

fun ValidationError(value: String, validators: List<Validate>): String{
    for(validator in validators){
        if(!validator.check(value)){
            return validator.errorMessage
        }
    }
    return ""
}


fun isNotEmpty(value: String): Boolean {
    return value.isNotEmpty()
}

fun isValidPassword(value: String): Boolean {
    val passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}\$"
    return value.matches(passwordPattern.toRegex())
}

fun isValidConfirmPassword(password: String, confirmPassword: String): Boolean {
    return password == confirmPassword
}

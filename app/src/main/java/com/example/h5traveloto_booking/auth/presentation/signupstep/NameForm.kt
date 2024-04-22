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
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton
import com.example.h5traveloto_booking.util.ui_shared_components.TextBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameForm(
    navController: NavController,
    navLogin: NavController,
    viewModel: SignUpViewModel
){
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }

    var messageFirstNameError by rememberSaveable { mutableStateOf("") }
    var isVisibleFirstNameError by rememberSaveable { mutableStateOf(false) }
    var isFocusedFirstName by rememberSaveable { mutableStateOf(false) }
    var messageLastNameError by rememberSaveable { mutableStateOf("") }
    var isVisibleLastNameError by rememberSaveable { mutableStateOf(false) }
    var isFocusedLastName by rememberSaveable { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 21.dp, end = 21.dp, top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SignUpHeader()
        Spacer(modifier = Modifier.height(62.dp))
        Column {
            //First Name
            TextBox(
                modifier = Modifier.fillMaxWidth()
                    .onFocusChanged {
                        if (it.isFocused) {
                            isFocusedFirstName = true
                        } else {
                            if (isFocusedFirstName) {
                                isVisibleFirstNameError = true
                            }
                            messageFirstNameError = ValidationError(
                                firstName, listOf(
                                    Validate(::isNotEmpty, "Vui lòng nhập trường này!"),
                                    Validate(::isValidName, "Tên chỉ bao gồm chữ cái")
                                )
                            )
                        }
                    },
                label = "First Name",
                placeholder = "Enter your first name",
                value = firstName,
                onValueChange = {
                    firstName = it
                    isVisibleFirstNameError = false
                    viewModel.setFirstName(it)
                    messageFirstNameError = ValidationError(
                        it, listOf(
                            Validate(::isNotEmpty, "Vui lòng nhập trường này!"),
                            Validate(::isValidName, "Tên chỉ bao gồm chữ cái")
                        )
                    )
                }
            )
            Spacer(modifier = Modifier.height(5.dp))
            //Text Error
            if (isVisibleFirstNameError && messageFirstNameError.isNotEmpty()) {
                Text(text = messageFirstNameError, color = Color.Red, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(30.dp))
            //Last Name
            TextBox(
                modifier = Modifier.fillMaxWidth()
                    .onFocusChanged {
                        if (it.isFocused) {
                            isFocusedLastName = true
                        } else {
                            if (isFocusedLastName) {
                                isVisibleLastNameError = true
                            }
                            messageLastNameError = ValidationError(
                                lastName, listOf(
                                    Validate(::isNotEmpty, "Vui lòng nhập trường này!"),
                                    Validate(::isValidName, "Tên chỉ bao gồm chữ cái")
                                )
                            )
                        }
                    },
                label = "Last Name",
                placeholder = "Enter your last name",
                value = lastName,
                onValueChange = {
                    lastName = it
                    isVisibleLastNameError = false
                    viewModel.setLastName(it)
                    messageLastNameError = ValidationError(
                        it, listOf(
                            Validate(::isNotEmpty, "Vui lòng nhập trường này!"),
                            Validate(::isValidName, "Tên chỉ bao gồm chữ cái")
                        )
                    )
                }
            )
            Spacer(modifier = Modifier.height(5.dp))
            //Text Error
            if (isVisibleLastNameError && messageLastNameError.isNotEmpty()) {
                Text(text = messageLastNameError, color = Color.Red, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(30.dp))

            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if(messageFirstNameError.isNotEmpty() || messageLastNameError.isNotEmpty())
                    {
                        if(messageFirstNameError.isNotEmpty()){
                            isVisibleFirstNameError = true
                        }
                        if (messageLastNameError.isNotEmpty()){
                            isVisibleLastNameError = true
                        }
                    } else {
                        navController.navigate(Screens.PasswordForm.name)
                    }
                },
                text = "Next"
            )
        }
        Spacer(modifier = Modifier.height(22.dp))
        SignUpBottom(navController = navLogin)
    }
}
package com.example.h5traveloto_booking.auth.presentation.signup

import android.util.Log
import android.widget.AutoCompleteTextView.Validator
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onFocusedBoundsChanged
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.theme.PrimaryColor
import com.example.h5traveloto_booking.ui_shared_components.PasswordBox
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton
import com.example.h5traveloto_booking.util.ui_shared_components.TextBox

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SignupScreen(navController: NavController,
                viewModel : SignUpViewModel = hiltViewModel()
) {

    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable {mutableStateOf("")}
    var  currentStep by rememberSaveable { mutableStateOf(0) }
    var keyboardController = LocalSoftwareKeyboardController.current
    var isSwiping by rememberSaveable { mutableStateOf(false) }


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 21.dp, end = 21.dp, top = 40.dp)
        .pointerInput(Unit){
            detectHorizontalDragGestures(
                onDragStart = { isSwiping = false },
                onDragEnd = { isSwiping = false },
                onHorizontalDrag = {change, dragAmount ->
                    if (dragAmount > 150 && !isSwiping) {
                        isSwiping = true
                        if (currentStep > 0) {
                            currentStep--
                        }
                    }
                }
            )
        }
    ) {

        Column(
            modifier = Modifier.align(Alignment.CenterHorizontally),
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
            Text(text = "Create Account", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(62.dp))
        Column {

            Spacer(modifier = Modifier.height(30.dp))

            when (currentStep) {
                0 -> {
                    var messageEmailError by rememberSaveable { mutableStateOf("") }
                    var isVisibleEmailError by rememberSaveable { mutableStateOf(false) }
                    var isFocused by rememberSaveable { mutableStateOf(false) }
                    //Email
                    TextBox(
                        modifier = Modifier.fillMaxWidth()
                            .onFocusChanged {
                                if(it.isFocused){
                                    isFocused = true
                                }
                                else{
                                    messageEmailError = ValidationError(email, listOf(
                                        Validate(::isNotEmpty, "Email is required"),
                                        Validate(::isValidEmail, "Invalid email")
                                    ))
                                    if(isFocused){
                                        isVisibleEmailError = true
                                    }
                                }
                            },
                        label= "Email",
                        placeholder = "Enter your email",
                        value = email,
                        onValueChange = {
                            email = it
                            isVisibleEmailError = false
                            messageEmailError = ValidationError(it, listOf(
                                Validate(::isNotEmpty, "Email is required"),
                                Validate(::isValidEmail, "Invalid email")
                            ))
                        }
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    //Text Error
                    if(isVisibleEmailError && messageEmailError.isNotEmpty()){
                        Text(text = messageEmailError, color = Color.Red, fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.height(30.dp))

                    PrimaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            if(messageEmailError.isNotEmpty()){
                                isVisibleEmailError = true
                            }
                            else{
                                currentStep++
                            }
                        },
                        text = "Next"
                    )
                }

                1 -> {
                    var messageFirstNameError by rememberSaveable { mutableStateOf("") }
                    var isVisibleFirstNameError by rememberSaveable { mutableStateOf(false) }
                    var isFocusedFirstName by rememberSaveable { mutableStateOf(false) }
                    var messageLastNameError by rememberSaveable { mutableStateOf("") }
                    var isVisibleLastNameError by rememberSaveable { mutableStateOf(false) }
                    var isFocusedLastName by rememberSaveable { mutableStateOf(false) }
                    //First Name
                    TextBox(
                        modifier = Modifier.fillMaxWidth()
                            .onFocusChanged {
                                if(it.isFocused){
                                    isFocusedFirstName = true
                                }
                                else{
                                    if(isFocusedFirstName){
                                        isVisibleFirstNameError = true
                                    }
                                    messageFirstNameError = ValidationError(firstName, listOf(
                                        Validate(::isNotEmpty, "First name is required"),
                                        Validate(::isValidName, "Invalid first name")
                                    ))
                                }
                            },
                        label= "First Name",
                        placeholder = "Enter your first name",
                        value = firstName,
                        onValueChange = {
                            firstName = it
                            isVisibleFirstNameError = false
                            messageFirstNameError = ValidationError(it, listOf(
                                Validate(::isNotEmpty, "First name is required"),
                                Validate(::isValidName, "Invalid first name")
                            ))
                        }
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    //Text Error
                    if(isVisibleFirstNameError && messageFirstNameError.isNotEmpty()){
                        Text(text = messageFirstNameError, color = Color.Red, fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    //Last Name
                    TextBox(
                        modifier = Modifier.fillMaxWidth()
                            .onFocusChanged {
                                if(it.isFocused){
                                    isFocusedLastName = true
                                }
                                else{
                                    if(isFocusedLastName){
                                        isVisibleLastNameError = true
                                    }
                                    messageLastNameError = ValidationError(lastName, listOf(
                                        Validate(::isNotEmpty, "Last name is required"),
                                        Validate(::isValidName, "Invalid last name")
                                    ))
                                }
                            },
                        label= "Last Name",
                        placeholder = "Enter your last name",
                        value = lastName,
                        onValueChange = {
                            lastName = it
                            isVisibleLastNameError = false
                            messageLastNameError = ValidationError(it, listOf(
                                Validate(::isNotEmpty, "Last name is required"),
                                Validate(::isValidName, "Invalid last name")
                            ))
                        }
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    //Text Error
                    if(isVisibleLastNameError && messageLastNameError.isNotEmpty()){
                        Text(text = messageLastNameError, color = Color.Red, fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.height(30.dp))

                    PrimaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            if(messageFirstNameError.isNotEmpty()){
                                isVisibleFirstNameError = true
                            }
                            else if(messageLastNameError.isNotEmpty()){
                                isVisibleLastNameError = true
                            }
                            else{
                                currentStep++
                            }
                        },
                        text = "Next"
                    )
                }
                2 -> {
                    var messagePasswordError by rememberSaveable { mutableStateOf("") }
                    var isVisiblePasswordError by rememberSaveable { mutableStateOf(false) }
                    var isFocusedPassword by rememberSaveable { mutableStateOf(false) }
                    var messageConfirmPasswordError by rememberSaveable { mutableStateOf("") }
                    var isVisibleConfirmPasswordError by rememberSaveable { mutableStateOf(false) }
                    var isFocusedConfirmPassword by rememberSaveable { mutableStateOf(false) }
                    //Password
                    PasswordBox(
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
                                        Validate(::isNotEmpty, "Password is required"),
                                        Validate(::isValidPassword, "Password must contain at least 8 characters, one letter and one number")
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
                                Validate(::isNotEmpty, "Password is required"),
                                Validate(::isValidPassword, "Password must contain at least 8 characters, one letter and one number")
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
                    PasswordBox(
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
                                        Validate(::isNotEmpty, "Password confirm is required"),
                                        Validate({isValidConfirmPassword(password, it)}, "Password confirm must match password")
                                    ))
                                }
                            },
                        label= "Password confirm",
                        placeholder = "Enter your password",
                        value = confirmPassword,
                        onValueChange = {
                            confirmPassword = it
                            isVisibleConfirmPasswordError = false
                            messageConfirmPasswordError = ValidationError(it, listOf(
                                Validate(::isNotEmpty, "Password confirm is required"),
                                Validate({isValidConfirmPassword(password, it)}, "Password confirm must match password")
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
                            if(messagePasswordError.isNotEmpty()){
                                isVisiblePasswordError = true
                            }
                            else if(messageConfirmPasswordError.isNotEmpty()){
                                isVisibleConfirmPasswordError = true
                            }
                            else{
                                keyboardController?.hide()
                                viewModel.register(email, firstName, lastName, password)
                            }
                        },
                        text = "Sign up"
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(22.dp))
        Column(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Already have an account?", fontSize = 12.sp)
            Text(
                text = "Login",
                fontSize = 14.sp,
                color = PrimaryColor,
                modifier = Modifier.clickable { navController.navigate(Screens.LoginScreen.name)}
            )
        }
    }
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

fun isValidEmail(value: String): Boolean {
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    return value.matches(emailPattern.toRegex())
}

fun isValidPassword(value: String): Boolean {
    val passwordPattern = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$"
    return value.matches(passwordPattern.toRegex())
}

fun isValidConfirmPassword(password: String, confirmPassword: String): Boolean {
    return password == confirmPassword
}

fun isValidName(value: String): Boolean {
    val namePattern = "^[a-zA-Z]*$"
    return value.matches(namePattern.toRegex())
}



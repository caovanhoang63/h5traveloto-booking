package com.example.h5traveloto_booking.auth.presentation.signup

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.h5traveloto_booking.auth.presentation.signupstep.EmailForm
import com.example.h5traveloto_booking.auth.presentation.signupstep.NameForm
import com.example.h5traveloto_booking.auth.presentation.signupstep.PasswordForm
import com.example.h5traveloto_booking.navigate.Screens

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SignUpNavigation(
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val signUpViewModel: SignUpViewModel = viewModel
    val signUpNavController = rememberNavController()

    NavHost(
        navController = signUpNavController,
        startDestination = Screens.EmailForm.name
    )  {
        composable(route = Screens.EmailForm.name) {
            EmailForm(navController = signUpNavController, navLogin = navController, viewModel = signUpViewModel)
        }
        composable(route = Screens.NameForm.name) {
            NameForm(navController = signUpNavController, navLogin = navController, viewModel = signUpViewModel)
        }
        composable(route = Screens.PasswordForm.name) {
            PasswordForm(navController = signUpNavController, navLogin = navController, viewModel = signUpViewModel)
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
    val emailPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"
    return value.matches(emailPattern.toRegex())
}

fun isValidPassword(value: String): Boolean {
    val passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}\$"
    return value.matches(passwordPattern.toRegex())
}

fun isValidConfirmPassword(password: String, confirmPassword: String): Boolean {
    return password == confirmPassword
}

fun isValidName(value: String): Boolean {
    val namePattern = "^[a-zA-Z]*$"
    return value.matches(namePattern.toRegex())
}
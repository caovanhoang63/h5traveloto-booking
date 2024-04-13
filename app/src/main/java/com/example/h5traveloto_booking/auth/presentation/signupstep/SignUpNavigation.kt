package com.example.h5traveloto_booking.auth.presentation.signupstep

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.h5traveloto_booking.auth.presentation.signup.SignUpViewModel
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
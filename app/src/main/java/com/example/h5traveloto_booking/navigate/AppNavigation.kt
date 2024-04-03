package com.example.h5traveloto_booking.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.h5traveloto_booking.auth.presentation.signup.SignUpViewModel
import com.example.h5traveloto_booking.auth.presentation.signup.SignupScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val signUpViewModel : SignUpViewModel



    NavHost (
        navController = navController,
        startDestination = Screens.SignUpScreen.name

    ) {
        composable(route = Screens.SignUpScreen.name) {
            SignupScreen(navController = navController)
        }
    }
}
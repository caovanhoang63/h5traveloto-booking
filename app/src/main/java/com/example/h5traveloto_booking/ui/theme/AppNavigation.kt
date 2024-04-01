package com.example.h5traveloto_booking.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import screens.LoginScreen
import screens.SignupScreen

@Composable
fun AppNavigation() {
    val appNavHost = rememberNavController()

    NavHost(navController = appNavHost, startDestination = "login") {
        composable("login") { LoginScreen(navController = appNavHost) }
        composable("signup") { SignupScreen(navController = appNavHost) }
    }
}
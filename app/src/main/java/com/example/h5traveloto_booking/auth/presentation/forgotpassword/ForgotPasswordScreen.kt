package com.example.h5traveloto_booking.auth.presentation.forgotpassword

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.h5traveloto_booking.auth.presentation.forgotpasswordstep.EmailForgot
import com.example.h5traveloto_booking.auth.presentation.forgotpasswordstep.PasswordForgot
import com.example.h5traveloto_booking.auth.presentation.forgotpasswordstep.PinForgot
import com.example.h5traveloto_booking.navigate.Screens

@Composable
fun ForgotPasswordScreen(
    navController: NavController,
    viewModel: ForgotPasswordViewModel = hiltViewModel()
){
    val forgotNavController = rememberNavController()
    LaunchedEffect(Unit){
        viewModel.setNavController(navController)
    }

    NavHost(
        navController = forgotNavController,
        startDestination = Screens.EmailForgot.name
    ){
        composable(Screens.EmailForgot.name){
            EmailForgot(
                navController = forgotNavController,
                navLogin = navController,
                viewModel = viewModel
            )
        }
        composable(Screens.PasswordForgot.name){
            PasswordForgot(
                navController = forgotNavController,
                navLogin = navController,
                viewModel = viewModel
            )
        }
        composable(Screens.PinForgot.name){
            PinForgot(
                navController = forgotNavController,
                navLogin = navController,
                viewModel = viewModel
            )
        }
    }
}
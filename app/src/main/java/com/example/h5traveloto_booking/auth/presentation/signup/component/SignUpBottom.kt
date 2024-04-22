package com.example.h5traveloto_booking.auth.presentation.signup.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.theme.PrimaryColor

@Composable
fun SignUpBottom(navController: NavController) {
    Column(
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
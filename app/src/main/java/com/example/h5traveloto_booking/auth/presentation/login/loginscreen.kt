// Main Screen
package com.example.h5traveloto_booking.auth.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController,
                viewModel: LoginViewModel =  hiltViewModel(),

) {

    viewModel.setNavController(navController)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 28.dp, end = 28.dp, top = 40.dp),
    ) {
        var email by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }


        // Logo and Welcome Text
        Column(horizontalAlignment = Alignment.Start) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "LoginLogo",
                modifier = Modifier
                    .height(25.dp)
                    .width(200.dp)
            )
            Spacer(modifier = Modifier.height(19.dp))
            Text(text = "Welcome Back!", fontSize = 16.sp, fontWeight = FontWeight.Bold)

        }
        // End of Logo and Welcome Text

        Spacer(modifier = Modifier.height(128.dp))

        // Login Form
        Column {
            Text(text = "Please login to continue", fontSize = 12.sp)
            Spacer(modifier = Modifier.height(30.dp))
            TextBox(
                modifier = Modifier.fillMaxWidth(),
                placeholder = "Enter your email",
                label = "Email",
                value = email,
                onValueChange = { email = it }
            )
            Spacer(modifier = Modifier.height(40.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Password", fontSize = 12.sp)
                Text(
                    text = "Forgot your password",
                    fontSize = 12.sp,
                    modifier = Modifier.clickable { }
                )
            }

            PasswordBox(
                modifier = Modifier.fillMaxWidth(),
                placeholder = "Enter your password",
                label = "Password",
                value = password,
                onValueChange = { password = it  }
            )
            Spacer(modifier = Modifier.height(40.dp))

            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {viewModel.authenticate(email,password)},
                text = "Login"
            )
        }
        // End of Login Form

        Spacer(modifier = Modifier.height(128.dp))

        // Create Account
        Column(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "New to H5Traveloto?", fontSize = 12.sp)
            Text(
                text = "Create Account Here",
                fontSize = 14.sp,
                color = PrimaryColor,
                modifier = Modifier.clickable { navController.navigate(Screens.SignUpScreen.name) {
                } }
            )
        }
        // End of Create Account
    }
}
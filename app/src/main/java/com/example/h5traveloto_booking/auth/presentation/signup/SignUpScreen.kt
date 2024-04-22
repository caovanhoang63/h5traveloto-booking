package com.example.h5traveloto_booking.auth.presentation.signup

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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
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
fun SignUpScreen(navController: NavController,
                 viewModel : SignUpViewModel = hiltViewModel()
) {

    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable {mutableStateOf("")}
    var  currentStep by rememberSaveable { mutableStateOf(0) }
    var keyboardController = LocalSoftwareKeyboardController.current


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 21.dp, end = 21.dp, top = 40.dp),
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
                    TextBox(
                        modifier = Modifier.fillMaxWidth(),
                        label= "Email",
                        placeholder = "Enter your email",
                        value = email,
                        onValueChange = {email = it}
                    )
                    Spacer(modifier = Modifier.height(30.dp))

                    PrimaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { currentStep++ },
                        text = "Next"
                    )
                }
                1 -> {

                    TextBox(
                        modifier = Modifier.fillMaxWidth(),
                        label= "First Name",
                        placeholder = "Enter your first name",
                        value = firstName,
                        onValueChange = {firstName = it}
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    TextBox(
                        modifier = Modifier.fillMaxWidth(),
                        label= "Last Name",
                        placeholder = "Enter your last name",
                        value = lastName,
                        onValueChange = {lastName = it}
                    )
                    Spacer(modifier = Modifier.height(30.dp))

                    PrimaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { currentStep++ },
                        text = "Next"
                    )
                }
                2 -> {
                    PasswordBox(
                        modifier = Modifier.fillMaxWidth(),
                        label= "Password",
                        placeholder = "Enter your password",
                        value = password,
                        onValueChange = {password = it}
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    PasswordBox(
                        modifier = Modifier.fillMaxWidth(),
                        label= "Password confirm",
                        placeholder = "Enter your password",
                        value = confirmPassword,
                        onValueChange = {confirmPassword = it}
                    )

                    Spacer(modifier = Modifier.height(30.dp))
                    PrimaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {viewModel.register(email, firstName, lastName, password)},
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
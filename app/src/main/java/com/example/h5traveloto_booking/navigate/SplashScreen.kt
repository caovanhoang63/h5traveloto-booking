package com.example.h5traveloto_booking.navigate

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.auth.domain.use_case.AuthenticateUseCases
import com.example.h5traveloto_booking.auth.domain.use_case.RenewTokenUseCase
import com.example.h5traveloto_booking.ui_shared_components.YSpacer
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController, setScreenStart: (name: String) -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
){

    LaunchedEffect(key1 = true) {
        //Fetch data from API
        viewModel.getScreenStart(setScreenStart)
        viewModel.logRefreshToken()
        viewModel.refreshToken()
        delay(2000)
        navController.navigate(Screens.AppNavigation.name)
    }
    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.onlylogo),
                contentDescription = "H5Traveloto Booking",
                modifier = Modifier.size(50.dp)
            )
            YSpacer(20)
            Image(
                painter = painterResource(id = R.drawable.logo_text),
                contentDescription = "H5Traveloto Booking",
                modifier = Modifier.size(200.dp,40.dp)
            )
        }
    }
}
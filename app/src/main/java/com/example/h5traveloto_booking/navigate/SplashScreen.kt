package com.example.h5traveloto_booking.navigate

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.auth.domain.use_case.AuthenticateUseCases
import com.example.h5traveloto_booking.auth.domain.use_case.RenewTokenUseCase
import com.example.h5traveloto_booking.ui_shared_components.PopupRegisterSuccess
import com.example.h5traveloto_booking.ui_shared_components.YSpacer
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController, setScreenStart: (name: String) -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
){
    var isRefreshToken by rememberSaveable { mutableStateOf(true) }

    LaunchedEffect(key1 = true) {
        viewModel.refreshToken(navController,setScreenStart, setIsRefreshToken = { isRefreshToken = it })
    }
    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){

        if(!isRefreshToken){
            setScreenStart("LoginScreen")
            PopupRegisterSuccess(
                onDismissRequest = { },
                onConfirmation = {
                    navController.navigate(Screens.AppNavigation.name,builder = {
                    popUpTo(Screens.SplashScreen.name){
                            inclusive = true
                        }
                    })
                },
                dialogTitle = "Phiên đăng nhập hết hạn",
                dialogText = "Vui lòng đăng nhập lại.",
                textButton = "Quay lại trang đăng nhập"
            )
        }
    }
}
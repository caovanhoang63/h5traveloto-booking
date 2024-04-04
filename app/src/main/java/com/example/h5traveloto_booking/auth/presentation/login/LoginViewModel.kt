package com.example.h5traveloto_booking.auth.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.h5traveloto_booking.auth.data.dto.LoginRequestDTO
import com.example.h5traveloto_booking.auth.data.dto.LoginResponseDTO
import com.example.h5traveloto_booking.auth.domain.use_case.AuthenticateUseCases
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.util.Result
import com.example.h5traveloto_booking.util.SharedPrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCases: AuthenticateUseCases,
    private val sharedPrefManager: SharedPrefManager ,
)  : ViewModel(){

    private lateinit var navController: NavController

    private val _authenticateResponse = MutableStateFlow<Result<LoginResponseDTO>>(Result.Idle)

    val authenticateResponse = _authenticateResponse.asStateFlow()

    fun setNavController(navController: NavController) {
        this.navController = navController
    }



    fun authenticate(
        email: String, password :String
    ) = viewModelScope.launch {

        val request = LoginRequestDTO(email,password )
        useCases.authenticateUseCase(request).onStart {

        }
        .catch {e ->
            e.printStackTrace()
            Log.d("api error:", "Error: ${e.message}")

        }.collect{
            res ->
                res.data.access_token?.let {
                    token -> sharedPrefManager.saveToken(token.Token)
                }
                res.data.refresh_token.let{
                    token -> sharedPrefManager.saveRefreshToken(token.Token)
                }
                navController.navigate(Screens.MainScreen.name)
        }
    }

}

package com.example.h5traveloto_booking.navigate

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.h5traveloto_booking.auth.data.dto.RefreshToken
import com.example.h5traveloto_booking.auth.domain.use_case.AuthenticateUseCases
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.utils.today
import com.example.h5traveloto_booking.util.SharedPrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import java.time.LocalDateTime

import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCases: AuthenticateUseCases,
    private val sharedPrefManager: SharedPrefManager
): ViewModel (){
    val token: String? = sharedPrefManager.getToken()
    private val dateTimeCurrent = LocalDateTime.now().toString()+"+07:00"
    private val refreshToken = RefreshToken(token.toString(),dateTimeCurrent, 3600)
    private var isRefreshToken = false

    suspend fun refreshToken(navController: NavController, setScreens: (screen: String) -> Unit, setIsRefreshToken: (isRefreshToken: Boolean) -> Unit){
        useCases.refreshTokenUseCase(refreshToken).onStart {
            Log.d("api refresh start:", "Start")
        }
        .catch {
            Log.d("api refresh error:", "Error: ${it.message}")
            isRefreshToken = false
            setIsRefreshToken(isRefreshToken)
        }
        .collect{ res ->
            Log.d("Success","Refresh Token Success")
            res.data.Token.let {
                token -> sharedPrefManager.saveToken(token)
                Log.d("Token", token)
            }
            isRefreshToken = true
            setIsRefreshToken(isRefreshToken)
            setScreens("MainScreen")
            navController.navigate(Screens.AppNavigation.name,) {
                popUpTo(0){
                    inclusive = true
                }
            }
        }
    }
    fun logRefreshToken() {
        Log.d("Refresh Token", refreshToken.Token)
        Log.d("Refresh Token", refreshToken.created)
        Log.d("Refresh Token", refreshToken.expiry.toString())
    }
    fun getIsisRefreshToken() : Boolean {
        return isRefreshToken
    }
}
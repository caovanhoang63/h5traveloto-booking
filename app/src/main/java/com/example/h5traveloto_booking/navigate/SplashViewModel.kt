package com.example.h5traveloto_booking.navigate

import android.util.Log
import androidx.lifecycle.ViewModel
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
    val dateTimeCurrent = LocalDateTime.now().toString()+"+07:00"
    val refreshToken = RefreshToken(token.toString(),dateTimeCurrent, 3600)

    suspend fun refreshToken() {
        useCases.refreshTokenUseCase(refreshToken).onStart {

        }
        .catch {
            Log.d("api refresh error:", "Error: ${it.message}")
        }
        .collect{
            Log.d("Success","Refresh Token Success")
        }
    }
    fun logRefreshToken() {
        Log.d("Refresh Token", refreshToken.Token)
        Log.d("Refresh Token", refreshToken.created)
        Log.d("Refresh Token", refreshToken.expiry.toString())
    }

    fun getScreenStart(setScreens: (screen: String) -> Unit) {
        if(token!=null){
            setScreens("MainScreen")
        }
        else {
            setScreens("LoginScreen")
        }
    }
}
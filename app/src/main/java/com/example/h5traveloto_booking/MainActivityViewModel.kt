package com.example.h5traveloto_booking

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.h5traveloto_booking.auth.data.dto.LoginRequestDTO
import com.example.h5traveloto_booking.auth.data.dto.RefreshToken
import com.example.h5traveloto_booking.auth.domain.use_case.AuthenticateUseCases
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.util.SharedPrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val useCases: AuthenticateUseCases,
    private val sharedPrefManager: SharedPrefManager
) : ViewModel() {

//    lateinit var  startScreen : String
//    val refreshToken  = sharedPrefManager.getRefreshToken()
//
//     fun checkTokenValidity() {
//
//         viewModelScope.launch {
//
//
//             refreshToken?.let {
//
//                 val token = refreshToken!!
//                 val Token = RefreshToken(Token = token, "",0)
//
//                 useCases.renewTokenUseCase(Token).onStart {
//
//                 }
//                     .catch { e ->
//                         e.printStackTrace()
//                         Log.d("api error:", "Error: ${e.message}")
//                         startScreen = Screens.LoginScreen.name
//
//                     }.collect { res ->
//                         res.data.access_token?.let { token ->
//                             sharedPrefManager.saveToken(token.Token)
//                         }
//
//                        startScreen = Screens.MainScreen.name
//                     }
//             } ?: run {
//                 startScreen = Screens.LoginScreen.name
//
//             }
//         }
//
//     }
}
package com.example.h5traveloto_booking.auth.presentation.forgotpassword

import android.util.Log
import androidx.compose.runtime.Recomposer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.h5traveloto_booking.auth.data.dto.forgotpassword.ChangePasswordRequest
import com.example.h5traveloto_booking.auth.domain.use_case.CheckExistedUseCases
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchHotel.SearchHotelDTO
import com.example.h5traveloto_booking.navigate.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.h5traveloto_booking.util.Result

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val useCases: CheckExistedUseCases
) : ViewModel() {
    private var navController: NavController? = null
    private var email: String = ""
    private var pin: String = ""
    private var password: String = ""



    fun setNavController(navController: NavController){
        this.navController = navController
    }

    fun getNavController(): NavController? {
        return navController
    }

    fun setEmail(email: String){
        this.email = email
    }

    fun getEmail(): String {
        return email
    }

    fun setPin(pin: String){
        this.pin = pin
        Log.d("PIN", pin)
    }

    fun setPassword(password: String){
        this.password = password
    }

    fun getPassWord(): String {
        return password
    }
    fun getPin(): String {
        return pin
    }

    fun sentMail() = viewModelScope.launch {
        useCases.sentMailUseCase(email).onStart {
            Log.d("Forgot Password", "Start sent mail")
        }
            .catch {
                Log.d("Forgot Password", "${it.message}")
            }
            .collect{
                Log.d("Forgot Password", "Sent mail success")
                Log.d("Forgot Password", it.toString())
            }
    }

    fun checkPin(
        onError: (isError: Boolean) -> Unit,
        navController: NavController
    ) = viewModelScope.launch {
        useCases.checkPinUseCase(email, pin).onStart {
            Log.d("Forgot Password", "Start check pin")
        }
            .catch {
                Log.d("Forgot Password", "${it.message}")
                onError(true)
            }
            .collect{
                Log.d("Forgot Password", "Check pin success")
                Log.d("Forgot Password", it.toString())
                navController.navigate(Screens.PasswordForgot.name)
            }
    }

    fun getChangePasswordRequest(): ChangePasswordRequest{
        return ChangePasswordRequest(password = password,pin = pin)
    }

    fun changePassword(
        onSuccess: (isSuccess: Boolean) -> Unit,
        onError: (isError: Boolean) -> Unit
    ) = viewModelScope.launch {
        useCases.changePasswordForgotUseCase(email, getChangePasswordRequest()).onStart {
            Log.d("Forgot Password", "Start change password")
        }
            .catch {
                Log.d("Forgot Password", "${it.message}")
                onError(true)
            }
            .collect{
                Log.d("Forgot Password", "Change password success")
                Log.d("Forgot Password", it.toString())
                onSuccess(true)
            }
    }
}
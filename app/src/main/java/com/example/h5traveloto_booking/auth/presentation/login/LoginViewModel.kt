package com.example.h5traveloto_booking.auth.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.h5traveloto_booking.auth.domain.repository.AuthenticateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel  @Inject constructor(
    private val authenticateRepository: AuthenticateRepository
) :ViewModel() {

        init {

        }

        fun authenticate(email: String, password: String) {
            // authenticateRepository.authenticate()
        }

    val loginUIState = MutableLiveData(LoginUIState())

    fun onEmailChange(newEmail: String) {
        loginUIState.value= loginUIState.value?.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        loginUIState.value = loginUIState.value?.copy(password = newPassword)
    }





}
package com.example.h5traveloto_booking.auth.presentation.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h5traveloto_booking.auth.data.dto.SignUpRequestDTO
import com.example.h5traveloto_booking.auth.data.dto.SignUpResponseDTO
import com.example.h5traveloto_booking.auth.domain.use_case.RegisterUseCases
import com.example.h5traveloto_booking.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val useCases: RegisterUseCases
)  :ViewModel(){
    private val _registerResponse = MutableStateFlow<Result<SignUpResponseDTO>>(Result.Idle)

    val registerResponse = _registerResponse.asStateFlow()




    fun register(
        email: String, firstName: String, lastName: String, password: String
    ) = viewModelScope.launch {
            val registerRequest  = SignUpRequestDTO(firstName, lastName, email, password)
            Log.d("SignUpViewModel", "firstName: $firstName, lastName: $lastName, email: $email, password: $password")

            useCases.registerUseCase(registerRequest).onStart {

            }.catch {e ->
                e.printStackTrace()
                Log.d("api error:", "Error: ${e.message}")

            }.collect{
                res -> res.id
                Log.d("SignUpViewModel", "register: $res")
            }
        }

}

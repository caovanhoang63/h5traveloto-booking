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

    private var email: String = ""
    private var firstName: String = ""
    private var lastName: String = ""
    private var password: String = ""

    public fun setEmail(newEmail: String){
        this.email = newEmail
    }
    public fun setFirstName(newFirstName: String){
        this.firstName = newFirstName
    }
    public fun setLastName(newLastName: String){
        this.lastName = newLastName
    }
    public fun setPassword(newPassword: String){
        this.password = newPassword
    }


    fun register(IsSuccess: (b: Boolean) -> Unit) = viewModelScope.launch {
        val registerRequest  = SignUpRequestDTO(firstName, lastName, email, password)
        Log.d("SignUpViewModel", "firstName: $firstName, lastName: $lastName, email: $email, password: $password")

        useCases.registerUseCase(registerRequest).onStart {

        }.catch {e ->
            e.printStackTrace()
            Log.d("api error:", "Error: ${e.message}")
            IsSuccess(false)
        }.collect{
            res -> res.id
            Log.d("SignUpViewModel", "register: $res")
            IsSuccess(true)
        }
    }

    public  fun getForm(){
        Log.d("SignUpViewModel", "Email: $email, FirstName: $firstName, LastName: $lastName, Password: $password")
    }

}

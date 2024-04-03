package com.example.h5traveloto_booking.auth.presentation.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h5traveloto_booking.auth.data.dto.SignUpRequestDTO
import com.example.h5traveloto_booking.auth.data.remote.api.response
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
    private val _registerResponse = MutableStateFlow<Result<response>>(Result.Idle)


    val registerResponse = _registerResponse.asStateFlow()

   fun LoadDataToObject(email : String, firstName: String, lastName : String, password : String )  {
       Log.d("SignUp","Email: ${email},Firstname: ${firstName},Lastname: ${lastName},Password: ${password}")
       val data = SignUpRequestDTO(firstName, lastName, email, password)
       Log.d("SignUp", data.email.toString())

       registerRequest = data


       register(registerRequest)



    }

    // Day ne
    // java.lang.IllegalStateException: Given component holder class com.example.h5traveloto_booking.MainActivity does not implement interface dagger.hilt.internal.GeneratedComponent or interface dagger.hilt.internal.GeneratedComponentManager
    var  registerRequest : SignUpRequestDTO = SignUpRequestDTO("","","","")


    fun register(
        registerRequest : SignUpRequestDTO
    ) = viewModelScope.launch {

        useCases.registerUseCase(registerRequest).onStart {

        }.catch {
                e ->
            e.printStackTrace()
            Log.d("api error:", "Error: ${e.message}")

        }.collect{
            res -> res.id
        }
    }





}

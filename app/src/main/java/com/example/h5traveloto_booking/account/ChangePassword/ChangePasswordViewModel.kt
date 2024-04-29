package com.example.h5traveloto_booking.account.ChangePassword

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h5traveloto_booking.account.PersonalInformationViewModel
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ChangePasswordDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ChangePasswordResponse
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ProfileDTO
import com.example.h5traveloto_booking.main.presentation.domain.usecases.AccountUseCases
import com.example.h5traveloto_booking.main.presentation.domain.usecases.PasswordUseCases
import com.example.h5traveloto_booking.util.ErrorResponse
import com.example.h5traveloto_booking.util.Result
import com.example.h5traveloto_booking.util.SharedPrefManager
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject


@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val useCases : PasswordUseCases,
    private val sharedPrefManager: SharedPrefManager,
) : ViewModel() {
    private val _changePasswordResponse = MutableStateFlow<Result<ChangePasswordResponse>>(Result.Idle)
    val PasswordResponse = _changePasswordResponse.asStateFlow()

    fun changePassword(
        oldpassword: String,
        password: String,
    ) = viewModelScope.launch {
        val token = sharedPrefManager.getToken()
        Log.d("ChangePassword ViewModel", "Get token")
        Log.d("ChangePassword ViewModel Token", token.toString())
        val bearerToken = "Bearer $token"
        val passwordDTO : ChangePasswordDTO = ChangePasswordDTO(oldpassword,password)
        useCases.changePasswordUseCases(bearerToken,passwordDTO).onStart {
            _changePasswordResponse.value = Result.Loading
            Log.d("ChangePassword ViewModel", "Loading")
        }.catch {
            Log.d("ChangePassword ViewModel", "catch")
            Log.d("ChangePassword ViewModel E", it.message.toString() )
            var error : ErrorResponse
            _changePasswordResponse.value = Result.Error(it)
            if (it is HttpException) {
                Log.d("ChangePassword test", "sadasads")
                val httpException = it
                val errorBody = it.response()?.errorBody()?.string()
                val moshi = Moshi.Builder().build()
                val adapter = moshi.adapter(ErrorResponse::class.java)
                val errorResponse = adapter.fromJson(errorBody)
                Log.d("ChangePassword ViewModel", "${errorResponse?.message}")
                _changePasswordResponse.value = Result.Error(it)
            }

        }.collect {
            Log.d("ChangePassword ViewModel","Ok")
            Log.d("ChangePassword ViewModel",it.data.toString())
//            Log.d("Success",it.paging.total.toString())
            _changePasswordResponse.value = Result.Success(it)
        }
    }
}
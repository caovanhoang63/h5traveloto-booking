package com.example.h5traveloto_booking.account.personal_information.UpdateInformation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.*
import com.example.h5traveloto_booking.main.presentation.domain.usecases.AccountUseCases
import com.example.h5traveloto_booking.main.presentation.domain.usecases.PasswordUseCases
import com.example.h5traveloto_booking.util.ErrorResponse
import com.example.h5traveloto_booking.util.Result
import com.example.h5traveloto_booking.util.SharedPrefManager
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class UpdateInformationViewModel @Inject constructor(
    private val useCases : AccountUseCases,
    private val sharedPrefManager: SharedPrefManager,
) : ViewModel() {
    private val _updateProfileResponse = MutableStateFlow<Result<UpdateProfileResponse>>(Result.Idle)
    private val _getProfileResponse = MutableStateFlow<Result<ProfileDTO>>(Result.Idle)
    val UpdateProfileResponse = _updateProfileResponse.asStateFlow()
    val GetProfileResponse = _getProfileResponse.asStateFlow()

    fun getProfile() = viewModelScope.launch {
        val token = sharedPrefManager.getToken()
        Log.d("UpdateProfile ViewModel", "Get token")
        Log.d("UpdateProfile ViewModel Token", token.toString())
        val bearerToken = "Bearer $token"
        useCases.getProfileUseCase(bearerToken).onStart {
            _getProfileResponse.value = Result.Loading
            Log.d("UpdateProfile ViewModel", "Loading")

        }.catch {
            Log.d("PersonalInformation ViewModel", "catch")
            Log.d("PersonalInformation ViewModel E", it.message.toString() )
            _getProfileResponse.value = Result.Error(it.message.toString())
        }.collect{
            Log.d("Success","Ok")
            Log.d("Success",it.data.email)
//            Log.d("Success",it.paging.total.toString())
            _getProfileResponse.value = Result.Success(it)
        }
    }

    fun updateProfile(
        firstName: String,
        lastName: String,
        phone:String,
    ) = viewModelScope.launch {
        val token = sharedPrefManager.getToken()
        Log.d("UpdateProfile ViewModel", "Get token")
        Log.d("UpdateProfile ViewModel Token", token.toString())
        val bearerToken = "Bearer $token"
        val updateProfileDTO  = UpdateProfileDTO(firstName,lastName,phone)
        useCases.updateProfileUseCase(bearerToken,updateProfileDTO).onStart {
            _updateProfileResponse.value = Result.Loading
            Log.d("UpdateProfile ViewModel", "Loading")
        }.catch {
            if(it is HttpException){
                Log.d("UpdateProfile ViewModel", "catch")
                //Log.d("ChangePassword ViewModel E", it.message.toString())
                Log.d("UpdateProfile ViewModel", "hehe")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("UpdateProfile ViewModel Error", errorResponse.message)
                _updateProfileResponse.value = Result.Error(errorResponse.message)
            }
            else if (it is Exception) {
                Log.d("UpdateProfile ViewModel", it.javaClass.name)
            }

        }.collect {
            Log.d("ChangePassword ViewModel","Ok")
            Log.d("ChangePassword ViewModel",it.data.toString())
//            Log.d("Success",it.paging.total.toString())
            _updateProfileResponse.value = Result.Success(it)
        }
    }
}
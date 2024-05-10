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
        birthDateOfBirth:String?,
        gender:String,
        avatar: Avatar?,
    ) = viewModelScope.launch {
        val token = sharedPrefManager.getToken()
        Log.d("UpdateProfile ViewModel", "Get token")
        Log.d("UpdateProfile ViewModel Token", token.toString())
        val bearerToken = "Bearer $token"
        var date: String?
        var updateProfileDTO:UpdateProfileDTO

        if(birthDateOfBirth!=null){
            date = birthDateOfBirth
            Log.d("UpdateProfile ViewModel", "Get birthDateOfBirth")
            Log.d("UpdateProfile ViewModel Date", date.toString())
            var arr=date.split("")
            date = arr[5]+arr[6]+arr[7]+arr[8]+"-"+arr[3]+arr[4]+"-"+arr[1]+arr[2]
            Log.d("UpdateProfile ViewModel Date", date.toString())
           updateProfileDTO = UpdateProfileDTO(firstName=firstName,lastName=lastName,phone=phone, dateOfBirth = date,gender=gender, avatar = avatar)
        } else updateProfileDTO= UpdateProfileDTO(firstName=firstName,lastName=lastName,phone=phone, dateOfBirth = birthDateOfBirth,gender=gender, avatar = avatar)
        Log.d("UpdateProfile ViewModel", birthDateOfBirth.toString())
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
                Log.d("UpdateProfile ViewModel Error", errorResponse.log)
                _updateProfileResponse.value = Result.Error(errorResponse.message)
            }
            else if (it is Exception) {
                Log.d("UpdateProfile ViewModel", it.javaClass.name)
            }

        }.collect {
            Log.d("UpdateProfile ViewModel","Ok")
            Log.d("UpdateProfile ViewModel",it.data.toString())
//            Log.d("Success",it.paging.total.toString())
            _updateProfileResponse.value = Result.Success(it)
        }
    }
}
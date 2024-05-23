package com.example.h5traveloto_booking.account

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.*
import com.example.h5traveloto_booking.main.presentation.domain.usecases.AccountUseCases
import com.example.h5traveloto_booking.main.presentation.domain.usecases.UpdateProfileUseCase
import com.example.h5traveloto_booking.main.presentation.domain.usecases.UploadUseCases
import com.example.h5traveloto_booking.share.UserShare
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
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File
import javax.inject.Inject

@HiltViewModel
class PersonalInformationViewModel @Inject constructor(
    private val useCases : AccountUseCases,
    private val sharedPrefManager: SharedPrefManager,
    private val uploadUseCase: UploadUseCases,
    private val updateUseCase: AccountUseCases
) : ViewModel() {
    private val _profileDataResponse = MutableStateFlow<Result<ProfileDTO>>(Result.Idle)
    val ProfileDataResponse = _profileDataResponse.asStateFlow()
    private val _uploadFileResponse = MutableStateFlow<Result<AvatarDTO>>(Result.Idle)
    val UploadFileResponse = _uploadFileResponse.asStateFlow()
    fun getProfile(
    ) = viewModelScope.launch {
        val token = sharedPrefManager.getToken()
        Log.d("PersonalInformation ViewModel", "Get token")
        Log.d("PersonalInformation ViewModel Token", token.toString())
        val bearerToken = "Bearer $token"
        useCases.getProfileUseCase(bearerToken).onStart {
            _profileDataResponse.value = Result.Loading
            Log.d("PersonalInformation ViewModel", "Loading")

        }.catch {
            if(it is HttpException){
                Log.d("PersonalInformation ViewModel", "catch")
                //Log.d("PersonalInformation ViewModel E", it.message.toString())
                Log.d("PersonalInformation ViewModel", "hehe")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("PersonalInformation ViewModel Error", errorResponse.message)
                _profileDataResponse.value = Result.Error(errorResponse.message)
            }
            else if (it is Exception) {
                Log.d("ChangePassword ViewModel", it.javaClass.name)
            }
        }.collect{
            Log.d("Success","Ok")
            Log.d("Success",it.data.email)
//            Log.d("Success",it.paging.total.toString())
            _profileDataResponse.value = Result.Success(it)
            UserShare.User.id = it.data.id
        }
    }

    fun uploadProfile(file: File, folder: String)= viewModelScope.launch {
        val token = sharedPrefManager.getToken()
        Log.d("PersonalInformation ViewModel", "Get token")
        Log.d("PersonalInformation ViewModel Token", token.toString())
        val bearerToken = "Bearer $token"
        uploadUseCase.uploadFileUseCase(bearerToken,file,folder).onStart {
            _uploadFileResponse.value = Result.Loading
            Log.d("PersonalInformation ViewModel", "Loading")
            Log.d("PersonalInformation ViewModel","uploadfile")
        }.catch {
            if(it is HttpException){
                Log.d("PersonalInformation ViewModel", "catch")
                //Log.d("PersonalInformation ViewModel E", it.message.toString())
                Log.d("PersonalInformation ViewModel", "hehe")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("PersonalInformation ViewModel Error", errorResponse.message)
                _uploadFileResponse.value = Result.Error(errorResponse.message)
            }
            else if (it is Exception) {
                Log.d("PersonalInformation ViewModel", it.javaClass.name)
            }
        }.collect{
            Log.d("PersonalInformation","Ok")
            Log.d("PersonalInformation",it.avatar.url.toString())
            Log.d("PersonalInformation",it.avatar.toString())
           // Log.d("PersonalInformation",it.toString())
//            Log.d("Success",it.paging.total.toString())
            _uploadFileResponse.value = Result.Success(it)
            updateProfile(it.avatar)
        }
    }

    private val _updateProfileResponse = MutableStateFlow<Result<UpdateProfileResponse>>(Result.Idle)
    val UpdateProfileResponse = _uploadFileResponse.asStateFlow()
    fun updateProfile(
        avatar: Avatar?,
    ) = viewModelScope.launch {
        val token = sharedPrefManager.getToken()
        Log.d("PersonalInformation ViewModel", "Get token")
        Log.d("PersonalInformation ViewModel Token", token.toString())
        val bearerToken = "Bearer $token"
        var updateProfileDTO: UpdateProfileDTO
        updateProfileDTO= UpdateProfileDTO(avatar = avatar)
        updateUseCase.updateProfileUseCase(bearerToken,updateProfileDTO).onStart {
            _updateProfileResponse.value = Result.Loading
            Log.d("PersonalInformation ViewModel", "Loading")
        }.catch {
            if(it is HttpException){
                Log.d("PersonalInformation ViewModel", "catch")
                //Log.d("ChangePassword ViewModel E", it.message.toString())
                Log.d("PersonalInformation ViewModel", "hehe")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("PersonalInformation ViewModel Error htpp", errorResponse.message)
                Log.d("PersonalInformation ViewModel Error htpp", errorResponse.log)
                _updateProfileResponse.value = Result.Error(errorResponse.message)
            }
            else if (it is Exception) {
                Log.d("PersonalInformation ViewModel Error", it.javaClass.name)
            }

        }.collect {
            Log.d("PersonalInformation ViewModel","Ok")
            Log.d("PersonalInformation ViewModel",it.data.toString())
//            Log.d("Success",it.paging.total.toString())
            _updateProfileResponse.value = Result.Success(it)
        }
    }

    private val _uploadImageResponse = MutableStateFlow<Result<AvatarDTO>>(Result.Idle)
    val UploadImageResponse = _uploadImageResponse.asStateFlow()
    fun uploadImage(file: File, folder: String)= viewModelScope.launch {
        val token = sharedPrefManager.getToken()
        Log.d("PersonalInformation ViewModel", "Get token")
        Log.d("PersonalInformation ViewModel Token", token.toString())
        val bearerToken = "Bearer $token"
        uploadUseCase.uploadFileUseCase(bearerToken,file,folder).onStart {
            _uploadImageResponse.value = Result.Loading
            Log.d("PersonalInformation ViewModel", "Loading")
            Log.d("PersonalInformation ViewModel","uploadfile")
        }.catch {
            if(it is HttpException){
                Log.d("AddImage ViewModel", "catch")
                //Log.d("PersonalInformation ViewModel E", it.message.toString())
                Log.d("AddImage ViewModel", "hehe")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("AddImage ViewModel Error", errorResponse.message)
                _uploadImageResponse.value = Result.Error(errorResponse.message)
            }
            else if (it is Exception) {
                _uploadImageResponse.value = Result.Error("errorResponse.message")
                Log.d("AddImage ViewModel", it.javaClass.name)
            }
        }.collect{
            Log.d("AddImage","Ok")
            Log.d("AddImage",it.avatar.url.toString())
            Log.d("AddImage",it.avatar.toString())
            // Log.d("PersonalInformation",it.toString())
//            Log.d("Success",it.paging.total.toString())
            _uploadImageResponse.value = Result.Success(it)
        }
    }
}

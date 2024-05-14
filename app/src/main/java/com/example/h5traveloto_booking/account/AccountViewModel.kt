package com.example.h5traveloto_booking.account

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ProfileDTO
import com.example.h5traveloto_booking.main.presentation.domain.usecases.AccountUseCases
import com.example.h5traveloto_booking.navigate.Screens
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
class AccountViewModel @Inject constructor(
    private val useCases : AccountUseCases,
    private val sharedPrefManager: SharedPrefManager
) : ViewModel() {
    private val _profileDataResponse = MutableStateFlow<Result<ProfileDTO>>(Result.Idle)
    val ProfileDataResponse = _profileDataResponse.asStateFlow()

    fun getProfile(

    ) = viewModelScope.launch {
        val token = sharedPrefManager.getToken()
        Log.d("Account ViewModel", "Get token")
        Log.d("Account ViewModel Token", token.toString())
        val bearerToken = "Bearer $token"
        useCases.getProfileUseCase(bearerToken).onStart {
            _profileDataResponse.value = Result.Loading
            Log.d("Account ViewModel", "Loading")

        }.catch {
            if(it is HttpException){
                Log.d("Account ViewModel", "catch")
                //Log.d("ChangePassword ViewModel E", it.message.toString())
                Log.d("Account ViewModel", "hehe")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("Account ViewModel Error", errorResponse.message)
                _profileDataResponse.value = Result.Error(errorResponse.message)
            }
            else if (it is Exception) {
                Log.d("ChangePassword ViewModel", it.javaClass.name)
            }
        }.collect{
            Log.d("Account ViewModel","Ok")
            Log.d("Success",it.data.avatar?.url.toString())
//            Log.d("Success",it.paging.total.toString())
            _profileDataResponse.value = Result.Success(it)
        }
    }


    fun signOut(navController: NavController) {
        navController.navigate(Screens.LoginScreen.name)

    }
}
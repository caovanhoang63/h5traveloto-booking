package com.example.h5traveloto_booking.main.presentation.favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ProfileDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.CollectionDTO
import com.example.h5traveloto_booking.main.presentation.domain.usecases.CollectionUseCase
import com.example.h5traveloto_booking.main.presentation.domain.usecases.FavoriteUseCases
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
import retrofit2.HttpException
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel@Inject constructor(
    private val usecases : FavoriteUseCases,
    private val sharedPrefManager: SharedPrefManager
): ViewModel() {
    private val collectionDataResponse = MutableStateFlow<Result<CollectionDTO>>(Result.Idle)
    val CollectionDataResponse = collectionDataResponse.asStateFlow()

    fun getCollectionData() = viewModelScope.launch {

        usecases.getCollectionUseCase("\"${UserShare.User.id}\"").onStart {
            collectionDataResponse.value = Result.Loading
        }.catch {
            if(it is HttpException){
                Log.d("Favorite ViewModel", "catch")
                //Log.d("ChangePassword ViewModel E", it.message.toString())
                Log.d("Favorite ViewModel", "hehe")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("Favorite ViewModel Error", errorResponse.message)
                Log.d("Favorite ViewModel Error", errorResponse.log)
                collectionDataResponse.value = Result.Error(errorResponse.message)
            }
            else if (it is Exception) {
                Log.d("Favorite ViewModel", it.javaClass.name)
            }
        }.collect{
            Log.d("Favorite ViewModel","Ok")
            Log.d("Success2",it.data.size.toString())
            Log.d("Success2",it.filter.userId)
            collectionDataResponse.value = Result.Success(it)
        }
    }

}
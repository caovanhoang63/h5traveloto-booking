package com.example.h5traveloto_booking.main.presentation.favorite.AddHotelInCollection

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.AllSavedHotelsDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.Response
import com.example.h5traveloto_booking.main.presentation.domain.usecases.FavoriteUseCases
import com.example.h5traveloto_booking.util.ErrorResponse
import com.example.h5traveloto_booking.util.Result
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
class AddHotelInCollectionViewModel @Inject constructor(
    private val useCases: FavoriteUseCases
) : ViewModel() {
    private val addHotelsDataResponse = MutableStateFlow<Result<Response>>(Result.Idle)
    val AddHotelsDataResponse = addHotelsDataResponse.asStateFlow()

    fun addHotelInCollection(collectionId: String,hotelId: String) = viewModelScope.launch {
        useCases.addHotelUseCase(collectionId, hotelId).onStart {
            addHotelsDataResponse.value = Result.Loading
        }.catch {
            if(it is HttpException){
                Log.d("AddHotel ViewModel", "catch")
                //Log.d("ChangePassword ViewModel E", it.message.toString())
                Log.d("AddHotel ViewModel", "hehe")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("AddHotel ViewModel Error", errorResponse.message)
                Log.d("AddHotel ViewModel Error", errorResponse.log)
                addHotelsDataResponse.value = Result.Error(errorResponse.message)
            }
            else if (it is Exception) {
                Log.d("AddHotel ViewModel", it.javaClass.name)
            }
        }.collect{
            addHotelsDataResponse.value = Result.Success(it)
        }
    }
}
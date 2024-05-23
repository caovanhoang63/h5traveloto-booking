package com.example.h5traveloto_booking.main.presentation.favorite.AllFavorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.AllSavedHotelsDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.CollectionDTO
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
class AllFavoriteViewModel @Inject constructor(
    private val useCases: FavoriteUseCases
) : ViewModel() {
    private val allSavedHotelsDataResponse = MutableStateFlow<Result<AllSavedHotelsDTO>>(Result.Idle)
    val AllSavedHotelsDataResponse = allSavedHotelsDataResponse.asStateFlow()

    fun getAllSavedHotels() = viewModelScope.launch {
        useCases.getAllSavedHotelsUseCase().onStart{
            allSavedHotelsDataResponse.value = Result.Loading
        }.catch {
            if(it is HttpException){
                Log.d("AllFavorite ViewModel", "catch")
                //Log.d("ChangePassword ViewModel E", it.message.toString())
                Log.d("AllFavorite ViewModel", "hehe")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("AllFavorite ViewModel Error", errorResponse.message)
                Log.d("AllFavorite ViewModel Error", errorResponse.log)
                allSavedHotelsDataResponse.value = Result.Error(errorResponse.message)
            }
            else if (it is Exception) {
                Log.d("AllFavorite ViewModel", it.javaClass.name)
            }
        }.collect{
            Log.d("AllFavorite ViewModel","Ok")
            Log.d("Success3",it.data.size.toString())
            Log.d("Success3",it.paging.total.toString())
            Log.d("Success3",it.paging.total.toString())
            allSavedHotelsDataResponse.value = Result.Success(it)
        }
    }
    private val unSavedHotelsDataResponse = MutableStateFlow<Result<Response>>(Result.Idle)
    val UnSavedHotelsDataResponse = unSavedHotelsDataResponse.asStateFlow()
    fun unsaveHotel(id: String) = viewModelScope.launch {
        useCases.unsaveHotelUseCase(id).onStart {
            unSavedHotelsDataResponse.value = Result.Loading
        }.catch {
            if(it is HttpException){
                Log.d("AllFavorite ViewModel", "catch")
                //Log.d("ChangePassword ViewModel E", it.message.toString())
                Log.d("AllFavorite ViewModel", "hehe")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("AllFavorite ViewModel Error", errorResponse.message)
                Log.d("AllFavorite ViewModel Error", errorResponse.log)
                unSavedHotelsDataResponse.value = Result.Error(errorResponse.message)
            }
            else if (it is Exception) {
                unSavedHotelsDataResponse.value = Result.Error("errorResponse.message")
                Log.d("AllFavorite ViewModel", it.javaClass.name)
            }
        }.collect{
            Log.d("Success3",it.data.toString())
            unSavedHotelsDataResponse.value = Result.Success(it)
            getAllSavedHotels()
        }
    }
}
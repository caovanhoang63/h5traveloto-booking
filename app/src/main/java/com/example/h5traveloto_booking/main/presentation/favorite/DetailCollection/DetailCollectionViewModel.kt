package com.example.h5traveloto_booking.main.presentation.favorite.DetailCollection

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
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class DetailCollectionViewModel @Inject constructor(
    private val useCases: FavoriteUseCases
): ViewModel() {
    private val allHotelsDataResponse = MutableStateFlow<Result<AllSavedHotelsDTO>>(Result.Idle)
    val AllHotelsDataResponse = allHotelsDataResponse.asStateFlow()

    fun getAllHotelsByCollectionId(id:String) = viewModelScope.launch {

        useCases.getHotelsByCollectionIdUseCase(id).onStart {
            allHotelsDataResponse.value = Result.Loading
        }.catch {
            if(it is HttpException){
                Log.d("DetailCollection ViewModel", "catch")
                //Log.d("ChangePassword ViewModel E", it.message.toString())
                Log.d("DetailCollection ViewModel", "hehe")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("DetailCollection ViewModel Error", errorResponse.message)
                Log.d("DetailCollection ViewModel Error", errorResponse.log)
                allHotelsDataResponse.value = Result.Error(errorResponse.message)
            }
            else if (it is Exception) {
                Log.d("DetailCollection ViewModel", it.javaClass.name)
            }
        }.collect{
            Log.d("DetailCollection",it.data.size.toString())
            allHotelsDataResponse.value = Result.Success(it)
        }
    }
    private val deleteHotelsDataResponse = MutableStateFlow<Result<Response>>(Result.Idle)
    val DeleteHotelsDataResponse = deleteHotelsDataResponse.asStateFlow()
    fun deleteHotel(collectionId: String, hotelId:String) = viewModelScope.launch {
        useCases.deleteHotelUseCase(collectionId, hotelId).onStart {
            deleteHotelsDataResponse.value = Result.Loading
        }.catch {
            if(it is HttpException){
                Log.d("DetailCollection ViewModel", "catch")
                //Log.d("ChangePassword ViewModel E", it.message.toString())
                Log.d("DetailCollection ViewModel", "hehe")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("DetailCollection ViewModel Error", errorResponse.message)
                Log.d("DetailCollection ViewModel Error", errorResponse.log)
                allHotelsDataResponse.value = Result.Error(errorResponse.message)
            }
            else if (it is Exception) {
                Log.d("DetailCollection ViewModel", it.javaClass.name)
            }
        }.collect{
            Log.d("DetailCollection ViewModel", it.toString())
            deleteHotelsDataResponse.value = Result.Success(it)
            getAllHotelsByCollectionId(collectionId)
        }
    }
}
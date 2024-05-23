package com.example.h5traveloto_booking.main.presentation.favorite.UpdateCollectionScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.Avatar
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.AddCollectionDTO
import com.example.h5traveloto_booking.main.presentation.domain.usecases.FavoriteUseCases
import com.example.h5traveloto_booking.util.ErrorResponse
import com.example.h5traveloto_booking.util.Result
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class UpdateCollectionViewModel @Inject constructor(
    val useCase: FavoriteUseCases
) : ViewModel() {
    private val updateCollectionDataResponse = MutableStateFlow<Result<com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.Response>>(Result.Idle)
    val UpdateCollectionDataResponse = updateCollectionDataResponse.asStateFlow()

    fun updateCollection(id:String,name:String?,image: Avatar?) = viewModelScope.launch {
        val data = AddCollectionDTO(name=name, cover = image)
        useCase.updateCollectionUseCase(id,data).onStart {
            updateCollectionDataResponse.value = Result.Loading
        }.catch {
            if(it is HttpException){
                Log.d("AddCollection ViewModel", "catch")
                //Log.d("ChangePassword ViewModel E", it.message.toString())
                Log.d("AddCollection ViewModel", "hehe")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("AddCollection ViewModel Error", errorResponse.message)
                Log.d("AddCollection ViewModel Error", errorResponse.log)
                updateCollectionDataResponse.value = Result.Error(errorResponse.message)
            }
            else if (it is Exception) {
                updateCollectionDataResponse.value = Result.Error("errorResponse.message")
                Log.d("AddHotel ViewModel", it.javaClass.name)
            }
        }.collect{
            updateCollectionDataResponse.value=Result.Success(it)
        }
    }
}
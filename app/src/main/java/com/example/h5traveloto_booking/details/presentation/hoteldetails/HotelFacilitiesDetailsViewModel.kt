package com.example.h5traveloto_booking.details.presentation.hoteldetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h5traveloto_booking.details.presentation.data.dto.HotelFacilitiesDetails.HotelFacilitiesDetailsDTO
import com.example.h5traveloto_booking.details.presentation.data.dto.roomFacilitiesDetails.RoomFacilitiesDetailsDTO
import com.example.h5traveloto_booking.details.presentation.domain.usecases.HotelFacilitiesDetailsUseCase
import com.example.h5traveloto_booking.details.presentation.domain.usecases.HotelFacilitiesDetailsUseCases
import com.example.h5traveloto_booking.details.presentation.domain.usecases.RoomFacilitiesDetailsUseCases
import com.example.h5traveloto_booking.share.shareDataHotelDetail
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
class HotelFacilitiesDetailsViewModel @Inject constructor(
    private val useCases: HotelFacilitiesDetailsUseCases,
) : ViewModel() {


    private val _HotelFacilitiesDetailsResponse = MutableStateFlow<Result<HotelFacilitiesDetailsDTO>>(Result.Idle)
    val hotelacilitiesDetailsResponse = _HotelFacilitiesDetailsResponse.asStateFlow()

    fun getHotelFacilitiesDetails(
    ) = viewModelScope.launch {

        useCases.getHotelFacilitiesDetailsUseCase(shareDataHotelDetail.getHotelId()).onStart {
            _HotelFacilitiesDetailsResponse.value = Result.Loading
            Log.d("HotelFacilitiesDetails ViewModel", "Loading")

        }.catch {
            if (it is HttpException) {
                Log.d("HotelFacilitiesDetails ViewModel", "catch")
                Log.d("HotelFacilitiesDetails ViewModel", "hehe")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("HotelFacilitiesDetails ViewModel Error", errorResponse.message)
                Log.d("HotelFacilitiesDetails ViewModel Error", errorResponse.log)
                _HotelFacilitiesDetailsResponse.value = Result.Error(errorResponse.message)
            }
            else if (it is Exception) {
                _HotelFacilitiesDetailsResponse.value = Result.Error("errorResponse.message")
                Log.d("HotelFacilitiesDetails ViewModel", it.message.toString())
            }
        }
            .collect {
                Log.d("HotelFacilitiesDetails Success", it.data.toString())
                _HotelFacilitiesDetailsResponse.value = Result.Success(it)
            }
    }
}
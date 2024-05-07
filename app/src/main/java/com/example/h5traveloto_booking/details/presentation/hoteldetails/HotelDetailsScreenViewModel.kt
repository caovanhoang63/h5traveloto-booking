package com.example.h5traveloto_booking.details.presentation.hoteldetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h5traveloto_booking.details.presentation.data.dto.hotelDetails.HotelDetailsDTO
import com.example.h5traveloto_booking.details.presentation.domain.usecases.HotelDetailsUseCases
import com.example.h5traveloto_booking.main.presentation.data.dto.Hotel.ListHotelDTO
import com.example.h5traveloto_booking.main.presentation.domain.usecases.HotelUseCases
import com.example.h5traveloto_booking.util.Result
import com.example.h5traveloto_booking.util.SharedPrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelDetailsScreenViewModel @Inject constructor(
    private val useCases: HotelDetailsUseCases,
    private val sharedPrefManager: SharedPrefManager
) : ViewModel() {
    private val _HotelDetailsResponse = MutableStateFlow<Result<HotelDetailsDTO>>(Result.Idle)
    val HotelDetailsResponse = _HotelDetailsResponse.asStateFlow()

    fun getHotelDetails(

    ) = viewModelScope.launch {
        val token = sharedPrefManager.getToken()
        Log.d("HotelDetails ViewModel", "Get token")
        Log.d("HotelDetails ViewModel Token", token.toString())
        val bearerToken = "Bearer $token"
        useCases.getHotelDetailsUseCase("3mMo3hVGTE4VTM").onStart {
            _HotelDetailsResponse.value = Result.Loading
            Log.d("HotelDetails ViewModel", "Loading")

        }.catch {

            Log.d("HotelDetails ViewModel", "catch")
            Log.d("HotelDetails ViewModel E", it.message.toString())
            _HotelDetailsResponse.value = Result.Error(it.message.toString())
        }.collect {
            Log.d("HotelDetails Success", it.data.description)
            _HotelDetailsResponse.value = Result.Success(it)
        }
    }
}
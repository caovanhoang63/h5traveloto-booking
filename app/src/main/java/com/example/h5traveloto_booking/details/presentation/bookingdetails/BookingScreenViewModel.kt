package com.example.h5traveloto_booking.details.presentation.bookingdetails

import android.util.Log
import androidx.core.graphics.rotationMatrix
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.CreateBookingDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.IdRespondDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.UserBookingDTO
import com.example.h5traveloto_booking.main.presentation.domain.usecases.BookingUseCases
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
class BookingScreenViewModel @Inject constructor (
    private val bookingUseCases: BookingUseCases,
    private val sharedPrefManager: SharedPrefManager
) : ViewModel() {

    private val _BookingIdResponse = MutableStateFlow<Result<IdRespondDTO>>(Result.Idle)
    val BookingIdResponse = _BookingIdResponse.asStateFlow()

    private val _bookingResponse = MutableStateFlow<Result<UserBookingDTO>>(Result.Idle)
    val BookingResponse = _bookingResponse.asStateFlow()

    fun createBooking (
        bookingData: CreateBookingDTO
    ) = viewModelScope.launch {
        val token = sharedPrefManager.getToken()
        Log.d("Booking ViewModel", "Get token")
        Log.d("Booking ViewModel Token", token.toString())
        val bearerToken = "Bearer $token"
        bookingUseCases.bookingUseCase(bookingData).onStart {
            _BookingIdResponse.value = Result.Loading
            Log.d("Booking ViewModel", "Loading")
        }.catch {
            Log.d("Booking ViewModel", "catch")
            Log.d("Booking ViewModel", it.message.toString())
            _BookingIdResponse.value = Result.Error(it.message.toString())
        }.collect {
            Log.d("Booking ViewModel", "UserBookings Success ${it.data.toString()}")
            _BookingIdResponse.value = Result.Success(it)
        }
    }
}
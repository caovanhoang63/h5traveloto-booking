package com.example.h5traveloto_booking.details.presentation.bookingdetails.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h5traveloto_booking.auth.domain.use_case.RegisterUsesCase
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.BookingResponse
import com.example.h5traveloto_booking.main.presentation.domain.usecases.BookingUseCases
import com.example.h5traveloto_booking.util.Result
import com.example.h5traveloto_booking.util.SharedPrefManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class BookingDetailsScreenViewModel @Inject constructor(
    private val usesCases: BookingUseCases,
    private val sharedPrefManager: SharedPrefManager
) : ViewModel() {
    private val _bookingResponse = MutableStateFlow<Result<BookingResponse>>(Result.Idle)
    val BookingResponse = _bookingResponse.asStateFlow()

    fun getBooking (
        bookingId: String
    ) = viewModelScope.launch {
        val token = sharedPrefManager.getToken()
        Log.d("BookingDetails ViewModel", "Get token")
        Log.d("BookingDetails ViewModel Token", token.toString())
        val bearerToken = "Bearer $token"

        usesCases.getBookingUseCase(bookingId).onStart {
            _bookingResponse.value = Result.Loading
            Log.d("BookingDetails ViewModel", "Loading")
        }.catch {
            Log.d("BookingDetails ViewModel", "catch")
            Log.d("BookingDetails ViewModel", it.message.toString())
            _bookingResponse.value = Result.Error(it.message.toString())
        }.collect {
            Log.d("BookingDetails ViewModel", "BookingDetails Screen Success")
            _bookingResponse.value = Result.Success(it)
        }
    }
}
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
            if(it is HttpException){
                Log.d("Booking ViewModel", "catch")
                //Log.d("ChangePassword ViewModel E", it.message.toString())
                Log.d("Booking ViewModel", "hehe")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("Booking ViewModel Error", errorResponse.message)
                _BookingIdResponse.value = Result.Error(errorResponse.message)
            }
            else if (it is Exception) {
                Log.d("Booking ViewModel", it.javaClass.name)
                _BookingIdResponse.value = Result.Error("loi roi")
            }
        }.collect {
            Log.d("Booking ViewModel", "UserBookings Success ${it.data.toString()}")
            _BookingIdResponse.value = Result.Success(it)
        }
    }
}
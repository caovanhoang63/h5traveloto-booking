package com.example.h5traveloto_booking.details.presentation.bookingdetails.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h5traveloto_booking.auth.domain.use_case.RegisterUsesCase
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.BookingResponse
import com.example.h5traveloto_booking.main.presentation.domain.usecases.BookingUseCases
import com.example.h5traveloto_booking.util.ErrorResponse
import com.example.h5traveloto_booking.util.Result
import com.example.h5traveloto_booking.util.SharedPrefManager
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.HttpException
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
            if(it is HttpException){
                Log.d("BookingDetails ViewModel", "catch")
                //Log.d("ChangePassword ViewModel E", it.message.toString())
                Log.d("BookingDetails ViewModel", "hehe")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("BookingDetails ViewModel Error", errorResponse.message)
                _bookingResponse.value = Result.Error(errorResponse.message)
            }
            else if (it is Exception) {
                Log.d("ChangePassword ViewModel", it.javaClass.name)
                _bookingResponse.value = Result.Error("loi roi")
            }
        }.collect {
            Log.d("BookingDetails ViewModel", "BookingDetails Screen Success")
            _bookingResponse.value = Result.Success(it)
        }
    }
}
package com.example.h5traveloto_booking.main.presentation.schedule

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.ListUserBookingDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.ListUserBookingParams
import com.example.h5traveloto_booking.main.presentation.domain.usecases.ListUserBookingUseCase
import com.example.h5traveloto_booking.main.presentation.domain.usecases.ListUserBookingUseCases
import com.example.h5traveloto_booking.share.UserShare
import com.example.h5traveloto_booking.util.Result
import com.example.h5traveloto_booking.util.SharedPrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val useCases: ListUserBookingUseCases,
    private val sharedPrefManager: SharedPrefManager
) : ViewModel() {
    val listUserBookingParams = ListUserBookingParams (
        state = "expired",
    )

    private val _UserBookingsResponse = MutableStateFlow<Result<ListUserBookingDTO>>(Result.Idle)
    val UserBookingsResponse = _UserBookingsResponse.asStateFlow()

    fun getUserBookings(

    ) = viewModelScope.launch {
        val token = sharedPrefManager.getToken()
        Log.d("Schedule ViewModel", "Get token")
        Log.d("Schedule ViewModel Token", token.toString())
        val bearerToken = "Bearer $token"

        if (UserShare.User.id != null) {
            useCases.getListUserBookingUseCase(UserShare.User.id.toString(), listUserBookingParams).onStart {
                _UserBookingsResponse.value = Result.Loading
                Log.d("Schedule ViewModel", "Loading")
            }.catch {
                Log.d("Schedule ViewModel", "catch")
                Log.d("Schedule ViewModel", it.message.toString())
                _UserBookingsResponse.value = Result.Error(it.message.toString())
            }.collect {
                Log.d("Schedule ViewModel", "UserBookings Success, has ${it.data.size} booking(s)")
                _UserBookingsResponse.value = Result.Success(it)
            }
        } else {
            Log.d("Schedule ViewModel", "User Id Null")
        }
    }
}
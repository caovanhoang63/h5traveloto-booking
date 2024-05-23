package com.example.h5traveloto_booking.details.presentation.bookingdetails.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h5traveloto_booking.auth.domain.use_case.RegisterUsesCase
import com.example.h5traveloto_booking.details.presentation.data.dto.reviews.CreateReviewDTO
import com.example.h5traveloto_booking.details.presentation.data.dto.roomFacilitiesDetails.RoomFacilitiesDetailsDTO
import com.example.h5traveloto_booking.details.presentation.data.dto.roomtypebyid.RoomTypeByIdDTO
import com.example.h5traveloto_booking.details.presentation.domain.usecases.ReviewUseCase
import com.example.h5traveloto_booking.details.presentation.domain.usecases.ReviewUseCases
import com.example.h5traveloto_booking.details.presentation.domain.usecases.RoomFacilitiesDetailsUseCases
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.BookingResponse
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchRoomType.SearchRoomTypeDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchRoomType.SearchRoomTypeParams
import com.example.h5traveloto_booking.main.presentation.domain.usecases.BookingUseCases
import com.example.h5traveloto_booking.main.presentation.domain.usecases.SearchUseCases
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
class BookingDetailsScreenViewModel @Inject constructor(
    private val bookingUsesCases: BookingUseCases,
    private val reviewUseCases: ReviewUseCases,
    private val sharedPrefManager: SharedPrefManager,
    private val roomTypeUseCases: RoomFacilitiesDetailsUseCases
) : ViewModel() {
    private val _bookingResponse = MutableStateFlow<Result<BookingResponse>>(Result.Idle)
    val BookingResponse = _bookingResponse.asStateFlow()

    private val _roomType = MutableStateFlow<Result<RoomTypeByIdDTO>>(Result.Idle)
    val RoomType = _roomType.asStateFlow()

    private val _roomTypeFacility = MutableStateFlow<Result<RoomFacilitiesDetailsDTO>>(Result.Idle)
    val RoomTypeFacility = _roomTypeFacility.asStateFlow()

    fun getBooking (
        bookingId: String
    ) = viewModelScope.launch {
        val token = sharedPrefManager.getToken()
        Log.d("BookingDetails ViewModel", "Get token")
        Log.d("BookingDetails ViewModel Token", token.toString())
        val bearerToken = "Bearer $token"

        bookingUsesCases.getBookingUseCase(bookingId).onStart {
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

    fun getRoomTypeById(
        roomTypeId: String
    ) = viewModelScope.launch {

        roomTypeUseCases.getRoomTypeByIdUseCase(roomTypeId).onStart {
            _roomType.value = Result.Loading
        }.catch {
            if(it is HttpException){
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                _roomType.value = Result.Error(errorResponse.message)
                Log.d("BookingDetails ViewModel", "${errorResponse.message}")
                Log.d("BookingDetails ViewModel", "${errorResponse.log}")

            }
            else if (it is Exception) {
                Log.d("BookingDetails ViewModel", "Exception")
                _roomType.value = Result.Error("loi roi")
            }
        }.collect {
            Log.d("BookingDetails ViewModel", "RoomTypeById Success")
            Log.d("BookingDetails ViewModel", it.toString())
            _roomType.value = Result.Success(it)
        }
    }

    fun getRoomTypeFacility(
        roomTypeId: String
    ) = viewModelScope.launch {
        roomTypeUseCases.getRoomFacilitiesDetailsUseCase(roomTypeId).onStart {
            _roomTypeFacility.value = Result.Loading
        }.catch {
            if(it is HttpException){
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                _roomTypeFacility.value = Result.Error(errorResponse.message)
                Log.d("BookingDetails ViewModel", "${errorResponse.message}")
                Log.d("BookingDetails ViewModel", "${errorResponse.log}")

            }
            else if (it is Exception) {
                Log.d("BookingDetails ViewModel", "Exception")
                _roomTypeFacility.value = Result.Error("loi roi")
            }
        }.collect {
            Log.d("BookingDetails ViewModel", "RoomTypeFacility Success")
            Log.d("BookingDetails ViewModel", it.toString())
            _roomTypeFacility.value = Result.Success(it)
        }
    }

    fun createReview(
        data: CreateReviewDTO
    ) = viewModelScope.launch {
        reviewUseCases.getReviewUseCase(data).onStart {

        }.catch {

        }.collect {

        }
    }
}
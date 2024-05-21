package com.example.h5traveloto_booking.details.presentation.hoteldetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h5traveloto_booking.details.presentation.data.dto.hotelDetails.HotelDetailsDTO
import com.example.h5traveloto_booking.details.presentation.data.dto.reviews.ListReviewsDTO
import com.example.h5traveloto_booking.details.presentation.domain.usecases.HotelDetailsUseCases
import com.example.h5traveloto_booking.details.presentation.domain.usecases.ListReviewsUseCase
import com.example.h5traveloto_booking.details.presentation.domain.usecases.ListReviewsUseCases
import com.example.h5traveloto_booking.main.presentation.data.dto.Hotel.ListHotelDTO
import com.example.h5traveloto_booking.main.presentation.domain.usecases.HotelUseCases
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
class HotelDetailsScreenViewModel @Inject constructor(
    private val useCases: HotelDetailsUseCases,
    private val reviewsUseCases: ListReviewsUseCases,
    private val sharedPrefManager: SharedPrefManager
) : ViewModel() {
    private val _HotelDetailsResponse = MutableStateFlow<Result<HotelDetailsDTO>>(Result.Idle)
    val HotelDetailsResponse = _HotelDetailsResponse.asStateFlow()

    private val _ListReviewsResponse = MutableStateFlow<Result<ListReviewsDTO>>(Result.Idle)
    val ListReviewsResponse = _ListReviewsResponse.asStateFlow()

    private var policy: HotelDetailsDTO? = null
    fun setPolicy() {
        if (_HotelDetailsResponse.value is Result.Success){
            policy = (_HotelDetailsResponse.value as Result.Success).data
        }
    }
    fun getPolicy(): HotelDetailsDTO? {
        return policy
    }


    fun getListReviews(

    ) = viewModelScope.launch {
        val token = sharedPrefManager.getToken()
        Log.d("Reviews ViewModel", "Get token")
        Log.d("Reviews ViewModel Token", token.toString())
        val bearerToken = "Bearer $token"
        Log.d("hotelid", shareDataHotelDetail.getHotelId())
        reviewsUseCases.geListReviewsUseCases("\"${shareDataHotelDetail.getHotelId()}\"").onStart {
            _ListReviewsResponse.value = Result.Loading
            Log.d("Reviews ViewModel", "Loading")
        }.catch {
            if(it is HttpException){
                Log.d("Reviews ViewModel", "catch")
                //Log.d("ChangePassword ViewModel E", it.message.toString())
                Log.d("Reviews ViewModel", "hehe")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("Reviews ViewModel Error", errorResponse.message)
                _ListReviewsResponse.value = Result.Error(errorResponse.message)
            }
            else if (it is Exception) {
                Log.d("Reviews ViewModel", it.javaClass.name)
            }
        }.collect {
            Log.d("Reviews Success", it.data.toString())
            _ListReviewsResponse.value = Result.Success(it)
        }
    }

    fun getHotelDetails(

    ) = viewModelScope.launch {
        val token = sharedPrefManager.getToken()
        Log.d("HotelDetails ViewModel", "Get token")
        Log.d("HotelDetails ViewModel Token", token.toString())
        val bearerToken = "Bearer $token"
        useCases.getHotelDetailsUseCase(shareDataHotelDetail.getHotelId()).onStart {
            _HotelDetailsResponse.value = Result.Loading
            Log.d("HotelDetails ViewModel", "Loading")

        }.catch {

            Log.d("HotelDetails ViewModel", "catch")
            Log.d("HotelDetails ViewModel E", it.message.toString())
            _HotelDetailsResponse.value = Result.Error(it.message.toString())
        }.collect {
            Log.d("HotelDetails Success", it.data.toString())
            _HotelDetailsResponse.value = Result.Success(it)
        }
    }
}
package com.example.h5traveloto_booking.details.presentation.roomdetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h5traveloto_booking.details.presentation.data.`class`.ListRoomsParams
import com.example.h5traveloto_booking.details.presentation.data.dto.hotelDetails.HotelDetailsDTO
import com.example.h5traveloto_booking.details.presentation.data.dto.listRooms.ListRoomDTO
import com.example.h5traveloto_booking.details.presentation.domain.usecases.HotelDetailsUseCases
import com.example.h5traveloto_booking.details.presentation.domain.usecases.ListRoomsUseCases
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchRoomType.SearchRoomTypeDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchRoomType.SearchRoomTypeParams
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
class ListRoomViewModel @Inject constructor(
    private val useCases: SearchUseCases,
    private val sharedPrefManager: SharedPrefManager
) : ViewModel() {
    private val _ListRoomsResponse = MutableStateFlow<Result<SearchRoomTypeDTO>>(Result.Idle)
    val ListRoomsResponse = _ListRoomsResponse.asStateFlow()
    /*val listRoomTypeParams =
        SearchRoomTypeParams(
            startDate = "\"29-06-2024\"",
            endDate = "\"30-06-2024\"",
            hotelId = "\"${shareDataHotelDetail.getHotelId()}\""
        )*/
    val listRoomTypeParams = SearchRoomTypeParams()
    fun setListRoomTypeParams(
        startDate: String,
        endDate: String,
        hotelId: String
    ) {
        listRoomTypeParams.startDate = startDate
        listRoomTypeParams.endDate = endDate
        listRoomTypeParams.hotelId = hotelId
    }
    fun getListRooms(

    ) = viewModelScope.launch {
        val token = sharedPrefManager.getToken()
        val bearerToken = "Bearer $token"
        Log.d("ListRooms ViewModel", listRoomTypeParams.toMap().toString())
        setListRoomTypeParams(shareDataHotelDetail.getStartDateString(), shareDataHotelDetail.getEndDateString(), shareDataHotelDetail.getHotelId())
        Log.d("ListRooms Params", listRoomTypeParams.toMap().toString())
        useCases.searchRoomTypeUseCase(listRoomTypeParams).onStart {
            _ListRoomsResponse.value = Result.Loading
            Log.d("ListRooms ViewModel", "Loading")

        }.catch {
            if (it is HttpException) {
                Log.d("ListRooms ViewModel", "catch")
                Log.d("ListRooms ViewModel", "hehe")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("ListRooms ViewModel Error", errorResponse.message)
                Log.d("ListRooms ViewModel Error", errorResponse.log)
                _ListRoomsResponse.value = Result.Error(errorResponse.message)
            }
            _ListRoomsResponse.value = Result.Error(it.message.toString())
        }
        .collect {
            Log.d("ListRooms Success", it.data.toString())
            _ListRoomsResponse.value = Result.Success(it)
        }
    }
}
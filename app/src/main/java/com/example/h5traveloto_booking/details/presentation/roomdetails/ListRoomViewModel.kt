package com.example.h5traveloto_booking.details.presentation.roomdetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h5traveloto_booking.details.presentation.data.`class`.ListRoomsParams
import com.example.h5traveloto_booking.details.presentation.data.dto.hotelDetails.HotelDetailsDTO
import com.example.h5traveloto_booking.details.presentation.data.dto.listRooms.ListRoomDTO
import com.example.h5traveloto_booking.details.presentation.domain.usecases.HotelDetailsUseCases
import com.example.h5traveloto_booking.details.presentation.domain.usecases.ListRoomsUseCases
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
class ListRoomViewModel @Inject constructor(
    private val useCases: ListRoomsUseCases,
    private val sharedPrefManager: SharedPrefManager
) : ViewModel() {
    private val _ListRoomsResponse = MutableStateFlow<Result<ListRoomDTO>>(Result.Idle)
    val ListRoomsResponse = _ListRoomsResponse.asStateFlow()
    val test = ListRoomsParams();
    fun getListRooms(

    ) = viewModelScope.launch {
        val token = sharedPrefManager.getToken()
        Log.d("ListRooms ViewModel", "Get token")
        Log.d("ListRooms ViewModel Token", token.toString())
        val bearerToken = "Bearer $token"
        useCases.getListRoomsUseCase(test).onStart {
            _ListRoomsResponse.value = Result.Loading
            Log.d("HotelDetails ViewModel", "Loading")

        }.catch {

            Log.d("ListRooms ViewModel", "catch")
            Log.d("ListRooms ViewModel E", it.message.toString())
            _ListRoomsResponse.value = Result.Error(it.message.toString())
        }.collect {
            Log.d("ListRooms Success",it.data.toString())
            _ListRoomsResponse.value = Result.Success(it)
        }
    }
}
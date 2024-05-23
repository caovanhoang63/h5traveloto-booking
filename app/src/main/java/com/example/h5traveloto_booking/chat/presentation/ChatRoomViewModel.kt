package com.example.h5traveloto_booking.chat.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h5traveloto_booking.chat.presentation.data.dto.ChatListDTO
import com.example.h5traveloto_booking.chat.presentation.data.dto.ChatRoom.ChatRoomDTO
import com.example.h5traveloto_booking.chat.presentation.domain.usecases.ChatListUseCases
import com.example.h5traveloto_booking.chat.presentation.domain.usecases.ChatRoomUseCases
import com.example.h5traveloto_booking.share.shareDataHotelDetail
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
class ChatRoomViewModel @Inject constructor(
    private val useCases: ChatRoomUseCases,
) : ViewModel() {
    private val _ChatRoomResponse = MutableStateFlow<Result<ChatRoomDTO>>(Result.Idle)
    val ChatRoomResponse = _ChatRoomResponse.asStateFlow()

    fun getChatRoom(

    ) = viewModelScope.launch {

        useCases.getChatRoomUseCase(shareDataHotelDetail.getHotelId()).onStart {
            _ChatRoomResponse.value = Result.Loading
            Log.d("ChatRoom ViewModel", "Loading")

        }.catch {

            Log.d("ChatRoom ViewModel", "catch")
            Log.d("ChatRoom ViewModel E", it.message.toString())
            _ChatRoomResponse.value = Result.Error(it.message.toString())
        }.collect {
            Log.d("ChatList Success", it.data.toString())
            _ChatRoomResponse.value = Result.Success(it)
        }
    }
}
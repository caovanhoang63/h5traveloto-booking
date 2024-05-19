package com.example.h5traveloto_booking.chat.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h5traveloto_booking.chat.presentation.data.dto.ChatListDTO
import com.example.h5traveloto_booking.chat.presentation.domain.usecases.ChatListUseCases
import com.example.h5traveloto_booking.details.presentation.data.dto.hotelDetails.HotelDetailsDTO
import com.example.h5traveloto_booking.details.presentation.domain.usecases.HotelDetailsUseCases
import com.example.h5traveloto_booking.util.Result
import com.example.h5traveloto_booking.util.SharedPrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import websocket.setupEvent
import websocket.socketHandler1
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor(
    private val useCases: ChatListUseCases,
    private val sharedPrefManager: SharedPrefManager
) : ViewModel() {
    private val _chatListResponse = MutableStateFlow<Result<ChatListDTO>>(Result.Idle)
    val ChatListResponse = _chatListResponse.asStateFlow()

    fun getChatList(

    ) = viewModelScope.launch {
        val token = sharedPrefManager.getToken()
        Log.d("ChatList ViewModel", "Get token")
        Log.d("ChatList ViewModel Token", token.toString())
        val bearerToken = "Bearer $token"

        useCases.getChatListUseCase("66472bbdf70ec79d3c5d6709",0,0).onStart {
            _chatListResponse.value = Result.Loading
            Log.d("ChatList ViewModel", "Loading")

        }.catch {

            Log.d("ChatList ViewModel", "catch")
            Log.d("ChatList ViewModel E", it.message.toString())
            _chatListResponse.value = Result.Error(it.message.toString())
        }.collect {
            Log.d("ChatList Success", it.data.toString())
            _chatListResponse.value = Result.Success(it)
        }
    }
}
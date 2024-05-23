package com.example.h5traveloto_booking.chat.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h5traveloto_booking.chat.presentation.data.dto.AllChat.AllChatDTO
import com.example.h5traveloto_booking.chat.presentation.data.dto.ChatRoom.ChatRoomDTO
import com.example.h5traveloto_booking.chat.presentation.domain.usecases.AllChatUseCases
import com.example.h5traveloto_booking.chat.presentation.domain.usecases.ChatRoomUseCases
import com.example.h5traveloto_booking.share.shareDataHotelDetail
import com.example.h5traveloto_booking.util.ErrorResponse
import com.example.h5traveloto_booking.util.Result
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
class AllChatScreenViewModel @Inject constructor(
    private val useCases: AllChatUseCases,
) : ViewModel() {
    private val _AllChatResponse = MutableStateFlow<Result<AllChatDTO>>(Result.Idle)
    val allChatResponse = _AllChatResponse.asStateFlow()

    fun getAllChat(
    ) = viewModelScope.launch {
        useCases.getAllChatUseCase().onStart {
            _AllChatResponse.value = Result.Loading
            Log.d("AllChat ViewModel", "Loading")

        }.catch {
            if (it is HttpException) {
                Log.d("AllChat ViewModel", "catch")
                Log.d("AllChat ViewModel", "hehe")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("AllChat ViewModel Error", errorResponse.message)
                Log.d("AllChat ViewModel Error", errorResponse.log)
                _AllChatResponse.value = Result.Error(errorResponse.message)
            }
            _AllChatResponse.value = Result.Error(it.message.toString())
        }
            .collect {
                Log.d("AllChat Success", it.data.toString())
                _AllChatResponse.value = Result.Success(it)
            }
    }
}
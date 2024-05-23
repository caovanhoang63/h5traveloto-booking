package com.example.h5traveloto_booking.chat.presentation.data.api

import com.example.h5traveloto_booking.chat.presentation.data.dto.AllChat.AllChatDTO
import retrofit2.http.GET

interface AllChatApi {
    @GET("/v1/users/chat-rooms")
    suspend fun getAllChat(): AllChatDTO
}
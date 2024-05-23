package com.example.h5traveloto_booking.chat.presentation.data.api

import com.example.h5traveloto_booking.chat.presentation.data.dto.ChatRoom.ChatRoomDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface ChatRoomApi {
    @GET("/v1/chat/hotels/{hotel-id}")
    suspend fun getChatRoom(
        @Path("hotel-id") hotelId: String
    ) : ChatRoomDTO
}
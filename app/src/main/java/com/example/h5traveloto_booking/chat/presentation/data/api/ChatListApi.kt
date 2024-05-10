package com.example.h5traveloto_booking.chat.presentation.data.api

import com.example.h5traveloto_booking.chat.presentation.data.dto.ChatListDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ChatListApi {
    @GET("/v1/chat/rooms/{roomid}/messages")
    suspend fun getChatList(
        @Path("roomid") roomid: String,
        @Query("page") page: Int?,
        @Query("limit") limit: Int?
    ) : ChatListDTO

}
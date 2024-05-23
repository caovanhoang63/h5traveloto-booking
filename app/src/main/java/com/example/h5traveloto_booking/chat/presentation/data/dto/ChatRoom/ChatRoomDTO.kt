package com.example.h5traveloto_booking.chat.presentation.data.dto.ChatRoom


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChatRoomDTO(
    @Json(name = "data")
    val `data`: Data
)
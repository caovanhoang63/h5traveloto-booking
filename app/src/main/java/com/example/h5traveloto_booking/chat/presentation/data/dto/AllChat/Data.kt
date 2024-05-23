package com.example.h5traveloto_booking.chat.presentation.data.dto.AllChat


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "hotel")
    val hotel: Hotel,
    @Json(name = "hotel_id")
    val hotelId: String,
    @Json(name = "hotel_unread")
    val hotelUnread: Int,
    @Json(name = "id")
    val id: String,
    @Json(name = "last_message")
    val lastMessage: LastMessage?,
    @Json(name = "status")
    val status: Int,
    @Json(name = "total_message")
    val totalMessage: Int,
    @Json(name = "updated_at")
    val updatedAt: String,
    @Json(name = "user_id")
    val userId: String,
    @Json(name = "user_unread")
    val userUnread: Int
)
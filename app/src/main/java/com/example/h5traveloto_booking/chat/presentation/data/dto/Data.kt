package com.example.h5traveloto_booking.chat.presentation.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "from")
    val from: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "is_from_customer")
    val isFromCustomer: Boolean,
    @Json(name = "message")
    val message: String,
    @Json(name = "room_id")
    val roomId: String,
    @Json(name = "status")
    val status: Int,
    @Json(name = "updated_at")
    val updatedAt: String
)
package com.example.h5traveloto_booking.details.presentation.data.dto.reviews


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "booking_id")
    val bookingId: String,
    @Json(name = "comment")
    val comment: String,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "hotel_id")
    val hotelId: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "rating")
    val rating: Int,
    @Json(name = "room_type_id")
    val roomTypeId: String,
    @Json(name = "status")
    val status: Int,
    @Json(name = "updated_at")
    val updatedAt: String,
    @Json(name = "user")
    val user: User,
    @Json(name = "user_id")
    val userId: String
)
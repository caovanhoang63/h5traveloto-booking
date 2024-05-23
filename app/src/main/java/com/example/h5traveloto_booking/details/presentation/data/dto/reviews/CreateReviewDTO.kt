package com.example.h5traveloto_booking.details.presentation.data.dto.reviews

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateReviewDTO(
    @Json(name = "hotel_id")
    val hotelId: String,
    @Json(name = "comment")
    val comment: String,
    @Json(name = "room_type_id")
    val roomTypeId: String,
    @Json(name = "rate")
    val rate: Int
)

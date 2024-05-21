package com.example.h5traveloto_booking.details.presentation.data.dto.reviews


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Filter(
    @Json(name = "booking_id")
    val bookingId: Any?,
    @Json(name = "hotel_id")
    val hotelId: Any?,
    @Json(name = "rating")
    val rating: Int?,
    @Json(name = "room_type_id")
    val roomTypeId: Any?,
    @Json(name = "user_id")
    val userId: Any?
)
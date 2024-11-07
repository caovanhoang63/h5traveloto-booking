package com.example.h5traveloto_booking.main.presentation.data.dto.Payment


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Filter(
    @Json(name = "expiry_date")
    val expiryDate: Any,
    @Json(name = "hotel_id")
    val hotelId: String,
    @Json(name = "room_type_id")
    val roomTypeId: Any,
    @Json(name = "start_date")
    val startDate: Any
)
package com.example.h5traveloto_booking.main.presentation.data.dto.Booking

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Filter (
    @Json(name = "user_id")
    val userId: Int,
    @Json(name = "hotel_id")
    val hotelId: Int,
    @Json(name = "room_type_id")
    val roomTypeId: Int,
    @Json(name = "start_date")
    val startDate: String?,
    @Json(name = "end_date")
    val endDate: String?
)
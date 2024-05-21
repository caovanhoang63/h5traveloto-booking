package com.example.h5traveloto_booking.main.presentation.data.dto.Booking

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CreateBookingDTO (
    @Json(name = "hotel_id")
    val hotelId: String,
    @Json(name = "room_type_id")
    val roomTypeId: String,
    @Json(name = "room_quantity")
    val roomQuantity: Int,
    @Json(name = "adults")
    val adults: Int,
    @Json(name = "children")
    val children: Int,
    @Json(name = "start_date")
    val startDate: String,
    @Json(name = "end_date")
    val endDate: String
)
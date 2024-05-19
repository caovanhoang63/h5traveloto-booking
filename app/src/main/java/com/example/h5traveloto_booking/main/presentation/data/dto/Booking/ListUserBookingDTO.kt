package com.example.h5traveloto_booking.main.presentation.data.dto.Booking

import com.example.h5traveloto_booking.main.presentation.data.dto.Hotel.Paging
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ListUserBookingDTO (
    @Json(name = "data")
    val data: List<HotelDTO>,
    @Json(name = "paging")
    val paging: Paging,
    @Json(name = "filter")
    val filter: Filter
)
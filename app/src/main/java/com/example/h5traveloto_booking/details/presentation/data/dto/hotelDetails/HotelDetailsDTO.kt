package com.example.h5traveloto_booking.details.presentation.data.dto.hotelDetails


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HotelDetailsDTO(
    @Json(name = "data")
    val `data`: Data
)
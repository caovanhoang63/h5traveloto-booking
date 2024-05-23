package com.example.h5traveloto_booking.chat.presentation.data.dto.AllChat


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "lat")
    val lat: Double,
    @Json(name = "lon")
    val lon: Double
)
package com.example.h5traveloto_booking.main.presentation.data.dto.Booking

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Province(
    @Json(name = "code")
    val code: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "full_name")
    val fullName: String
)

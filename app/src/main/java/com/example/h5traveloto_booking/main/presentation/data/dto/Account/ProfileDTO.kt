package com.example.h5traveloto_booking.main.presentation.data.dto.Account


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProfileDTO(
    @Json(name = "data")
    val `data`: Profile
)
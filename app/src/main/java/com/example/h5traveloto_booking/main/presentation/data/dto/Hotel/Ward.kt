package com.example.h5traveloto_booking.main.presentation.data.dto.Hotel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Ward(
    @Json(name = "code")
    val code: String,
    @Json(name = "name")
    val name: String
)
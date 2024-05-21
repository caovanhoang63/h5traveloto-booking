package com.example.h5traveloto_booking.main.presentation.data.dto.Favorite


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class District(
    @Json(name = "code")
    val code: Int,
    @Json(name = "name")
    val name: String
)
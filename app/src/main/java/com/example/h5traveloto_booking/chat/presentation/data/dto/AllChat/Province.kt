package com.example.h5traveloto_booking.chat.presentation.data.dto.AllChat


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Province(
    @Json(name = "code")
    val code: String,
    @Json(name = "full_name")
    val fullName: String,
    @Json(name = "name")
    val name: String
)
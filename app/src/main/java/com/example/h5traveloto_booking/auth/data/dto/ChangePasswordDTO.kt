package com.example.h5traveloto_booking.auth.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChangePasswordDTO(
    @Json(name = "password")
    val password: String
)
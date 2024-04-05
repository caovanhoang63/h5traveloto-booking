package com.example.h5traveloto_booking.auth.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class SignUpResponseDTO (
    @Json(name = "data")
    val id : String
)
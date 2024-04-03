package com.example.h5traveloto_booking.auth.data.dto

import com.squareup.moshi.Json



data class LoginRequestDTO(
    @Json(name = "email")
    val email: String,
    @Json(name = "password")
    val password: String
)


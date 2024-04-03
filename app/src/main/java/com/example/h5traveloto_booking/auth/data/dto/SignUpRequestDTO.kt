package com.example.h5traveloto_booking.auth.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class SignUpRequestDTO(
    @Json(name = "first_name")
    val firstName : String,
    @Json(name = "last_name")
    val lastName : String,
    @Json(name = "email")
    val email: String,
    @Json(name = "password")
    val password: String
)
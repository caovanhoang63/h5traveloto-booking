package com.example.h5traveloto_booking.main.presentation.data.dto.Account


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChangePasswordDTO(
    @Json(name = "old_password")
    val oldPassword: String,
    @Json(name = "password")
    val password: String
)
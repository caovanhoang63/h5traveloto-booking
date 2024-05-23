package com.example.h5traveloto_booking.auth.data.dto.forgotpassword

import com.squareup.moshi.Json

data class ChangePasswordDTO(
    @Json(name = "data")
    val data: Boolean
)

data class ChangePasswordRequest(
    @Json(name = "password")
    val password: String,
    @Json(name = "pin")
    val pin: String
)

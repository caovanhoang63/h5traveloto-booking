package com.example.h5traveloto_booking.auth.data.dto.forgotpassword

import com.squareup.moshi.Json

data class SentMailDTO(
    @Json(name = "data")
    val data: Boolean,
)

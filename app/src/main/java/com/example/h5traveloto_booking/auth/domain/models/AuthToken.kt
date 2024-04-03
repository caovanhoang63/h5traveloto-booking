package com.example.h5traveloto_booking.auth.domain.models

import com.squareup.moshi.Json
import java.time.LocalDateTime

data class AuthToken(
    val accessToken: String,
    val created : LocalDateTime,
    val expiryDate: Long
)
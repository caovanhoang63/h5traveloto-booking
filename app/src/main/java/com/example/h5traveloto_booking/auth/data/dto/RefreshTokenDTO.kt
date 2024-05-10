package com.example.h5traveloto_booking.auth.data.dto

import com.squareup.moshi.Json

data class RefreshTokenDTO(
    @Json(name = "data")
    val data: RefreshToken,
)

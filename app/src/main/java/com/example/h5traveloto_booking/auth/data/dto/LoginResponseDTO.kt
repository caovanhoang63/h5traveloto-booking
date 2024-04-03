package com.example.h5traveloto_booking.auth.data.dto

import com.squareup.moshi.Json
import java.time.LocalDateTime

data class LoginResponseDTO(
    val data: DataResponse
)

data class DataResponse(

    @Json(name = "access_token")
    val accessToken: Token,

    @Json(name = "refresh_token")
    val refresh_token: Token
)

data class Token(
    @Json(name = "token")
    val Token: String,
    @Json(name = "created")
    val created: LocalDateTime,
    @Json(name = "expiry")
    val expiry: Int
)
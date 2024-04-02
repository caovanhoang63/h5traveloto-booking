package com.example.h5traveloto_booking.auth.domain.models

data class AuthenticateRequest(
    val email: String,
    val password: String
)

data class AuthenticateResponse(
    val accessToken: String,
)
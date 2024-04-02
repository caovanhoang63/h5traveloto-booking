package com.example.h5traveloto_booking.auth.domain.models

data class RegisterRequest(
    val first_name : String,
    val last_name : String,
    val email: String,
    val password: String
)

data class RegisterResponse(
    val id : String
)
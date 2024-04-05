package com.example.h5traveloto_booking.auth.data.dto

data class AccessToken(
    val Token: String,
    val created: String,
    val expiry: Int
)
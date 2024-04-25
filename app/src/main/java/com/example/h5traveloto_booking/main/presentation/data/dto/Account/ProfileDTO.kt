package com.example.h5traveloto_booking.main.presentation.data.dto.Account

import com.squareup.moshi.Json

data class ProfileDTO(
    @Json(name = "data")
    val data: Profile
)

data class Profile(
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "last_name")
    val lastName: String,
    @Json(name = "phone")
    val phone: String,
    @Json(name = "role")
    val role: String,
    @Json(name = "status")
    val status: Int,
    @Json(name = "updated_at")
    val updatedAt: String
)

package com.example.h5traveloto_booking.details.presentation.data.dto.reviews


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "avatar")
    val avatar: Any?,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "last_name")
    val lastName: String,
    @Json(name = "role")
    val role: String,
    @Json(name = "status")
    val status: Int,
    @Json(name = "updated_at")
    val updatedAt: String
)
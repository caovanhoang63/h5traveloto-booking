package com.example.h5traveloto_booking.main.presentation.data.dto.Account

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class UpdateProfileDTO(
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "last_name")
    val lastName: String,
    @Json(name = "phone")
    val phone: String,
)

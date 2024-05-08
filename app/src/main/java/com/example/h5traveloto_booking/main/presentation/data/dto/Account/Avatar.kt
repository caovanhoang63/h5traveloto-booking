package com.example.h5traveloto_booking.main.presentation.data.dto.Account


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Avatar(
    @Json(name = "cloud_name")
    val cloudName: String,
    @Json(name = "extension")
    val extension: String,
    @Json(name = "height")
    val height: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "url")
    val url: String,
    @Json(name = "width")
    val width: Int
)
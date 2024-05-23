package com.example.h5traveloto_booking.details.presentation.data.dto.roomtypebyid


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Bed(
    @Json(name = "double")
    val double: Int,
    @Json(name = "king")
    val king: Int,
    @Json(name = "others")
    val others: String?,
    @Json(name = "queen")
    val queen: Int,
    @Json(name = "single")
    val single: Int
)
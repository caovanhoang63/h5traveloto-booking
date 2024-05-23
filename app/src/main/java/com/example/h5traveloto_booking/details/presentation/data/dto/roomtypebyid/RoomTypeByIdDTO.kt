package com.example.h5traveloto_booking.details.presentation.data.dto.roomtypebyid


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RoomTypeByIdDTO(
    @Json(name = "data")
    val `data`: Data
)
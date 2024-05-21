package com.example.h5traveloto_booking.main.presentation.data.dto.Booking

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class IdRespondDTO (
    @Json(name = "data")
    val data: String
)
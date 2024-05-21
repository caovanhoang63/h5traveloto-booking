package com.example.h5traveloto_booking.main.presentation.data.dto.Search

import com.squareup.moshi.Json


data class DistrictsDTO(
    @Json(name = "data")
    val districts: List<District>
)

data class District(
    @Json(name = "name")
    val name: String,
    @Json(name = "code")
    val code: String
)
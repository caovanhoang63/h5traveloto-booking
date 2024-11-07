package com.example.h5traveloto_booking.main.presentation.data.dto.Payment


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DealDTO(
    @Json(name = "data")
    val `data`: List<DataXXX>,
    @Json(name = "filter")
    val filter: Filter,
    @Json(name = "paging")
    val paging: Paging
)
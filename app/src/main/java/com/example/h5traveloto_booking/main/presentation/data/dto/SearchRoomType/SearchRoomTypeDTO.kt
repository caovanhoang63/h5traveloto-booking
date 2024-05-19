package com.example.h5traveloto_booking.main.presentation.data.dto.SearchRoomType


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchRoomTypeDTO(
    @Json(name = "data")
    val `data`: List<Data>,
    @Json(name = "filter")
    val filter: Filter
)
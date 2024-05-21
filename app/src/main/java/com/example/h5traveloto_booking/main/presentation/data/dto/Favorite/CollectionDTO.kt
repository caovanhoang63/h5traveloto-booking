package com.example.h5traveloto_booking.main.presentation.data.dto.Favorite


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CollectionDTO(
    @Json(name = "data")
    val `data`: List<Data>,
    @Json(name = "filter")
    val filter: Filter,
    @Json(name = "paging")
    val paging: Paging
)
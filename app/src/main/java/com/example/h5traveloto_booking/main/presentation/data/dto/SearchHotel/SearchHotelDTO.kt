package com.example.h5traveloto_booking.main.presentation.data.dto.SearchHotel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchHotelDTO(
    @Json(name = "data")
    val `data`: List<Data>?,
    @Json(name = "filter")
    val filter: Filter?,
    @Json(name = "paging")
    val paging: Paging?
)
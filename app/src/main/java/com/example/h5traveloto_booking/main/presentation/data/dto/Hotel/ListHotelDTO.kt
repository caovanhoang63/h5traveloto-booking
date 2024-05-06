package com.example.h5traveloto_booking.main.presentation.data.dto.Hotel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListHotelDTO(
    @Json(name = "data")
    val `data`: List<HotelDTO>,
    @Json(name = "filter")
    val filter: Filter,
    @Json(name = "paging")
    val paging: Paging
)
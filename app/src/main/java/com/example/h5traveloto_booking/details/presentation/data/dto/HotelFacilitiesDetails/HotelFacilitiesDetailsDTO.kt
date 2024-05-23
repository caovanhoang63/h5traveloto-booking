package com.example.h5traveloto_booking.details.presentation.data.dto.HotelFacilitiesDetails


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HotelFacilitiesDetailsDTO(
    @Json(name = "data")
    val `data`: List<Data>
)
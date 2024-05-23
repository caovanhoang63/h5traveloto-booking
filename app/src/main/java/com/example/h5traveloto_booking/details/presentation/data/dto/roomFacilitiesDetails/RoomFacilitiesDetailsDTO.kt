package com.example.h5traveloto_booking.details.presentation.data.dto.roomFacilitiesDetails


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RoomFacilitiesDetailsDTO(
    @Json(name = "data")
    val `data`: List<Data>
)
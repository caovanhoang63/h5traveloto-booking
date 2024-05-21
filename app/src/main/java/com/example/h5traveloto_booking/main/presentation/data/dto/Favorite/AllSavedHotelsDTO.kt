package com.example.h5traveloto_booking.main.presentation.data.dto.Favorite


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AllSavedHotelsDTO(
    @Json(name = "data")
    val `data`: List<DataX>,
    @Json(name = "paging")
    val paging: Paging
)
package com.example.h5traveloto_booking.chat.presentation.data.dto.AllChat


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AllChatDTO(
    @Json(name = "data")
    val `data`: List<Data>,
    @Json(name = "paging")
    val paging: Paging
)
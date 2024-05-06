package com.example.h5traveloto_booking.main.presentation.data.dto.Hotel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Paging(
    @Json(name = "cursor")
    val cursor: String?,
    @Json(name = "limit")
    val limit: Int?,
    @Json(name = "next_cursor")
    val nextCursor: String?,
    @Json(name = "page")
    val page: Int?,
    @Json(name = "total")
    val total: Int?
)
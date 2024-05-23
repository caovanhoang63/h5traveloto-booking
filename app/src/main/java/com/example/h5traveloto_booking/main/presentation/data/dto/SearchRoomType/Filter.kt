package com.example.h5traveloto_booking.main.presentation.data.dto.SearchRoomType


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Filter(
    @Json(name = "adults")
    val adults: Int,
    @Json(name = "children")
    val children: Int,
    @Json(name = "end_date")
    val endDate: String,
    @Json(name = "hotel_id")
    val hotelId: String,
    @Json(name = "max_price")
    val maxPrice: Int,
    @Json(name = "min_price")
    val minPrice: Int,
    @Json(name = "query_time")
    val queryTime: Int,
    @Json(name = "room_quantity")
    val roomQuantity: Int,
    @Json(name = "start_date")
    val startDate: String
)
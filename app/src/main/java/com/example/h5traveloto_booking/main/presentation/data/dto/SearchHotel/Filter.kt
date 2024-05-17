package com.example.h5traveloto_booking.main.presentation.data.dto.SearchHotel


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
    @Json(name = "id")
    val id: String,
    @Json(name = "lat")
    val lat: String,
    @Json(name = "list_facility")
    val listFacility: Any,
    @Json(name = "lng")
    val lng: String,
    @Json(name = "max_price")
    val maxPrice: Int,
    @Json(name = "min_price")
    val minPrice: Int,
    @Json(name = "query_time")
    val queryTime: Int,
    @Json(name = "room_quantity")
    val roomQuantity: Int,
    @Json(name = "search_term")
    val searchTerm: String,
    @Json(name = "search_text")
    val searchText: Any,
    @Json(name = "star")
    val star: Int,
    @Json(name = "start_date")
    val startDate: String
)
package com.example.h5traveloto_booking.details.presentation.data.dto.listRooms


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Filter(
    @Json(name = "area")
    val area: Int,
    @Json(name = "bed")
    val bed: Any?,
    @Json(name = "break_fast")
    val breakFast: Boolean,
    @Json(name = "free_cancel")
    val freeCancel: Boolean,
    @Json(name = "hotel_id")
    val hotelId: String,
    @Json(name = "max_customer")
    val maxCustomer: Int,
    @Json(name = "max_price")
    val maxPrice: Long,
    @Json(name = "min_price")
    val minPrice: Int,
    @Json(name = "pay_in_hotel")
    val payInHotel: Boolean
)
package com.example.h5traveloto_booking.main.presentation.data.dto.Payment


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataXX(
    @Json(name = "adults")
    val adults: Int,
    @Json(name = "children")
    val children: Int,
    @Json(name = "currency")
    val currency: String,
    @Json(name = "discount_amount")
    val discountAmount: Int,
    @Json(name = "end_date")
    val endDate: String,
    @Json(name = "final_amount")
    val finalAmount: Int,
    @Json(name = "hotel_id")
    val hotelId: String,
    @Json(name = "id")
    val id: Any,
    @Json(name = "room_quantity")
    val roomQuantity: Int,
    @Json(name = "room_type_id")
    val roomTypeId: String,
    @Json(name = "start_date")
    val startDate: String,
    @Json(name = "status")
    val status: Int,
    @Json(name = "total_amount")
    val totalAmount: Int
)
package com.example.h5traveloto_booking.main.presentation.data.dto.Booking

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.datetime.LocalDate

@JsonClass(generateAdapter = true)
data class BookingDTO (
    @Json(name = "id")
    val id: String,
    @Json(name = "status")
    val status: Int,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "updated_at")
    val updatedAt: String,
    @Json(name = "user_id")
    val userId: String,
    @Json(name = "room_type_id")
    val roomTypeId: String,
    @Json(name = "room_quantity")
    val roomQuantity: Int,
    @Json(name = "adults")
    val adults: Int,
    @Json(name = "children")
    val children: Int,
    @Json(name = "deal_id")
    val dealId: String?,
    @Json(name = "total_amount")
    val totalAmount: Long,
    @Json(name = "discount_amount")
    val discountAmount: Long,
    @Json(name = "final_amount")
    val finalAmount: Long,
    @Json(name = "currency")
    val currency: String,
    @Json(name = "state")
    val state: String,
    @Json(name = "pay_in_hotel")
    val payInHotel: Boolean,
    @Json(name = "start_date")
    val startDate: String,
    @Json(name = "end_date")
    val endDate: String
)

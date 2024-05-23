package com.example.h5traveloto_booking.main.presentation.data.dto.Payment


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataX(
    @Json(name = "amount")
    val amount: Int,
    @Json(name = "booking_id")
    val bookingId: String,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "currency")
    val currency: String,
    @Json(name = "customer_id")
    val customerId: String,
    @Json(name = "hotel_id")
    val hotelId: String,
    @Json(name = "method")
    val method: String,
    @Json(name = "payment_status")
    val paymentStatus: String,
    @Json(name = "status")
    val status: Int,
    @Json(name = "txn_id")
    val txnId: String,
    @Json(name = "updated_at")
    val updatedAt: String
)
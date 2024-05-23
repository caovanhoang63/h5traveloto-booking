package com.example.h5traveloto_booking.main.presentation.data.dto.Payment


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Link(
    @Json(name = "amount")
    val amount: Float,
    @Json(name = "booking_id")
    val bookingId: String,
    @Json(name = "currency")
    val currency: String,
    @Json(name = "deal_id")
    val dealId: String?,
    @Json(name = "method")
    val method: String,
    @Json(name = "payment_url")
    val paymentUrl: String,
    @Json(name = "txn_id")
    val txnId: String?,
    @Json(name = "created_at")
    val createdAt: String,
)
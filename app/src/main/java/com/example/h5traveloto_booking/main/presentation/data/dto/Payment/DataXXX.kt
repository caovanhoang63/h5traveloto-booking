package com.example.h5traveloto_booking.main.presentation.data.dto.Payment


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataXXX(
    @Json(name = "available_quantity")
    val availableQuantity: Int,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "discount_amount")
    val discountAmount: Int,
    @Json(name = "discount_percent")
    val discountPercent: Int,
    @Json(name = "discount_type")
    val discountType: String,
    @Json(name = "expiry_date")
    val expiryDate: String,
    @Json(name = "hotel_id")
    val hotelId: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "image")
    val image: Image,
    @Json(name = "is_unlimited")
    val isUnlimited: Boolean,
    @Json(name = "min_price")
    val minPrice: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "room_type_id")
    val roomTypeId: String,
    @Json(name = "start_date")
    val startDate: String,
    @Json(name = "status")
    val status: Int,
    @Json(name = "total_quantity")
    val totalQuantity: Int,
    @Json(name = "updated_at")
    val updatedAt: String
)
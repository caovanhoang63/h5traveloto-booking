package com.example.h5traveloto_booking.details.presentation.data.dto.roomtypebyid


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "area")
    val area: Int,
    @Json(name = "bed")
    val bed: Bed,
    @Json(name = "break_fast")
    val breakFast: Boolean,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "cur_available_room")
    val curAvailableRoom: Int,
    @Json(name = "free_cancel")
    val freeCancel: Boolean,
    @Json(name = "hotel_id")
    val hotelId: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "images")
    val images: List<Image>?,
    @Json(name = "max_customer")
    val maxCustomer: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "pay_in_hotel")
    val payInHotel: Boolean,
    @Json(name = "price")
    val price: Int,
    @Json(name = "rating")
    val rating: Int,
    @Json(name = "status")
    val status: Int,
    @Json(name = "total_room")
    val totalRoom: Int,
    @Json(name = "updated_at")
    val updatedAt: String
)
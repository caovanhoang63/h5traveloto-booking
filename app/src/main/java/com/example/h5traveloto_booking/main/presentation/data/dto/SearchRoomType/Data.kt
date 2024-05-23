package com.example.h5traveloto_booking.main.presentation.data.dto.SearchRoomType


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "area")
    val area: Int,
    @Json(name = "available_room")
    val availableRoom: Int,
    @Json(name = "bed")
    val bed: Bed,
    @Json(name = "break_fast")
    val breakFast: Int,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "facility_list")
    val facilityList: List<Int>?,
    @Json(name = "free_cancel")
    val freeCancel: Int,
    @Json(name = "id")
    val id: String,
    @Json(name = "images")
    val images: List<Image>?,
    @Json(name = "max_customer")
    val maxCustomer: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "pay_in_hotel")
    val payInHotel: Int,
    @Json(name = "price")
    val price: Int,
    @Json(name = "status")
    val status: Int,
    @Json(name = "total_room")
    val totalRoom: Int,
    @Json(name = "description")
    val description: String,
    @Json(name = "updated_at")
    val updatedAt: String
)
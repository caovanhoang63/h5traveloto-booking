package com.example.h5traveloto_booking.chat.presentation.data.dto.AllChat


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Hotel(
    @Json(name = "address")
    val address: String,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "district")
    val district: District,
    @Json(name = "hotel_type")
    val hotelType: Int,
    @Json(name = "hotline")
    val hotline: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "images")
    val images: List<Image>,
    @Json(name = "location")
    val location: Location,
    @Json(name = "logo")
    val logo: Logo,
    @Json(name = "name")
    val name: String,
    @Json(name = "province")
    val province: Province,
    @Json(name = "star")
    val star: Int,
    @Json(name = "status")
    val status: Int,
    @Json(name = "total_rating")
    val totalRating: Int,
    @Json(name = "total_room_type")
    val totalRoomType: Int,
    @Json(name = "updated_at")
    val updatedAt: String,
    @Json(name = "ward")
    val ward: Ward
)
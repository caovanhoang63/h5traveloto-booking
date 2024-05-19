package com.example.h5traveloto_booking.main.presentation.data.dto.Booking

import com.example.h5traveloto_booking.main.presentation.data.dto.Hotel.*
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class HotelDTO (
    @Json(name = "id")
    val id: String,
    @Json(name = "status")
    val status: String,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "updated_at")
    val updatedAt: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "address")
    val address: String,
    @Json(name = "logo")
    val logo: Logo?,
    @Json(name = "image")
    val image: Image?,
    @Json(name = "province")
    val province: Province,
    @Json(name = "district")
    val district: District?,
    @Json(name = "ward")
    val ward: Ward,
    @Json(name = "star")
    val star: Int,
    @Json(name = "total_rating")
    val totalRating: Int
)
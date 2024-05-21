package com.example.h5traveloto_booking.main.presentation.data.dto.Favorite


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataX(
    @Json(name = "address")
    val address: String,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "district")
    val district: District,
    @Json(name = "id")
    val id: Any,
    @Json(name = "images")
    val images: List<Image>,
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
    @Json(name = "ward")
    val ward: Ward
)
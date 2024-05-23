package com.example.h5traveloto_booking.details.presentation.data.dto.roomFacilitiesDetails


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "description_en")
    val descriptionEn: String,
    @Json(name = "description_vn")
    val descriptionVn: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "name_en")
    val nameEn: String,
    @Json(name = "name_vn")
    val nameVn: String,
    @Json(name = "status")
    val status: Int,
    @Json(name = "updated_at")
    val updatedAt: String
)
package com.example.h5traveloto_booking.main.presentation.data.dto.Favorite


import com.example.h5traveloto_booking.main.presentation.data.dto.Account.Avatar
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "cover")
    val cover: Avatar?,
    @Json(name = "created_at")
    val createdAt: String?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "is_private")
    val isPrivate: Boolean?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "status")
    val status: Int?,
    @Json(name = "updated_at")
    val updatedAt: String?,
    @Json(name = "user_id")
    val userId: Int?
)
package com.example.h5traveloto_booking.main.presentation.data.dto.Favorite


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddCollectionDTO(
    @Json(name = "cover")
    val cover: Cover,
    @Json(name = "is_private")
    val isPrivate: Boolean,
    @Json(name = "name")
    val name: String
)
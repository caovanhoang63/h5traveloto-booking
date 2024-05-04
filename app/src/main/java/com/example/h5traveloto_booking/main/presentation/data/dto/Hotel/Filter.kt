package com.example.h5traveloto_booking.main.presentation.data.dto.Hotel


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Filter(
    @Json(name = "distance")
    val distance: Int?,
    @Json(name = "district_code")
    val districtCode: String?,
    @Json(name = "hotel_type")
    val hotelType: Any?,
    @Json(name = "lat")
    val lat: Int?,
    @Json(name = "lng")
    val lng: Int?,
    @Json(name = "owner_id")
    val ownerId: Int?,
    @Json(name = "province_code")
    val provinceCode: String?,
    @Json(name = "ward_Code")
    val wardCode: String?
)
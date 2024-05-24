package com.example.h5traveloto_booking.main.presentation.data.dto.Booking

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CreateBookingDTO (
    @Json(name = "hotel_id")
    val hotelId: String,
    @Json(name = "room_type_id")
    val roomTypeId: String,
    @Json(name = "room_quantity")
    val roomQuantity: Int,
    @Json(name = "adults")
    val adults: Int,
    @Json(name = "children")
    val children: Int,
    @Json(name = "start_date")
    val startDate: String,
    @Json(name = "end_date")
    val endDate: String,
    val price : Int? = 0,
)

data class CreateBookingDTO2 (
    val hotelId: String? =null,
    val roomTypeId: String? =null,
    val roomQuantity: Int? =null,
    val adults: Int? =null,
    val children: Int? =null,
    val startDate: String? =null,
    val endDate: String? =null,
){
    fun toMap(): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
        if (hotelId != null) map["hotel_id"] = hotelId
        if (roomTypeId != null) map["room_type_id"] = roomTypeId
        if (roomQuantity != null) map["room_quantity"] = roomQuantity
        if (adults != null) map["adults"] = adults
        if (children != null) map["children"] = children
        if (startDate != null) map["start_date"] = startDate
        if (endDate != null) map["end_date"] = endDate
        return map
    }
}
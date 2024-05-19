package com.example.h5traveloto_booking.main.presentation.data.dto.Booking

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class UserBookingDTO (
    @Json(name = "id")
    val id: String,
    @Json(name = "status")
    val status: Int,
    @Json(name = "created_at")
    val createAt: String,
    @Json(name = "updated_at")
    val updateAt: String,
    @Json(name = "hotel")
    val hotel: HotelDTO,
    @Json(name = "user_id")
    val userId: String,
    @Json(name = "room_type_id")
    val roomTypeId: String,
    @Json(name = "customer_quantity")
    val customerQuantity: String,
    @Json(name = "start_date")
    val startDate: String,
    @Json(name = "end_date")
    val endDate: String
)
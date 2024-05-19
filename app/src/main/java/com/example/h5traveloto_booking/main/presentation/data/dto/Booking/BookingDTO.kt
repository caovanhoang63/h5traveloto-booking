package com.example.h5traveloto_booking.main.presentation.data.dto.Booking

import com.squareup.moshi.JsonClass
import kotlinx.datetime.LocalDate

data class BookingDTO(
    val id: String,
//    val status: Int,
//    val create_at: String,
//    val update_at: String,
//    val hotel_id: String,
//    val use_id: String,
//    val room_type_id: String,
//    val room_quantity: Int?,
//    val customer_quantity: Int?,
    val start_date: LocalDate,
    val end_date: LocalDate
)

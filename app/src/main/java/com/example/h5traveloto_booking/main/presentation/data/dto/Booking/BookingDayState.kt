package com.example.h5traveloto_booking.main.presentation.data.dto.Booking

import kotlinx.datetime.LocalDate

data class BookingDayState(
    val date: LocalDate,
    var booking_id: MutableList<String> = mutableListOf<String>(),
    var is_StartDate: Boolean = false,
    var is_MiddleDate: Boolean = false,
    var is_EndDate: Boolean = false,
    var is_FullDate: Boolean = false
)

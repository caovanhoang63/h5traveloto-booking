package com.example.h5traveloto_booking.main.presentation.domain.repository

import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.ListUserBookingDTO

interface BookingRepository {
    suspend fun getUserBookings (userID: String): ListUserBookingDTO
}
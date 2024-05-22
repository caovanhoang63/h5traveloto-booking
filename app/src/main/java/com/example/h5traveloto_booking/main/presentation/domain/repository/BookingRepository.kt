package com.example.h5traveloto_booking.main.presentation.domain.repository

import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.CreateBookingDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.IdRespondDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.ListUserBookingDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.ListUserBookingParams

interface BookingRepository {
    suspend fun getUserBookings (userID: String, listUserBookingParams: ListUserBookingParams): ListUserBookingDTO
    suspend fun createBooking (createBookingData: CreateBookingDTO): IdRespondDTO
}
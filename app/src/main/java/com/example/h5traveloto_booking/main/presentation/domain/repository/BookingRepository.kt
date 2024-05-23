package com.example.h5traveloto_booking.main.presentation.domain.repository

import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.*

interface BookingRepository {
    suspend fun getUserBookings (userID: String, listUserBookingParams: ListUserBookingParams): ListUserBookingDTO
    suspend fun createBooking (createBookingData: CreateBookingDTO): IdRespondDTO
    suspend fun getBooking(bookingId: String): BookingResponse
}
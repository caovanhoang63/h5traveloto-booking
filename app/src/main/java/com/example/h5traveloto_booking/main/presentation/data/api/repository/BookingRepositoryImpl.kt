package com.example.h5traveloto_booking.main.presentation.data.api.repository

import com.example.h5traveloto_booking.main.presentation.data.api.Booking.BookingApi
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.ListUserBookingDTO
import com.example.h5traveloto_booking.main.presentation.domain.repository.BookingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookingRepositoryImpl @Inject constructor (
    private val api: BookingApi
) : BookingRepository {
    override suspend fun getUserBookings(userID: String): ListUserBookingDTO {
        return withContext(Dispatchers.Default) {
            api.getListUserBooking(userID)
        }
    }
}
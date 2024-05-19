package com.example.h5traveloto_booking.main.presentation.domain.usecases

import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.ListUserBookingDTO
import com.example.h5traveloto_booking.main.presentation.domain.repository.BookingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ListUserBookingUseCase @Inject constructor(
    private val repository: BookingRepository
) {
    suspend operator fun invoke(userId: String):
            Flow<ListUserBookingDTO> = flow {
                emit(repository.getUserBookings(userId))
    }
}
package com.example.h5traveloto_booking.main.presentation.domain.usecases


import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.CreateBookingDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.IdRespondDTO
import com.example.h5traveloto_booking.main.presentation.domain.repository.BookingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class BookingUseCase @Inject constructor(
    private val repository: BookingRepository
) {
    suspend operator fun invoke(createBookingDTO: CreateBookingDTO) : Flow<IdRespondDTO> = flow{
        emit(repository.createBooking(createBookingDTO))
    }
}
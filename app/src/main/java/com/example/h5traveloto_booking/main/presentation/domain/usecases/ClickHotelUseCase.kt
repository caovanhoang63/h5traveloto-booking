package com.example.h5traveloto_booking.main.presentation.domain.usecases

import com.example.h5traveloto_booking.main.presentation.data.dto.Hotel.ClickHotelDTO
import com.example.h5traveloto_booking.main.presentation.domain.repository.HotelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ClickHotelUseCase @Inject constructor(
    private val repository: HotelRepository
)   {
    suspend operator fun invoke(token: String, hotelId: String): Flow<ClickHotelDTO> = flow{
        emit(repository.ClickHotel(token, hotelId))
    }
}
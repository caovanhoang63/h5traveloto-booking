package com.example.h5traveloto_booking.main.presentation.domain.usecases

import com.example.h5traveloto_booking.auth.data.dto.LoginRequestDTO
import com.example.h5traveloto_booking.auth.data.dto.LoginResponseDTO
import com.example.h5traveloto_booking.auth.domain.repository.AuthenticateRepository
import com.example.h5traveloto_booking.main.presentation.data.dto.Hotel.ListHotelDTO
import com.example.h5traveloto_booking.main.presentation.domain.repository.HotelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ListHotelUseCase @Inject constructor(
    private val repository: HotelRepository
) {
    suspend operator fun invoke(token: String) : Flow<ListHotelDTO> = flow {
        emit(repository.ListHotel(token))
    }
}



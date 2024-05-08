package com.example.h5traveloto_booking.details.presentation.domain.usecases

import com.example.h5traveloto_booking.details.presentation.data.dto.hotelDetails.HotelDetailsDTO
import com.example.h5traveloto_booking.details.presentation.domain.repository.HotelDetailsRepository
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ChangePasswordDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ChangePasswordResponse
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow
import javax.inject.Inject

data class HotelDetailsUseCase @Inject constructor(
    private val repository: HotelDetailsRepository
) {
    suspend operator fun invoke(hotelid: String):
            kotlinx.coroutines.flow.Flow<HotelDetailsDTO> = flow {
        emit(repository.getHotelDetails(hotelid))
    }
}
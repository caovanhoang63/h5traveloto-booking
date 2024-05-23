package com.example.h5traveloto_booking.details.presentation.domain.usecases

import com.example.h5traveloto_booking.details.presentation.data.dto.HotelFacilitiesDetails.HotelFacilitiesDetailsDTO
import com.example.h5traveloto_booking.details.presentation.data.dto.hotelDetails.HotelDetailsDTO
import com.example.h5traveloto_booking.details.presentation.domain.repository.HotelDetailsRepository
import com.example.h5traveloto_booking.details.presentation.domain.repository.HotelFacilitiesDetailsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HotelFacilitiesDetailsUseCase @Inject constructor(
    private val repository: HotelFacilitiesDetailsRepository
) {
    suspend operator fun invoke(hotelid: String):
            kotlinx.coroutines.flow.Flow<HotelFacilitiesDetailsDTO> = flow {
        emit(repository.getHotelFacilitiesDetails(hotelid))
    }
}
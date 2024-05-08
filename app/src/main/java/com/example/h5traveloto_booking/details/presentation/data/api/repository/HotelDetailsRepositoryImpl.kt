package com.example.h5traveloto_booking.details.presentation.data.api.repository

import com.example.h5traveloto_booking.details.presentation.data.api.hotelDetails.HotelDetailsApi
import com.example.h5traveloto_booking.details.presentation.data.dto.hotelDetails.HotelDetailsDTO
import com.example.h5traveloto_booking.details.presentation.domain.repository.HotelDetailsRepository
import com.example.h5traveloto_booking.main.presentation.domain.repository.HotelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HotelDetailsRepositoryImpl @Inject constructor(
    private val api: HotelDetailsApi
) : HotelDetailsRepository {
    override suspend fun getHotelDetails(hotelId: String): HotelDetailsDTO {
        return withContext(Dispatchers.Default) {
            api.getHotelDetails(hotelId)
        }
    }
}
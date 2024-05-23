package com.example.h5traveloto_booking.details.presentation.data.api.repository

import com.example.h5traveloto_booking.details.presentation.data.api.HotelFacilitiesDetails.HotelFacilitiesDetailsApi
import com.example.h5traveloto_booking.details.presentation.data.api.reviews.ListReviewsApi
import com.example.h5traveloto_booking.details.presentation.data.dto.HotelFacilitiesDetails.HotelFacilitiesDetailsDTO
import com.example.h5traveloto_booking.details.presentation.data.dto.reviews.ListReviewsDTO
import com.example.h5traveloto_booking.details.presentation.domain.repository.HotelFacilitiesDetailsRepository
import com.example.h5traveloto_booking.details.presentation.domain.repository.ListReviewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HotelFacilitiesDetailsRepositoryImpl @Inject constructor(
    private val api: HotelFacilitiesDetailsApi
): HotelFacilitiesDetailsRepository {
    override suspend fun getHotelFacilitiesDetails(hotelId: String): HotelFacilitiesDetailsDTO {
        return withContext(Dispatchers.Default) {
            api.getHotelFacilitiesDetails(hotelId)
        }
    }
}
package com.example.h5traveloto_booking.details.presentation.domain.usecases

import com.example.h5traveloto_booking.details.presentation.data.dto.hotelDetails.HotelDetailsDTO
import com.example.h5traveloto_booking.details.presentation.data.dto.reviews.ListReviewsDTO
import com.example.h5traveloto_booking.details.presentation.domain.repository.ListReviewsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

data class ListReviewsUseCase @Inject constructor(
    private val repository: ListReviewsRepository
) {
    suspend operator fun invoke(hotelid: String):
            kotlinx.coroutines.flow.Flow<ListReviewsDTO> = flow {
        emit(repository.getListReviews(hotelid))
    }
}
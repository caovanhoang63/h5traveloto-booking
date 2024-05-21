package com.example.h5traveloto_booking.details.presentation.data.api.repository

import com.example.h5traveloto_booking.details.presentation.data.api.reviews.ListReviewsApi
import com.example.h5traveloto_booking.details.presentation.data.dto.reviews.ListReviewsDTO
import com.example.h5traveloto_booking.details.presentation.domain.repository.ListReviewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListReviewsRepositoryImpl @Inject constructor(
    private val api: ListReviewsApi
): ListReviewsRepository {
    override suspend fun getListReviews(hotelId: String): ListReviewsDTO {
        return withContext(Dispatchers.Default) {
            api.getListReviews(hotelId)
        }
    }
}
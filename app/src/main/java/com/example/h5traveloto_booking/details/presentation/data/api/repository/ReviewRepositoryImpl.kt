package com.example.h5traveloto_booking.details.presentation.data.api.repository

import androidx.compose.foundation.text.selection.DisableSelection
import com.example.h5traveloto_booking.details.presentation.data.api.reviews.ReviewApi
import com.example.h5traveloto_booking.details.presentation.data.dto.reviews.CreateReviewDTO
import com.example.h5traveloto_booking.details.presentation.domain.repository.ReviewRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val api: ReviewApi
): ReviewRepository {
    override suspend fun createReview(data: CreateReviewDTO) {
        return withContext(Dispatchers.Default) {
            api.createReview(data)
        }
    }
}
package com.example.h5traveloto_booking.details.presentation.domain.usecases

import com.example.h5traveloto_booking.details.presentation.data.dto.reviews.CreateReviewDTO
import com.example.h5traveloto_booking.details.presentation.domain.repository.ReviewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReviewUseCase @Inject constructor(
    private val repository: ReviewRepository
) {
    suspend operator fun invoke(data: CreateReviewDTO) = flow {
        emit(repository.createReview(data))
    }
}
package com.example.h5traveloto_booking.details.presentation.domain.repository

import com.example.h5traveloto_booking.details.presentation.data.dto.reviews.CreateReviewDTO

interface ReviewRepository {
    suspend fun createReview(data: CreateReviewDTO)
}
package com.example.h5traveloto_booking.details.presentation.data.api.reviews

import com.example.h5traveloto_booking.details.presentation.data.dto.reviews.CreateReviewDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface ReviewApi {
    @POST("/v1/reviews")
    suspend fun createReview(
        @Body review: CreateReviewDTO
    )
}
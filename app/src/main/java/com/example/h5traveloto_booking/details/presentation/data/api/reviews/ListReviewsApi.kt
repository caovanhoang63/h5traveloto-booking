package com.example.h5traveloto_booking.details.presentation.data.api.reviews

import com.example.h5traveloto_booking.details.presentation.data.dto.reviews.ListReviewsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ListReviewsApi {
    @GET("/v1/reviews")
    suspend fun getListReviews(
        @Query("hotel_id") hotelId: String,
    ): ListReviewsDTO
}
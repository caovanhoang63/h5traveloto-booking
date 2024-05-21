package com.example.h5traveloto_booking.details.presentation.domain.repository

import com.example.h5traveloto_booking.details.presentation.data.dto.hotelDetails.HotelDetailsDTO
import com.example.h5traveloto_booking.details.presentation.data.dto.reviews.ListReviewsDTO

interface ListReviewsRepository {
    suspend fun getListReviews( hotelId: String): ListReviewsDTO

}
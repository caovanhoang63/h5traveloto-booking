package com.example.h5traveloto_booking.details.presentation.domain.repository

import com.example.h5traveloto_booking.details.presentation.data.dto.hotelDetails.HotelDetailsDTO

interface HotelDetailsRepository {
    suspend fun getHotelDetails( hotelId: String): HotelDetailsDTO
}
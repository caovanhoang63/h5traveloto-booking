package com.example.h5traveloto_booking.main.presentation.domain.repository

import com.example.h5traveloto_booking.main.presentation.data.dto.Hotel.ListHotelDTO

interface HotelRepository {
    suspend fun ListHotel(token: String ) : ListHotelDTO

}
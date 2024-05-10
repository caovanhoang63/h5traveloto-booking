package com.example.h5traveloto_booking.chat.presentation.domain.repository

import com.example.h5traveloto_booking.chat.presentation.data.dto.ChatListDTO
import com.example.h5traveloto_booking.details.presentation.data.dto.hotelDetails.HotelDetailsDTO

interface ChatListRepository {
    suspend fun getChatList(hotelId: String, page: Int?, limit: Int?): ChatListDTO
}
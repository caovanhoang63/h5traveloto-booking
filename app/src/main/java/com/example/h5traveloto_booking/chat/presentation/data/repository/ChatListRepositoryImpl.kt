package com.example.h5traveloto_booking.chat.presentation.data.repository

import com.example.h5traveloto_booking.chat.presentation.data.api.ChatListApi
import com.example.h5traveloto_booking.chat.presentation.data.dto.ChatListDTO
import com.example.h5traveloto_booking.chat.presentation.domain.repository.ChatListRepository
import com.example.h5traveloto_booking.details.presentation.data.api.hotelDetails.HotelDetailsApi
import com.example.h5traveloto_booking.details.presentation.data.dto.hotelDetails.HotelDetailsDTO
import com.example.h5traveloto_booking.details.presentation.domain.repository.HotelDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChatListRepositoryImpl @Inject constructor(
    private val api: ChatListApi
) : ChatListRepository {
    override suspend fun getChatList(hotelId: String, page: Int?, limit: Int?): ChatListDTO {
        return withContext(Dispatchers.Default) {
            api.getChatList(hotelId, page, limit)
        }
    }
}
package com.example.h5traveloto_booking.chat.presentation.domain.repository

import com.example.h5traveloto_booking.chat.presentation.data.dto.ChatListDTO
import com.example.h5traveloto_booking.chat.presentation.data.dto.ChatRoom.ChatRoomDTO

interface ChatRoomRepository {
    suspend fun getChatRoom(hotelId: String): ChatRoomDTO

}
package com.example.h5traveloto_booking.chat.presentation.domain.repository

import com.example.h5traveloto_booking.chat.presentation.data.dto.AllChat.AllChatDTO
import com.example.h5traveloto_booking.chat.presentation.data.dto.ChatListDTO

interface AllChatRepository {
    suspend fun getAllChat(): AllChatDTO

}
package com.example.h5traveloto_booking.chat.presentation.data.repository

import com.example.h5traveloto_booking.chat.presentation.data.api.AllChatApi
import com.example.h5traveloto_booking.chat.presentation.data.api.ChatListApi
import com.example.h5traveloto_booking.chat.presentation.data.dto.AllChat.AllChatDTO
import com.example.h5traveloto_booking.chat.presentation.data.dto.ChatListDTO
import com.example.h5traveloto_booking.chat.presentation.domain.repository.AllChatRepository
import com.example.h5traveloto_booking.chat.presentation.domain.repository.ChatListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AllChatRepositoryImpl @Inject constructor(
    private val api: AllChatApi
) : AllChatRepository {
    override suspend fun getAllChat(): AllChatDTO {
        return withContext(Dispatchers.Default) {
            api.getAllChat()
        }
    }
}
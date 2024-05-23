package com.example.h5traveloto_booking.chat.presentation.data.repository

import com.example.h5traveloto_booking.chat.presentation.data.api.ChatListApi
import com.example.h5traveloto_booking.chat.presentation.data.api.ChatRoomApi
import com.example.h5traveloto_booking.chat.presentation.data.dto.ChatListDTO
import com.example.h5traveloto_booking.chat.presentation.data.dto.ChatRoom.ChatRoomDTO
import com.example.h5traveloto_booking.chat.presentation.domain.repository.ChatListRepository
import com.example.h5traveloto_booking.chat.presentation.domain.repository.ChatRoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChatRoomRepositoryImpl @Inject constructor(
    private val api: ChatRoomApi
) : ChatRoomRepository {
    override suspend fun getChatRoom(hotelId: String): ChatRoomDTO {
        return withContext(Dispatchers.Default) {
            api.getChatRoom(hotelId)
        }
    }
}
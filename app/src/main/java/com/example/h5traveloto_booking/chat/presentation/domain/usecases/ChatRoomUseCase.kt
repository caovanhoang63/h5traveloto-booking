package com.example.h5traveloto_booking.chat.presentation.domain.usecases

import com.example.h5traveloto_booking.chat.presentation.data.dto.ChatListDTO
import com.example.h5traveloto_booking.chat.presentation.data.dto.ChatRoom.ChatRoomDTO
import com.example.h5traveloto_booking.chat.presentation.domain.repository.ChatListRepository
import com.example.h5traveloto_booking.chat.presentation.domain.repository.ChatRoomRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChatRoomUseCase @Inject constructor(
    private val repository: ChatRoomRepository
) {
    suspend operator fun invoke(hotelid: String):
            kotlinx.coroutines.flow.Flow<ChatRoomDTO> = flow {
        emit(repository.getChatRoom(hotelid))
    }
}
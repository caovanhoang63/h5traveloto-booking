package com.example.h5traveloto_booking.chat.presentation.domain.usecases

import com.example.h5traveloto_booking.chat.presentation.data.dto.AllChat.AllChatDTO
import com.example.h5traveloto_booking.chat.presentation.data.dto.ChatListDTO
import com.example.h5traveloto_booking.chat.presentation.domain.repository.AllChatRepository
import com.example.h5traveloto_booking.chat.presentation.domain.repository.ChatListRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AllChatUseCase @Inject constructor(
    private val repository: AllChatRepository
) {
    suspend operator fun invoke():
            kotlinx.coroutines.flow.Flow<AllChatDTO> = flow {
        emit(repository.getAllChat())
    }
}
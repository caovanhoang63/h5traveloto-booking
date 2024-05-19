package com.example.h5traveloto_booking.chat.presentation.domain.usecases

import com.example.h5traveloto_booking.chat.presentation.data.dto.ChatListDTO
import com.example.h5traveloto_booking.chat.presentation.domain.repository.ChatListRepository
import com.example.h5traveloto_booking.details.presentation.data.dto.hotelDetails.HotelDetailsDTO
import com.example.h5traveloto_booking.details.presentation.domain.repository.HotelDetailsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChatListUseCase @Inject constructor(
    private val repository: ChatListRepository
) {
    suspend operator fun invoke(hotelid: String, page: Int?, limit: Int?):
            kotlinx.coroutines.flow.Flow<ChatListDTO> = flow {
        emit(repository.getChatList(hotelid, page, limit))
    }
}
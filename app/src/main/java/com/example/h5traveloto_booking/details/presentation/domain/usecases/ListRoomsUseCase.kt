package com.example.h5traveloto_booking.details.presentation.domain.usecases

import com.example.h5traveloto_booking.details.presentation.data.`class`.ListRoomsParams
import com.example.h5traveloto_booking.details.presentation.data.dto.hotelDetails.HotelDetailsDTO
import com.example.h5traveloto_booking.details.presentation.data.dto.listRooms.ListRoomDTO
import com.example.h5traveloto_booking.details.presentation.domain.repository.HotelDetailsRepository
import com.example.h5traveloto_booking.details.presentation.domain.repository.ListRoomsRepository
import com.example.h5traveloto_booking.main.presentation.data.dto.Hotel.ListHotelDTO
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

data class ListRoomsUseCase @Inject constructor(
    private val repository: ListRoomsRepository
) {
    suspend operator fun invoke(listRoomsParams: ListRoomsParams):
            kotlinx.coroutines.flow.Flow<ListRoomDTO> = flow {
        emit(repository.getListRooms(listRoomsParams))
    }
}
package com.example.h5traveloto_booking.details.presentation.domain.usecases

import com.example.h5traveloto_booking.details.presentation.data.`class`.ListRoomsParams
import com.example.h5traveloto_booking.details.presentation.data.dto.listRooms.ListRoomDTO
import com.example.h5traveloto_booking.details.presentation.data.dto.roomFacilitiesDetails.RoomFacilitiesDetailsDTO
import com.example.h5traveloto_booking.details.presentation.domain.repository.ListRoomsRepository
import com.example.h5traveloto_booking.details.presentation.domain.repository.RoomFacilitiesDetailsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RoomFacilitiesDetailsUseCase @Inject constructor(
    private val repository: RoomFacilitiesDetailsRepository
) {
    suspend operator fun invoke(roomTypeId: String):
            kotlinx.coroutines.flow.Flow<RoomFacilitiesDetailsDTO> = flow {
        emit(repository.getRoomFacilitiesDetails(roomTypeId))
    }
}
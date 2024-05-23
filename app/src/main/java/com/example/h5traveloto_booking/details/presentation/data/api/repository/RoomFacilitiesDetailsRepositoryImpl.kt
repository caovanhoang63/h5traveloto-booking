package com.example.h5traveloto_booking.details.presentation.data.api.repository

import com.example.h5traveloto_booking.details.presentation.data.api.RoomFacilitiesDetails.RoomFacilitiesDetailsApi
import com.example.h5traveloto_booking.details.presentation.data.api.listRooms.ListRoomsApi
import com.example.h5traveloto_booking.details.presentation.data.`class`.ListRoomsParams
import com.example.h5traveloto_booking.details.presentation.data.dto.listRooms.ListRoomDTO
import com.example.h5traveloto_booking.details.presentation.data.dto.roomFacilitiesDetails.RoomFacilitiesDetailsDTO
import com.example.h5traveloto_booking.details.presentation.domain.repository.ListRoomsRepository
import com.example.h5traveloto_booking.details.presentation.domain.repository.RoomFacilitiesDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomFacilitiesDetailsRepositoryImpl @Inject constructor(
    private val api: RoomFacilitiesDetailsApi
): RoomFacilitiesDetailsRepository {
    override suspend fun getRoomFacilitiesDetails(roomTypeId: String): RoomFacilitiesDetailsDTO {
        return withContext(Dispatchers.Default) {
            api.getRoomFacilitiesDetails(roomTypeId)
        }

    }
}
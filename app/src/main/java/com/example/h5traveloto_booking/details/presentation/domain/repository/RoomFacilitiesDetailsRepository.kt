package com.example.h5traveloto_booking.details.presentation.domain.repository

import com.example.h5traveloto_booking.details.presentation.data.`class`.ListRoomsParams
import com.example.h5traveloto_booking.details.presentation.data.dto.listRooms.ListRoomDTO
import com.example.h5traveloto_booking.details.presentation.data.dto.roomFacilitiesDetails.RoomFacilitiesDetailsDTO

interface RoomFacilitiesDetailsRepository {
    suspend fun getRoomFacilitiesDetails(roomTypeId:String): RoomFacilitiesDetailsDTO

}
package com.example.h5traveloto_booking.details.presentation.domain.repository

import com.example.h5traveloto_booking.details.presentation.data.`class`.ListRoomsParams
import com.example.h5traveloto_booking.details.presentation.data.dto.listRooms.ListRoomDTO

interface ListRoomsRepository {
    suspend fun getListRooms(listRoomsParams: ListRoomsParams): ListRoomDTO
}
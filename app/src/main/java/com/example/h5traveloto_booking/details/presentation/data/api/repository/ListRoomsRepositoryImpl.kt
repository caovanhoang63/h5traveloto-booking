package com.example.h5traveloto_booking.details.presentation.data.api.repository

import com.example.h5traveloto_booking.details.presentation.data.api.listRooms.ListRoomsApi
import com.example.h5traveloto_booking.details.presentation.data.`class`.ListRoomsParams
import com.example.h5traveloto_booking.details.presentation.data.dto.hotelDetails.HotelDetailsDTO
import com.example.h5traveloto_booking.details.presentation.data.dto.listRooms.ListRoomDTO
import com.example.h5traveloto_booking.details.presentation.domain.repository.ListRoomsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListRoomsRepositoryImpl @Inject constructor(
    private val api: ListRoomsApi
):ListRoomsRepository{
    override suspend fun getListRooms(listRoomsParams: ListRoomsParams): ListRoomDTO {
        return withContext(Dispatchers.Default) {
            api.getListRoomTypes(listRoomsParams.toMap())
        }

    }
}
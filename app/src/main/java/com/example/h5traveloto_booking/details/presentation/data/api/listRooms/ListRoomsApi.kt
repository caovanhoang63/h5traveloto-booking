package com.example.h5traveloto_booking.details.presentation.data.api.listRooms

import com.example.h5traveloto_booking.details.presentation.data.`class`.ListRoomsParams
import com.example.h5traveloto_booking.details.presentation.data.dto.listRooms.ListRoomDTO
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ListRoomsApi {
    @GET("/v1/room-types")
    suspend fun getListRoomTypes(
        @QueryMap listRoomsParams: Map<String, String>?
    ):ListRoomDTO
}
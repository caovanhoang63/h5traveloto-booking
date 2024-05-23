package com.example.h5traveloto_booking.details.presentation.domain.repository

import com.example.h5traveloto_booking.details.presentation.data.`class`.ListRoomsParams
import com.example.h5traveloto_booking.details.presentation.data.dto.HotelFacilitiesDetails.HotelFacilitiesDetailsDTO
import com.example.h5traveloto_booking.details.presentation.data.dto.listRooms.ListRoomDTO

interface HotelFacilitiesDetailsRepository {
    suspend fun getHotelFacilitiesDetails(hotelId:String): HotelFacilitiesDetailsDTO

}
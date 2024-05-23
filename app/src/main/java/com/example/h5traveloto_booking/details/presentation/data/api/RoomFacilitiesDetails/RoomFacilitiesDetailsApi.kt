package com.example.h5traveloto_booking.details.presentation.data.api.RoomFacilitiesDetails

import com.example.h5traveloto_booking.details.presentation.data.dto.hotelDetails.HotelDetailsDTO
import com.example.h5traveloto_booking.details.presentation.data.dto.reviews.ListReviewsDTO
import com.example.h5traveloto_booking.details.presentation.data.dto.roomFacilitiesDetails.RoomFacilitiesDetailsDTO
import com.example.h5traveloto_booking.details.presentation.data.dto.roomtypebyid.RoomTypeByIdDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RoomFacilitiesDetailsApi {
    @GET("/v1/room-types/{room-type-id}/facilities")
    suspend fun getRoomFacilitiesDetails(
        @Path("room-type-id") roomTypeId: String,
    ) : RoomFacilitiesDetailsDTO

    @GET("room-types/{roomTypeId}")
    suspend fun getRoomTypeById(
        @Path("roomTypeId") roomTypeId: String
    ) : RoomTypeByIdDTO
}
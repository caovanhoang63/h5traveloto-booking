package com.example.h5traveloto_booking.details.presentation.data.api.hotelDetails

import com.example.h5traveloto_booking.details.presentation.data.dto.hotelDetails.HotelDetailsDTO
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface HotelDetailsApi {
    @GET("/v1/hotels/{hotelId}/detail")
    suspend fun getHotelDetails(
        @Path("hotelId") hotelId: String,
    ) : HotelDetailsDTO
}
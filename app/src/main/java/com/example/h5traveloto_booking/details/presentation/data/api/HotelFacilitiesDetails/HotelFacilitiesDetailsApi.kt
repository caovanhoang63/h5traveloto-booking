package com.example.h5traveloto_booking.details.presentation.data.api.HotelFacilitiesDetails

import com.example.h5traveloto_booking.details.presentation.data.dto.HotelFacilitiesDetails.HotelFacilitiesDetailsDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface HotelFacilitiesDetailsApi {
    @GET("/v1/hotels/{hotel-id}/facilities")
    suspend fun getHotelFacilitiesDetails(
        @Path("hotel-id") hotelId: String,
    ) : HotelFacilitiesDetailsDTO
}
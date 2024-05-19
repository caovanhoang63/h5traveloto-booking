package com.example.h5traveloto_booking.main.presentation.data.api.Booking

import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.ListUserBookingDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface BookingApi {
    @GET("/v1/users/{userId}/bookings")
    suspend fun getListUserBooking (
        @Path("userId") userId: String
    ) : ListUserBookingDTO
}
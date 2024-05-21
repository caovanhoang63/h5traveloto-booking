package com.example.h5traveloto_booking.main.presentation.data.api.Booking

import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.CreateBookingDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.IdRespondDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.ListUserBookingDTO
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BookingApi {
    @GET("/v1/users/{userId}/bookings")
    suspend fun getListUserBooking (
        @Path("userId") userId: String
    ) : ListUserBookingDTO

    @POST("/v1/bookings/")
    suspend fun createBooking (
        @Body bookingData : CreateBookingDTO
    ) : IdRespondDTO
}
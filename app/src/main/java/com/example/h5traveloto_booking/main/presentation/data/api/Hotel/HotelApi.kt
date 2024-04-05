package com.example.h5traveloto_booking.main.presentation.data.api.Hotel

import com.example.h5traveloto_booking.main.presentation.data.dto.Hotel.ListHotelDTO
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface HotelApi {
    @GET("hotels/list")
    suspend fun ListHotel(
        @Header("Authorization") token : String
    ) : ListHotelDTO
}
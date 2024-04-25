package com.example.h5traveloto_booking.main.presentation.data.api.Hotel

import com.example.h5traveloto_booking.main.presentation.data.dto.Search.DistrictsDTO
import retrofit2.http.GET

interface SearchApi {
    @GET("provinces")
    suspend fun listDistricts() : DistrictsDTO

}
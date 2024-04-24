package com.example.h5traveloto_booking.main.presentation.data.api.Account

import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ProfileDTO
import retrofit2.http.GET
import retrofit2.http.Header

interface ProfileApi {
    @GET("users/profile")
    suspend fun GetProfile(
        @Header("Authorization") token: String
    ) : ProfileDTO
}
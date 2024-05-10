package com.example.h5traveloto_booking.main.presentation.data.api.Account

import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ProfileDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.UpdateProfileDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.UpdateProfileResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH

interface ProfileApi {
    @GET("users/profile")
    suspend fun GetProfile(
        @Header("Authorization") token: String
    ) : ProfileDTO

    @PATCH("users/profile")
    suspend fun UpdateProfile(
        @Header("Authorization") token: String,
        @Body updateProfile : UpdateProfileDTO
    ) : UpdateProfileResponse


}
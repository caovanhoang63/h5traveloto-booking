package com.example.h5traveloto_booking.main.presentation.data.api.Account

import android.view.PixelCopy.Request
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ProfileDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.UpdateProfileDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.UpdateProfileResponse
import okhttp3.RequestBody
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
        @Body updateProfile : RequestBody
    ) : UpdateProfileResponse


}
package com.example.h5traveloto_booking.auth.data.remote.api

import com.example.h5traveloto_booking.auth.data.dto.LoginRequestDTO
import com.example.h5traveloto_booking.auth.data.dto.LoginResponseDTO
import com.example.h5traveloto_booking.auth.data.dto.RefreshToken
import com.example.h5traveloto_booking.auth.data.dto.RefreshTokenDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticateApi {
    @POST("authenticate")
    suspend fun authenticate(
        @Body body:  LoginRequestDTO
    ): LoginResponseDTO


    @POST("renew-token")
    suspend fun reviewToken(
        @Body body : RefreshToken
    ) : LoginResponseDTO

    @POST("renew-token")
    suspend fun refreshToken(
        @Body body : RefreshToken
    ) : RefreshTokenDTO
}
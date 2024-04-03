package com.example.h5traveloto_booking.auth.data.remote.api

import com.example.h5traveloto_booking.auth.data.dto.LoginRequestDTO
import com.example.h5traveloto_booking.auth.data.dto.LoginResponseDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticateApi {


    @POST("authenticate")
    suspend fun authenticate(
        @Body body:  LoginRequestDTO
    ): LoginResponseDTO
}
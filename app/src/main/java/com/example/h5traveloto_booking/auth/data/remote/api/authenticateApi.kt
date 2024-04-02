package com.example.h5traveloto_booking.auth.data.remote.api

import com.example.h5traveloto_booking.auth.domain.models.AuthenticateRequest
import com.example.h5traveloto_booking.auth.domain.models.AuthenticateResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticateApi {
    @POST("/authenticate")
    suspend fun authenticate(@Body body: AuthenticateRequest): AuthenticateResponse
}
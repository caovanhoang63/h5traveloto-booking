package com.example.h5traveloto_booking.auth.data.remote.api

import com.example.h5traveloto_booking.auth.domain.models.RegisterRequest
import com.example.h5traveloto_booking.auth.domain.models.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApi {
    @POST("/register")
    suspend fun Register(@Body body: RegisterRequest): RegisterResponse
}
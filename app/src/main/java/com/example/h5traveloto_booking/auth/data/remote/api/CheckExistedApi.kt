package com.example.h5traveloto_booking.auth.data.remote.api

import com.example.h5traveloto_booking.auth.data.dto.ExistedCheckResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface CheckExistedApi {
    @GET("users/exists")
    suspend fun checkExisted(@Query("email") email: String): ExistedCheckResponseDTO
}
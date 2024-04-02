package com.example.h5traveloto_booking.auth.domain.repository

import com.example.h5traveloto_booking.auth.domain.models.AuthenticateRequest
import com.example.h5traveloto_booking.auth.domain.models.AuthenticateResponse

interface AuthenticateRepository {
    suspend fun authenticate(auth : AuthenticateRequest) : AuthenticateResponse
}
package com.example.h5traveloto_booking.auth.domain.repository

import com.example.h5traveloto_booking.auth.data.dto.LoginRequestDTO
import com.example.h5traveloto_booking.auth.data.dto.LoginResponseDTO

interface AuthenticateRepository {
    suspend fun authenticate(login : LoginRequestDTO): LoginResponseDTO
}
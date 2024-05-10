package com.example.h5traveloto_booking.auth.domain.repository

import com.example.h5traveloto_booking.auth.data.dto.LoginRequestDTO
import com.example.h5traveloto_booking.auth.data.dto.LoginResponseDTO
import com.example.h5traveloto_booking.auth.data.dto.RefreshToken
import com.example.h5traveloto_booking.auth.data.dto.RefreshTokenDTO

interface AuthenticateRepository {
    suspend fun authenticate(login : LoginRequestDTO): LoginResponseDTO
    suspend fun renewToken(token : RefreshToken) :LoginResponseDTO
    suspend fun refreshToken(token : RefreshToken) : RefreshTokenDTO
}
// api, database, local, cache
// input --> out 


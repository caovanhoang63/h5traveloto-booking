package com.example.h5traveloto_booking.auth.domain.repository

import com.example.h5traveloto_booking.auth.data.dto.SignUpRequestDTO
import com.example.h5traveloto_booking.auth.data.dto.SignUpResponseDTO
import com.example.h5traveloto_booking.auth.data.remote.api.response

interface RegisterRepository {
    suspend fun register(body : SignUpRequestDTO): SignUpResponseDTO
    suspend fun ping() : response
}



// server, cache, database

// Phong  , phong maytinh , phong ...
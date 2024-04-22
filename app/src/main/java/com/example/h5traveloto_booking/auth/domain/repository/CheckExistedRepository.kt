package com.example.h5traveloto_booking.auth.domain.repository

import com.example.h5traveloto_booking.auth.data.dto.ExistedCheckResponseDTO

interface CheckExistedRepository {
    suspend fun checkExisted(email: String): ExistedCheckResponseDTO
}
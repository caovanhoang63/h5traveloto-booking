package com.example.h5traveloto_booking.main.presentation.domain.repository

import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ProfileDTO

interface ProfileRepository {
    suspend fun GetProfile(token: String): ProfileDTO
}
package com.example.h5traveloto_booking.main.presentation.domain.repository

import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ProfileDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.UpdateProfileDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.UpdateProfileResponse

interface ProfileRepository {
    suspend fun GetProfile(token: String): ProfileDTO
    suspend fun UpdateProfile(token: String, data: UpdateProfileDTO): UpdateProfileResponse
}
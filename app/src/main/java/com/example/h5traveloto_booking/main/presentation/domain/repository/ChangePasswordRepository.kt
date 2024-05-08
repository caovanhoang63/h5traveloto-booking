package com.example.h5traveloto_booking.main.presentation.domain.repository

import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ChangePasswordDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ChangePasswordResponse

interface ChangePasswordRepository {
    suspend fun changePassword(token:String,password: ChangePasswordDTO) :ChangePasswordResponse
}
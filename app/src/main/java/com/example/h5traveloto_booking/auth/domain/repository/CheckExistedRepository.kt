package com.example.h5traveloto_booking.auth.domain.repository

import com.example.h5traveloto_booking.auth.data.dto.ExistedCheckResponseDTO
import com.example.h5traveloto_booking.auth.data.dto.forgotpassword.ChangePasswordDTO
import com.example.h5traveloto_booking.auth.data.dto.forgotpassword.ChangePasswordRequest
import com.example.h5traveloto_booking.auth.data.dto.forgotpassword.CheckPinDTO
import com.example.h5traveloto_booking.auth.data.dto.forgotpassword.SentMailDTO

interface CheckExistedRepository {
    suspend fun checkExisted(email: String): ExistedCheckResponseDTO
    suspend fun sendMail(email: String): SentMailDTO
    suspend fun checkPin(email: String, pin: String): CheckPinDTO
    suspend fun changePassword(email: String, changePasswordRequest: ChangePasswordRequest): ChangePasswordDTO
}
package com.example.h5traveloto_booking.main.presentation.data.api.Account

import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ChangePasswordDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ChangePasswordResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PATCH

interface ChangePasswordApi {
    @PATCH("users/change-password")
    suspend fun changePassword(
        @Header("Authorization") token: String,
        @Body changePassword: ChangePasswordDTO,
    ) : ChangePasswordResponse
}
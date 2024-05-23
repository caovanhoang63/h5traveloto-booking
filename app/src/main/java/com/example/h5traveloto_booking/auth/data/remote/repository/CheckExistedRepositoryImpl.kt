package com.example.h5traveloto_booking.auth.data.remote.repository

import com.example.h5traveloto_booking.auth.data.dto.ExistedCheckResponseDTO
import com.example.h5traveloto_booking.auth.data.dto.forgotpassword.ChangePasswordDTO
import com.example.h5traveloto_booking.auth.data.dto.forgotpassword.ChangePasswordRequest
import com.example.h5traveloto_booking.auth.data.dto.forgotpassword.CheckPinDTO
import com.example.h5traveloto_booking.auth.data.dto.forgotpassword.SentMailDTO
import com.example.h5traveloto_booking.auth.data.remote.api.CheckExistedApi
import com.example.h5traveloto_booking.auth.domain.repository.CheckExistedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CheckExistedRepositoryImpl @Inject constructor(
    private val api : CheckExistedApi
): CheckExistedRepository {
    override suspend fun checkExisted(email: String): ExistedCheckResponseDTO {
        return withContext(Dispatchers.Default) {
            api.checkExisted(email)
        }
    }

    override suspend fun sendMail(email: String): SentMailDTO {
        return withContext(Dispatchers.Default) {
            api.sendMail(email)
        }
    }

    override suspend fun checkPin(email: String, pin: String) : CheckPinDTO {
        return withContext(Dispatchers.Default) {
            api.checkPin(email, pin)
        }
    }

    override suspend fun changePassword(email: String, changePasswordRequest: ChangePasswordRequest): ChangePasswordDTO {
        return withContext(Dispatchers.Default) {
            api.changePassword(email, changePasswordRequest)
        }
    }
}
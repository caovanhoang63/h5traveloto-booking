package com.example.h5traveloto_booking.main.presentation.data.api.repository

import com.example.h5traveloto_booking.main.presentation.data.api.Account.ChangePasswordApi
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ChangePasswordDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ChangePasswordResponse
import com.example.h5traveloto_booking.main.presentation.domain.repository.ChangePasswordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChangePasswordRepositoryImpl @Inject constructor(
    private val api: ChangePasswordApi
): ChangePasswordRepository {
    override suspend fun changePassword(token: String, password: ChangePasswordDTO): ChangePasswordResponse {
        return withContext(Dispatchers.Default){
            api.changePassword(token, password)
        }
    }
}
package com.example.h5traveloto_booking.auth.data.remote.repository

import com.example.h5traveloto_booking.auth.data.dto.ExistedCheckResponseDTO
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
}
package com.example.h5traveloto_booking.auth.domain.use_case

import com.example.h5traveloto_booking.auth.data.dto.LoginRequestDTO
import com.example.h5traveloto_booking.auth.data.dto.LoginResponseDTO
import com.example.h5traveloto_booking.auth.data.dto.RefreshToken
import com.example.h5traveloto_booking.auth.domain.repository.AuthenticateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RenewTokenUseCase @Inject constructor(
    private val repository: AuthenticateRepository
) {
    suspend operator fun invoke(body : RefreshToken) : Flow<LoginResponseDTO> = flow {
        emit(repository.renewToken(token = body))
    }
}
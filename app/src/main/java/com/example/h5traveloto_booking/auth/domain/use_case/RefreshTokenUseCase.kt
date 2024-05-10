package com.example.h5traveloto_booking.auth.domain.use_case

import com.example.h5traveloto_booking.auth.data.dto.RefreshToken
import com.example.h5traveloto_booking.auth.data.dto.RefreshTokenDTO
import com.example.h5traveloto_booking.auth.domain.repository.AuthenticateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RefreshTokenUseCase @Inject constructor(
    private val repository: AuthenticateRepository
){
    suspend operator fun invoke(body: RefreshToken) : Flow<RefreshTokenDTO> = flow{
        emit(repository.refreshToken(token = body))
    }
}
package com.example.h5traveloto_booking.auth.domain.use_case

import com.example.h5traveloto_booking.auth.data.dto.SignUpRequestDTO
import com.example.h5traveloto_booking.auth.data.dto.SignUpResponseDTO
import com.example.h5traveloto_booking.auth.domain.repository.RegisterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterUsesCase @Inject constructor(
    private val repository : RegisterRepository
) {
    suspend operator fun invoke(body : SignUpRequestDTO) : Flow<SignUpResponseDTO> = flow {
        emit(repository.register(body))
    }
}
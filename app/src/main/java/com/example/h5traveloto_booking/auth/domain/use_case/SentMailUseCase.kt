package com.example.h5traveloto_booking.auth.domain.use_case

import com.example.h5traveloto_booking.auth.data.dto.forgotpassword.SentMailDTO
import com.example.h5traveloto_booking.auth.domain.repository.CheckExistedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SentMailUseCase @Inject constructor(
    private val repository: CheckExistedRepository
) {
    suspend operator fun invoke(email: String): Flow<SentMailDTO> = flow {
        emit(repository.sendMail(email))
    }
}
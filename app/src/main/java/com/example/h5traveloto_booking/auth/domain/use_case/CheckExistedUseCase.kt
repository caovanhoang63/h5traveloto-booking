package com.example.h5traveloto_booking.auth.domain.use_case

import com.example.h5traveloto_booking.auth.data.dto.ExistedCheckResponseDTO
import com.example.h5traveloto_booking.auth.domain.repository.CheckExistedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

data class CheckExistedUseCase @Inject constructor(
    private val repository : CheckExistedRepository
){
    suspend operator fun invoke(email: String): Flow<ExistedCheckResponseDTO> = flow {
        emit(repository.checkExisted(email))
    }
}

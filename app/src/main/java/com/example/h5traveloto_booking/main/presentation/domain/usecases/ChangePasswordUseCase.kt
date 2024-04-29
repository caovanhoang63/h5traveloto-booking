package com.example.h5traveloto_booking.main.presentation.domain.usecases

import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ChangePasswordDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ChangePasswordResponse
import com.example.h5traveloto_booking.main.presentation.domain.repository.ChangePasswordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

data class ChangePasswordUseCase @Inject constructor(
    val repository: ChangePasswordRepository
){
    suspend operator fun invoke(token: String, password: ChangePasswordDTO) :
            Flow<ChangePasswordResponse> = flow {
                emit(repository.changePassword(token, password))
    }
}

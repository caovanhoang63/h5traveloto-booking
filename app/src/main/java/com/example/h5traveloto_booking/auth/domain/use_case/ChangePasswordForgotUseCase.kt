package com.example.h5traveloto_booking.auth.domain.use_case

import com.example.h5traveloto_booking.auth.data.dto.forgotpassword.ChangePasswordDTO
import com.example.h5traveloto_booking.auth.data.dto.forgotpassword.ChangePasswordRequest
import com.example.h5traveloto_booking.auth.domain.repository.CheckExistedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChangePasswordForgotUseCase @Inject constructor(
    private val repository : CheckExistedRepository
){
    suspend operator fun invoke(email: String, changePasswordRequest: ChangePasswordRequest) : Flow<ChangePasswordDTO> = flow {
        emit(repository.changePassword(email, changePasswordRequest))
    }
}
package com.example.h5traveloto_booking.main.presentation.domain.usecases

import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ProfileDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.UpdateProfileDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.UpdateProfileResponse
import com.example.h5traveloto_booking.main.presentation.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateProfileUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(token:String,data: UpdateProfileDTO): Flow<UpdateProfileResponse> = flow {
        emit(repository.UpdateProfile(token,data))
    }
}
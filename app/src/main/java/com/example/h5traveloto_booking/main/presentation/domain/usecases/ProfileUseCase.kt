package com.example.h5traveloto_booking.main.presentation.domain.usecases

import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ProfileDTO
import com.example.h5traveloto_booking.main.presentation.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(token:String): Flow<ProfileDTO> = flow {
        emit(repository.GetProfile(token))
    }
}
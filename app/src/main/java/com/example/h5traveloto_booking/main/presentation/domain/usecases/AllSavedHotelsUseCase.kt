package com.example.h5traveloto_booking.main.presentation.domain.usecases

import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.AllSavedHotelsDTO
import com.example.h5traveloto_booking.main.presentation.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

data class AllSavedHotelsUseCase @Inject constructor(
    val repository : FavoriteRepository
){
    suspend operator fun invoke() : Flow<AllSavedHotelsDTO> = flow {
        emit(repository.getAllSavedHotels())
    }
}

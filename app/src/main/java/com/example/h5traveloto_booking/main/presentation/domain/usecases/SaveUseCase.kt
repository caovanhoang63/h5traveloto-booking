package com.example.h5traveloto_booking.main.presentation.domain.usecases

import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.Response
import com.example.h5traveloto_booking.main.presentation.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

data class SaveUseCase @Inject constructor(
    val repository: FavoriteRepository
){
    suspend operator fun invoke(hotelId: String): Flow<Response> = flow {
        emit(repository.save(hotelId = hotelId))
    }
}
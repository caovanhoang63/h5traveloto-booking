package com.example.h5traveloto_booking.main.presentation.domain.usecases

import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.AllSavedHotelsDTO
import com.example.h5traveloto_booking.main.presentation.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class GetHotelsByCollectionIdUseCase(
    val repository : FavoriteRepository
) {
    suspend operator fun invoke(CollectionId:String): Flow<AllSavedHotelsDTO> = flow {
        emit(repository.getAllHotelsByCollectionId(CollectionId))
    }
}

package com.example.h5traveloto_booking.main.presentation.domain.usecases

import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.CollectionDTO
import com.example.h5traveloto_booking.main.presentation.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

data class CollectionUseCase @Inject constructor(
    val repository: FavoriteRepository
){
    suspend operator fun invoke(id : String) : Flow<CollectionDTO> = flow {
        emit(repository.getCollectionById(id))
    }
}

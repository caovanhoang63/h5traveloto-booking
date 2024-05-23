package com.example.h5traveloto_booking.main.presentation.domain.usecases

import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.Collection
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.Response
import com.example.h5traveloto_booking.main.presentation.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


data class GetCollectionByCollectionIdUseCase @Inject constructor(
    val repository: FavoriteRepository
){
    suspend operator fun invoke(collectionId: String) : Flow<Collection> = flow{
        emit(repository.getCollectionByCollectionId(collectionId))
    }
}

package com.example.h5traveloto_booking.main.presentation.data.api.repository

import com.example.h5traveloto_booking.main.presentation.data.api.Favorite.FavoriteApi
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.AllSavedHotelsDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.CollectionDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.Response
import com.example.h5traveloto_booking.main.presentation.domain.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

class FavoriteRepositoryImpl @Inject constructor(
    private val api : FavoriteApi
): FavoriteRepository {
    override suspend fun getCollectionById(id: String): CollectionDTO {
       return withContext(Dispatchers.Default){
           api.getCollectionById(id)
       }
    }

    override suspend fun getAllSavedHotels(): AllSavedHotelsDTO {
        return withContext(Dispatchers.Default){
            api.getAllSavedHotels()
        }
    }

    override suspend fun unSaveHotel(id: String): Response {
        return withContext(Dispatchers.Default){
            api.unSaveHotel(id)
        }
    }

    override suspend fun getAllHotelsByCollectionId(collectionId: String): AllSavedHotelsDTO {
        return withContext(Dispatchers.Default){
            api.getAllHotelsByCollectionId(collectionId)
        }
    }

    override suspend fun deleteHotel(collectionId: String, hotelId: String): Response {
        return withContext(Dispatchers.Default){
            api.deleteHotel(collectionId, hotelId)
        }
    }

    override suspend fun addHotel(collectionId: String, hotelId: String): Response {
        return withContext(Dispatchers.Default){
            api.addHotel(collectionId, hotelId)
        }
    }
}
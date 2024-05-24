package com.example.h5traveloto_booking.main.presentation.data.api.repository

import com.example.h5traveloto_booking.main.presentation.data.api.Favorite.FavoriteApi
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.*
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.Collection
import com.example.h5traveloto_booking.main.presentation.domain.repository.FavoriteRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
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

    override suspend fun createCollection(data: AddCollectionDTO): CreateResponse {
        return withContext(Dispatchers.Default){
            val nonNullProperties = data.copy(
                cover = data.cover.takeIf { it != null },
                isPrivate = data.isPrivate.takeIf { it != null },
                name = data.name.takeIf { it != null },
            )
            val requestBody = Gson().toJson(nonNullProperties.toMap())
                .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
            api.createCollection(requestBody)
        }
    }

    override suspend fun deleteCollection(collectionId: String): Response {
        return withContext(Dispatchers.Default){
            api.deteleCollection(collectionId)
        }
    }

    override suspend fun updateCollection(collectionId: String,data: AddCollectionDTO): Response {
        return withContext(Dispatchers.Default){
            val nonNullProperties = data.copy(
                cover = data.cover.takeIf { it != null },
                isPrivate = data.isPrivate.takeIf { it != null },
                name = data.name.takeIf { it != null },
            )
            val requestBody = Gson().toJson(nonNullProperties.toMap())
                .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
            api.updateCollection(collectionId = collectionId,requestBody)
        }
    }

    override suspend fun getCollectionByCollectionId(id: String): Collection {
        return withContext(Dispatchers.Default){
            api.getCollectionByCollectionId(id)
        }
    }

    override suspend fun isSaved(hotelId: String): Response {
        return withContext(Dispatchers.Default){
            api.isSaved(hotelId)
        }
    }

    override suspend fun save(hotelId: String): Response {
        return withContext(Dispatchers.Default){
            api.save(hotelId)
        }
    }
}
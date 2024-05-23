package com.example.h5traveloto_booking.main.presentation.domain.repository

import com.example.h5traveloto_booking.auth.domain.models.User
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.*
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.Collection

interface FavoriteRepository {
    suspend fun getCollectionById(id: String): CollectionDTO
    suspend fun getAllSavedHotels() :AllSavedHotelsDTO
    suspend fun unSaveHotel(id: String): Response
    suspend fun getAllHotelsByCollectionId(collectionId: String): AllSavedHotelsDTO
    suspend fun deleteHotel(collectionId: String,hotelId: String):Response
    suspend fun addHotel(collectionId: String,hotelId: String):Response
    suspend fun createCollection(data:AddCollectionDTO):CreateResponse
    suspend fun deleteCollection(collectionId: String):Response
    suspend fun updateCollection(collectionId: String,data: AddCollectionDTO):Response
    suspend fun getCollectionByCollectionId(id: String): Collection
}
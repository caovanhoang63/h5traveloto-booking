package com.example.h5traveloto_booking.main.presentation.domain.repository

import com.example.h5traveloto_booking.auth.domain.models.User
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.AllSavedHotelsDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.CollectionDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.Response

interface FavoriteRepository {
    suspend fun getCollectionById(id: String): CollectionDTO
    suspend fun getAllSavedHotels() :AllSavedHotelsDTO
    suspend fun unSaveHotel(id: String): Response
    suspend fun getAllHotelsByCollectionId(collectionId: String): AllSavedHotelsDTO
    suspend fun deleteHotel(collectionId: String,hotelId: String):Response
    suspend fun addHotel(collectionId: String,hotelId: String):Response
}
package com.example.h5traveloto_booking.main.presentation.data.api.Favorite

import android.database.Observable
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.AllSavedHotelsDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.Collection
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.CollectionDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.CreateResponse
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.Response
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface FavoriteApi {
    @GET("collections/")
    suspend fun getCollectionById(
        @Query("user_id") id: String
    ):CollectionDTO

    @GET("hotels/saved")
    suspend fun getAllSavedHotels() : AllSavedHotelsDTO

    @DELETE("hotels/{hotel-id}/unsave")
    suspend fun unSaveHotel(
        @Path("hotel-id") hotelId: String
    ): Response

    @GET("collections/{collection-id}/hotels")
    suspend fun getAllHotelsByCollectionId(
        @Path("collection-id") collectionId: String
    ) : AllSavedHotelsDTO

    @DELETE("collections/{collection-id}/hotels/{hotel-id}")
    suspend fun deleteHotel(
        @Path("collection-id") collectionId: String,
        @Path("hotel-id") hotelId: String
    ):Response

    @POST("collections/{collection-id}/hotels/{hotel-id}")
    suspend fun addHotel(
        @Path("collection-id") collectionId: String,
        @Path("hotel-id") hotelId: String,
    ):Response

    @POST("collections")
    suspend fun createCollection(
        @Body createCollection:RequestBody
    ) :CreateResponse

    @DELETE("collections/{collection-id}")
    suspend fun deteleCollection(
        @Path("collection-id") collectionId: String
    ) : Response

    @PATCH("collections/{collection-id}")
    suspend fun updateCollection(
        @Path("collection-id") collectionId: String,
        @Body updateCollection:RequestBody
    ) :Response

    @GET("collections/{collection-id}")
    suspend fun getCollectionByCollectionId(
        @Path("collection-id") collectionId: String
    ): Collection
}
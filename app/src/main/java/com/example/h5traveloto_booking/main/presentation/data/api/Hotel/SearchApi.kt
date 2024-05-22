package com.example.h5traveloto_booking.main.presentation.data.api.Hotel

import com.example.h5traveloto_booking.main.presentation.data.dto.Search.DistrictsDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Search.SuggestionsDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchHotel.SearchHotelDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchRoomType.SearchRoomTypeDTO
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface SearchApi {
    @GET("provinces")
    suspend fun listDistricts() : DistrictsDTO

    @GET("search/suggestions")
    suspend fun searchSuggestions(
        @Query("limit") limit: Int,
        @Query("search_text") searchText: String,
    ) : SuggestionsDTO

    @GET("search/hotels")
    suspend fun searchHotels(
        @QueryMap params: Map<String, String>?,
    ) : SearchHotelDTO

    @GET("search/room-types")
    suspend fun searchRoomTypes(
        @QueryMap params: Map<String, String>?,
    ) : SearchRoomTypeDTO

    @GET("search/hotels/prominent")
    suspend fun searchProminentHotels(
        @Query("limit") limit: Int,
    ) : SearchHotelDTO
}
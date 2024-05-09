package com.example.h5traveloto_booking.main.presentation.data.api.Hotel

import com.example.h5traveloto_booking.main.presentation.data.dto.Search.DistrictsDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Search.SuggestionsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("provinces")
    suspend fun listDistricts() : DistrictsDTO

    @GET("search/suggestions")
    suspend fun searchSuggestions(
        @Query("limit") limit: Int,
        @Query("search_text") searchText: String,
    ) : SuggestionsDTO
}
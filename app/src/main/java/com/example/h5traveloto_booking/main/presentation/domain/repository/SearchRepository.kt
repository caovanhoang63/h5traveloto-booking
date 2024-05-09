package com.example.h5traveloto_booking.main.presentation.domain.repository

import com.example.h5traveloto_booking.main.presentation.data.dto.Search.DistrictsDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Search.SuggestionsDTO

interface SearchRepository {
    suspend fun listDistricts() : DistrictsDTO
    suspend fun searchSuggestions(limit: Int, searchText: String) : SuggestionsDTO
}
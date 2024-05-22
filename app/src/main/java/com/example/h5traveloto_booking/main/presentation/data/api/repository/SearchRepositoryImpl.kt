package com.example.h5traveloto_booking.main.presentation.data.api.repository

import com.example.h5traveloto_booking.main.presentation.data.api.Hotel.SearchApi
import com.example.h5traveloto_booking.main.presentation.data.dto.Search.DistrictsDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Search.SuggestionsDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchHotel.SearchHotelDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchHotel.SearchHotelParams
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchRoomType.SearchRoomTypeDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchRoomType.SearchRoomTypeParams
import com.example.h5traveloto_booking.main.presentation.domain.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val api : SearchApi
) : SearchRepository {
    override suspend fun listDistricts(): DistrictsDTO {
        return withContext(Dispatchers.Default) {
            api.listDistricts()
        }
    }

    override suspend fun searchSuggestions(limit: Int, searchText: String): SuggestionsDTO {
        return withContext(Dispatchers.Default) {
            api.searchSuggestions(limit, searchText)
        }
    }

    override suspend fun searchHotels(params: SearchHotelParams): SearchHotelDTO {
        return withContext(Dispatchers.Default) {
            api.searchHotels(params.toMap())
        }
    }

    override suspend fun searchRoomTypes(params: SearchRoomTypeParams): SearchRoomTypeDTO {
        return withContext(Dispatchers.Default) {
            api.searchRoomTypes(params.toMap())
        }
    }

    override suspend fun searchProminentHotels(limit: Int): SearchHotelDTO {
        return withContext(Dispatchers.Default) {
            api.searchProminentHotels(limit)
        }
    }
}
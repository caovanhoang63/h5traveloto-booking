package com.example.h5traveloto_booking.main.presentation.data.api.repository

import com.example.h5traveloto_booking.main.presentation.data.api.Hotel.SearchApi
import com.example.h5traveloto_booking.main.presentation.data.dto.Search.DistrictsDTO
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
}
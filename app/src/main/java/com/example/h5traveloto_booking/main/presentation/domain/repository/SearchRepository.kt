package com.example.h5traveloto_booking.main.presentation.domain.repository

import com.example.h5traveloto_booking.main.presentation.data.dto.Search.DistrictsDTO

interface SearchRepository {
    suspend fun listDistricts() : DistrictsDTO
}
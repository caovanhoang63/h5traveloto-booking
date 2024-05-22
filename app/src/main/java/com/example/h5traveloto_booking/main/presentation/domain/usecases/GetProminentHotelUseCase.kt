package com.example.h5traveloto_booking.main.presentation.domain.usecases

import com.example.h5traveloto_booking.main.presentation.data.dto.SearchHotel.SearchHotelDTO
import com.example.h5traveloto_booking.main.presentation.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProminentHotelUseCase @Inject constructor(
    private val repository: SearchRepository
){
    suspend operator fun invoke(limit: Int): Flow<SearchHotelDTO> = flow {
        emit(repository.searchProminentHotels(limit))
    }
}
package com.example.h5traveloto_booking.main.presentation.domain.usecases

import com.example.h5traveloto_booking.main.presentation.data.dto.Search.DistrictsDTO
import com.example.h5traveloto_booking.main.presentation.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ListDistrictsUseCase @Inject constructor(
    private val repository: SearchRepository
){
    suspend operator fun invoke() : Flow<DistrictsDTO> = flow {
        emit(repository.listDistricts())
    }
}
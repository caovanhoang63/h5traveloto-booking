package com.example.h5traveloto_booking.main.presentation.domain.usecases

import com.example.h5traveloto_booking.main.presentation.data.dto.SearchRoomType.SearchRoomTypeDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchRoomType.SearchRoomTypeParams
import com.example.h5traveloto_booking.main.presentation.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRoomTypeUseCase @Inject constructor(
    private val repository: SearchRepository
){
    suspend operator fun invoke(params: SearchRoomTypeParams): Flow<SearchRoomTypeDTO> = flow {
        emit(repository.searchRoomTypes(params))
    }
}
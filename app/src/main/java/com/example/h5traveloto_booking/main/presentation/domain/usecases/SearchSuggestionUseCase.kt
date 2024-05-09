package com.example.h5traveloto_booking.main.presentation.domain.usecases

import com.example.h5traveloto_booking.main.presentation.data.dto.Search.SuggestionsDTO
import com.example.h5traveloto_booking.main.presentation.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchSuggestionUseCase @Inject constructor(
  private val repository: SearchRepository
){
    suspend operator fun invoke(limit: Int,searchText: String): Flow<SuggestionsDTO> = flow {
        emit(repository.searchSuggestions(limit, searchText))
    }
}
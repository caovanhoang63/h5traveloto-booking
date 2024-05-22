package com.example.h5traveloto_booking.main.presentation.domain.usecases

data class SearchUseCases (
    val listDistrictsUseCase: ListDistrictsUseCase,
    val searchSuggestionUseCase: SearchSuggestionUseCase,
    val searchHotelUseCase: SearchHotelUseCase,
    val searchRoomTypeUseCase: SearchRoomTypeUseCase,
    val getProminentHotelUseCase: GetProminentHotelUseCase
)
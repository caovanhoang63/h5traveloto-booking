package com.example.h5traveloto_booking.main.presentation.domain.usecases

data class FavoriteUseCases(
    val getCollectionUseCase : CollectionUseCase,
    val getAllSavedHotelsUseCase: AllSavedHotelsUseCase,
    val unsaveHotelUseCase: UnSaveHotelUseCase,
    val getHotelsByCollectionIdUseCase: GetHotelsByCollectionIdUseCase,
    val deleteHotelUseCase: DeleteHotelUseCase,
    val addHotelUseCase : AddHotelUseCase
)

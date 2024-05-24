package com.example.h5traveloto_booking.main.presentation.domain.usecases

data class FavoriteUseCases(
    val getCollectionUseCase : CollectionUseCase,
    val getAllSavedHotelsUseCase: AllSavedHotelsUseCase,
    val unsaveHotelUseCase: UnSaveHotelUseCase,
    val getHotelsByCollectionIdUseCase: GetHotelsByCollectionIdUseCase,
    val deleteHotelUseCase: DeleteHotelUseCase,
    val addHotelUseCase : AddHotelUseCase,
    val createCollectionUseCase : CreateCollectionUseCase,
    val deleteCollectionUseCase: DeleteCollectionUseCase,
    val updateCollectionUseCase : UpdateCollectionUseCase,
    val getCollectionByCollectionIdUseCase: GetCollectionByCollectionIdUseCase,
    val isSavedUseCase: IsSavedUseCase,
    val saveUseCase :SaveUseCase,
)

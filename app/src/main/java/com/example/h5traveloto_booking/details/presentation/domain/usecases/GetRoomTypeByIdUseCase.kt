package com.example.h5traveloto_booking.details.presentation.domain.usecases

import com.example.h5traveloto_booking.details.presentation.data.dto.roomtypebyid.RoomTypeByIdDTO
import com.example.h5traveloto_booking.details.presentation.domain.repository.RoomFacilitiesDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRoomTypeByIdUseCase @Inject constructor(
    private val roomFacilitiesDetailsRepository: RoomFacilitiesDetailsRepository
){
    suspend operator fun invoke(roomTypeId: String) : Flow<RoomTypeByIdDTO> = flow {
        emit(roomFacilitiesDetailsRepository.getRoomTypeById(roomTypeId))
    }
}
package com.example.h5traveloto_booking.main.presentation.domain.usecases

import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ChangePasswordDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ChangePasswordResponse
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.CreateBookingDTO2
import com.example.h5traveloto_booking.main.presentation.data.dto.Payment.CheckDealDTO
import com.example.h5traveloto_booking.main.presentation.domain.repository.ChangePasswordRepository
import com.example.h5traveloto_booking.main.presentation.domain.repository.PaymentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


data class CheckDealUseCase @Inject constructor(
    val repository: PaymentRepository
){
    suspend operator fun invoke(hotelId: String, data: CreateBookingDTO2) :
            Flow<CheckDealDTO> = flow {
        emit(repository.checkDeal(hotelId, data))
    }
}
package com.example.h5traveloto_booking.main.presentation.domain.usecases

import com.example.h5traveloto_booking.main.presentation.data.dto.Payment.LinkPaymentDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Payment.SuccessPaymentDTO
import com.example.h5traveloto_booking.main.presentation.domain.repository.PaymentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


data class SuccessPaymentUseCase(
    val repository: PaymentRepository
) {
    suspend operator fun invoke(bookingId:String): Flow<SuccessPaymentDTO> = flow {
        emit(repository.successPayment(bookingId))
    }
}
package com.example.h5traveloto_booking.main.presentation.domain.usecases

import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.Response
import com.example.h5traveloto_booking.main.presentation.domain.repository.PaymentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

data class CancelPaymentUseCase @Inject constructor(
    val repository: PaymentRepository
){
    suspend operator fun invoke(txnId:String): Flow<Response> = flow {
        emit(repository.cancelPayment(txnId))
    }
}
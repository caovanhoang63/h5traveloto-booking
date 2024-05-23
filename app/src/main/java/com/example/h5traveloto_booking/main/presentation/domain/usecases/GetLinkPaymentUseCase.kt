package com.example.h5traveloto_booking.main.presentation.domain.usecases

import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.Response
import com.example.h5traveloto_booking.main.presentation.data.dto.Payment.LinkPaymentDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Payment.QueryPaymentParams
import com.example.h5traveloto_booking.main.presentation.domain.repository.FavoriteRepository
import com.example.h5traveloto_booking.main.presentation.domain.repository.PaymentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class GetLinkPaymentUseCase(
    val repository: PaymentRepository
) {
    suspend operator fun invoke(bookingId:String,dealId:String?,currency:String): Flow<LinkPaymentDTO> = flow {
        emit(repository.getLinkPayment(bookingId = bookingId, dealId = dealId, currency = currency))
    }
}

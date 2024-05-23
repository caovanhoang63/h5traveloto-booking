package com.example.h5traveloto_booking.main.presentation.data.api.repository

import android.util.Log
import com.example.h5traveloto_booking.main.presentation.data.api.Payment.PaymentApi
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.Response
import com.example.h5traveloto_booking.main.presentation.data.dto.Payment.LinkPaymentDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Payment.QueryPaymentParams
import com.example.h5traveloto_booking.main.presentation.data.dto.Payment.SuccessPaymentDTO
import com.example.h5traveloto_booking.main.presentation.domain.repository.PaymentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    private val api : PaymentApi
): PaymentRepository {
    override suspend  fun getLinkPayment(bookingId:String,dealId:String?,currency:String): LinkPaymentDTO {
        return withContext(Dispatchers.Default){
            api.getLinkPayment(bookingId = "\"${bookingId}\"",dealId = dealId,currency = currency)
        }
    }

    override suspend fun cancelPayment(txnID: String): Response {
        return withContext(Dispatchers.Default){
            api.cancelPayment(txnID)
        }
    }

    override suspend fun executePayment(txnID: String): Response {
        return withContext(Dispatchers.Default){
            api.executePayment(txnID)
        }
    }

    override suspend fun successPayment(bookingId: String): SuccessPaymentDTO {
        return withContext(Dispatchers.Default){
            api.successPayment(bookingId)
        }
    }
}
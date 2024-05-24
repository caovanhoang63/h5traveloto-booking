package com.example.h5traveloto_booking.main.presentation.data.api.repository

import android.util.Log
import com.example.h5traveloto_booking.main.presentation.data.api.Payment.PaymentApi
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.CreateBookingDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.CreateBookingDTO2
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.Response
import com.example.h5traveloto_booking.main.presentation.data.dto.Payment.CheckDealDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Payment.LinkPaymentDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Payment.QueryPaymentParams
import com.example.h5traveloto_booking.main.presentation.data.dto.Payment.SuccessPaymentDTO
import com.example.h5traveloto_booking.main.presentation.domain.repository.PaymentRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
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

    override suspend fun checkDeal(dealId: String, data: CreateBookingDTO2): CheckDealDTO {
        return withContext(Dispatchers.Default){
            val nonNullProperties = data.copy(
                hotelId = data.hotelId.takeIf { it != null },
                roomTypeId = data.roomTypeId.takeIf { it != null },
                roomQuantity = data.roomQuantity.takeIf { it != null },
                adults = data.adults.takeIf { it != null },
                children = data.children.takeIf { it != null },
                startDate = data.startDate.takeIf { it != null },
                endDate = data.endDate.takeIf { it != null },
            )
            val requestBody = Gson().toJson(nonNullProperties.toMap())
                .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
            api.checkDeal(dealId,requestBody)
        }
    }
}
package com.example.h5traveloto_booking.main.presentation.domain.repository

import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.Response
import com.example.h5traveloto_booking.main.presentation.data.dto.Payment.LinkPaymentDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Payment.QueryPaymentParams
import com.example.h5traveloto_booking.main.presentation.data.dto.Payment.SuccessPaymentDTO

interface PaymentRepository {
    suspend fun getLinkPayment(bookingId:String,dealId:String?,currency:String) : LinkPaymentDTO
    suspend fun executePayment(txnID:String):Response
    suspend fun cancelPayment(txnID:String):Response
    suspend fun successPayment(bookingId:String):SuccessPaymentDTO
}
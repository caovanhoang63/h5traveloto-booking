package com.example.h5traveloto_booking.main.presentation.data.api.Payment

import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.Response
import com.example.h5traveloto_booking.main.presentation.data.dto.Payment.LinkPaymentDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Payment.SuccessPaymentDTO
import retrofit2.http.*

interface PaymentApi {
    @GET("payment/vnpay/pay-in")
    suspend fun getLinkPayment(
        @Query("booking_id") bookingId: String,
        @Query("deal_id") dealId: String?,
        @Query("currency") currency: String,
    ): LinkPaymentDTO

    @POST("payment/execute/{txn_id}")
    suspend fun executePayment(
        @Path("txn_id") txnId: String,
    ) : Response

    @POST("payment/cancel/{txn_id}")
    suspend fun cancelPayment(
        @Path("txn_id") txnId: String,
    ):Response

    @GET("payment/success/{booking_id}")
    suspend fun successPayment(
        @Path("booking_id") bookingId: String,
    ):SuccessPaymentDTO
}
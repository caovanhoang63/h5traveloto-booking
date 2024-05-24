package com.example.h5traveloto_booking.main.presentation.domain.usecases

data class PaymentUseCases(
    val getLinkPaymentUseCase : GetLinkPaymentUseCase,
    val executePaymentUseCase: ExecutePaymentUseCase,
    val cancelPaymentUseCase : CancelPaymentUseCase,
    val successPaymentUseCase : SuccessPaymentUseCase,
    val checkDealUseCase: CheckDealUseCase
)

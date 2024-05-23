package com.example.h5traveloto_booking.auth.domain.use_case

data class CheckExistedUseCases(
    val checkExistedUseCase: CheckExistedUseCase,
    val sentMailUseCase: SentMailUseCase,
    val checkPinUseCase: CheckPinUseCase,
    val changePasswordForgotUseCase: ChangePasswordForgotUseCase
)

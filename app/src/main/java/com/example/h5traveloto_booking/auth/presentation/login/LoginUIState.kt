package com.example.h5traveloto_booking.auth.presentation.login

data class LoginUIState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String = ""
)
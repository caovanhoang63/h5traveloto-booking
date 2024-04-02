package com.example.h5traveloto_booking.auth.data.remote.repository

import com.example.h5traveloto_booking.auth.data.remote.api.AuthenticateApi
import com.example.h5traveloto_booking.auth.domain.models.AuthenticateRequest
import com.example.h5traveloto_booking.auth.domain.models.AuthenticateResponse
import com.example.h5traveloto_booking.auth.domain.repository.AuthenticateRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticateRepositoryImpl @Inject constructor(
    private val authenticateApi: AuthenticateApi
) : AuthenticateRepository {

    override suspend fun authenticate(auth: AuthenticateRequest): AuthenticateResponse {
        return authenticateApi.authenticate(auth)
    }

}
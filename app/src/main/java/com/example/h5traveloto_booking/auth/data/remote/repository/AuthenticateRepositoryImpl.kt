package com.example.h5traveloto_booking.auth.data.remote.repository

import com.example.h5traveloto_booking.auth.data.dto.LoginRequestDTO
import com.example.h5traveloto_booking.auth.data.dto.LoginResponseDTO
import com.example.h5traveloto_booking.auth.data.dto.RefreshToken
import com.example.h5traveloto_booking.auth.data.dto.RefreshTokenDTO
import com.example.h5traveloto_booking.auth.data.remote.api.AuthenticateApi
import com.example.h5traveloto_booking.auth.domain.repository.AuthenticateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class AuthenticateRepositoryImpl @Inject constructor(
    private val api : AuthenticateApi
)  :  AuthenticateRepository {
    override suspend fun authenticate(login: LoginRequestDTO): LoginResponseDTO {
       return withContext(Dispatchers.Default) {
           api.authenticate(body = login)
       }
    }

    override suspend fun renewToken(token: RefreshToken): LoginResponseDTO {
        return withContext(Dispatchers.Default) {
            api.reviewToken(body = token)
        }
    }

    override suspend fun refreshToken(token: RefreshToken): RefreshTokenDTO {
        return withContext(Dispatchers.Default) {
            api.refreshToken(body = token)
        }
    }
}
package com.example.h5traveloto_booking.auth.data.remote.repository

import android.util.Log
import com.example.h5traveloto_booking.auth.data.dto.SignUpRequestDTO
import com.example.h5traveloto_booking.auth.data.dto.SignUpResponseDTO
import com.example.h5traveloto_booking.auth.data.remote.api.RegisterApi
import com.example.h5traveloto_booking.auth.data.remote.api.response
import com.example.h5traveloto_booking.auth.domain.repository.RegisterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val api : RegisterApi
)  : RegisterRepository {
    override suspend fun register(body: SignUpRequestDTO): SignUpResponseDTO {

        return withContext(Dispatchers.Default) {
            Log.d("api", "signup")
            Log.d("SignUp1",body.email)







            api.register(body)
        }
    }

    override suspend fun ping(): response {
        return withContext(Dispatchers.Default) {
            api.ping()
        }
    }
}
package com.example.h5traveloto_booking.main.presentation.data.api.repository

import com.example.h5traveloto_booking.main.presentation.data.api.Account.ProfileApi
import com.example.h5traveloto_booking.main.presentation.data.api.Hotel.HotelApi
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ProfileDTO
import com.example.h5traveloto_booking.main.presentation.domain.repository.HotelRepository
import com.example.h5traveloto_booking.main.presentation.domain.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val api : ProfileApi
)  : ProfileRepository {
    override suspend fun GetProfile(token : String ): ProfileDTO {
        return withContext(Dispatchers.Default) {
            api.GetProfile(token)
        }
    }

}
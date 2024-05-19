package com.example.h5traveloto_booking.main.presentation.data.api.repository

import com.example.h5traveloto_booking.main.presentation.data.api.Account.ProfileApi
import com.example.h5traveloto_booking.main.presentation.data.api.Hotel.HotelApi
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ProfileDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.UpdateProfileDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.UpdateProfileResponse
import com.example.h5traveloto_booking.main.presentation.domain.repository.HotelRepository
import com.example.h5traveloto_booking.main.presentation.domain.repository.ProfileRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val api : ProfileApi
)  : ProfileRepository {
    override suspend fun GetProfile(token : String ): ProfileDTO {
        return withContext(Dispatchers.Default) {
            api.GetProfile(token)
        }
    }
    override suspend fun UpdateProfile(token : String, data: UpdateProfileDTO) : UpdateProfileResponse {
        return withContext(Dispatchers.Default) {
            val nonNullProperties = data.copy(
                avatar = data.avatar.takeIf { it != null },
                dateOfBirth = data.dateOfBirth.takeIf { it != null },
                firstName = data.firstName.takeIf { it != null },
                gender = data.gender.takeIf { it != null },
                lastName = data.lastName.takeIf { it != null },
                phone = data.phone.takeIf { it != null }
            )
            val requestBody = Gson().toJson(nonNullProperties.toMap())
                .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
            api.UpdateProfile(token,requestBody)
        }
    }

}
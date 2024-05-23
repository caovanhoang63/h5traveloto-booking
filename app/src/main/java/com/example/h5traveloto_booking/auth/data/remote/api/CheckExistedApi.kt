package com.example.h5traveloto_booking.auth.data.remote.api

import com.example.h5traveloto_booking.auth.data.dto.ExistedCheckResponseDTO
import com.example.h5traveloto_booking.auth.data.dto.forgotpassword.ChangePasswordDTO
import com.example.h5traveloto_booking.auth.data.dto.forgotpassword.ChangePasswordRequest
import com.example.h5traveloto_booking.auth.data.dto.forgotpassword.CheckPinDTO
import com.example.h5traveloto_booking.auth.data.dto.forgotpassword.SentMailDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CheckExistedApi {
    @GET("users/exists")
    suspend fun checkExisted(@Query("email") email: String): ExistedCheckResponseDTO

    @POST("forgot-password/{email}")
    suspend fun sendMail(
        @Path("email") email: String
    ): SentMailDTO

    @GET("users/check-pin/{email}")
    suspend fun checkPin(
        @Path("email") email: String,
        @Query("pin") pin: String
    ): CheckPinDTO

    @PATCH("change-password-forgot/{email}")
    suspend fun changePassword(
        @Path("email") email: String,
        @Body changePasswordRequest: ChangePasswordRequest
    ): ChangePasswordDTO
}
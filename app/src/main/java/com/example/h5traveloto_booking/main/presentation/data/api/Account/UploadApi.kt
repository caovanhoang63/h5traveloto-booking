package com.example.h5traveloto_booking.main.presentation.data.api.Account

import com.example.h5traveloto_booking.main.presentation.data.dto.Account.AvatarDTO
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface UploadApi {

    @POST("upload")
    @Multipart
    suspend fun uploadFile(
        @Header("Authorization") authorization: String,
        @Part file: MultipartBody.Part,
        @Part("folder") folder: RequestBody,
    ): AvatarDTO
}
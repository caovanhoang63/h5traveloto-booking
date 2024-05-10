package com.example.h5traveloto_booking.main.presentation.data.api.repository

import android.util.Log
import com.example.h5traveloto_booking.main.presentation.data.api.Account.UploadApi
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.AvatarDTO
import com.example.h5traveloto_booking.main.presentation.domain.repository.UploadRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class UploadRepositoryImpl @Inject constructor(
    private val api: UploadApi
): UploadRepository {
    override suspend fun uploadFile(token: String, file: File, folder: String): AvatarDTO{
        val requestBody = file.asRequestBody(/*"multipart/form-data"*/"image/*".toMediaTypeOrNull())
        val multipartBody = MultipartBody.Part.createFormData("file", file.name, requestBody)
        //val folderPart = folder.toRequestBody("text/plain".toMediaTypeOrNull())
        val folderPart = RequestBody.create(MultipartBody.FORM,folder)
        return withContext(Dispatchers.Default){
            api.uploadFile("Bearer $token",multipartBody,folderPart)
        }
    }
}
package com.example.h5traveloto_booking.main.presentation.domain.repository

import com.example.h5traveloto_booking.main.presentation.data.dto.Account.Avatar
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.AvatarDTO
import java.io.File

interface UploadRepository {
    suspend fun uploadFile(token:String,file: File,folder:String): AvatarDTO
}
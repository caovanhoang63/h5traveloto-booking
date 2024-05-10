package com.example.h5traveloto_booking.main.presentation.domain.usecases

import com.example.h5traveloto_booking.main.presentation.data.dto.Account.AvatarDTO
import com.example.h5traveloto_booking.main.presentation.domain.repository.UploadRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import javax.inject.Inject

class UploadFileUseCase @Inject constructor(
    val repository: UploadRepository
) {
    suspend operator fun invoke(token:String,file: File,folder:String) : Flow<AvatarDTO> = flow{
        emit(repository.uploadFile(token,file,folder))
    }
}
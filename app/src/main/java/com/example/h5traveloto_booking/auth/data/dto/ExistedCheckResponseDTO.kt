package com.example.h5traveloto_booking.auth.data.dto

import com.squareup.moshi.Json

data class ExistedCheckResponseDTO(
    @Json(name = "data")
    val isExisted: Boolean? = null,
    @Json(name = "status_code")
    val statusCode: Int? = null,
    @Json(name = "message")
    val message: String? = null,
    @Json(name = "log")
    val log: String? = null,
    @Json(name = "error_key")
    val errorKey: String? = null
)

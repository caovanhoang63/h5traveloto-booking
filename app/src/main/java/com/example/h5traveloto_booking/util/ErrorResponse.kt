package com.example.h5traveloto_booking.util

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse (
    @Json(name = "message" )
    val message : String,
    @Json(name = "key")
    val key : String ,
    @Json(name = "log")
    val log : String,
)
package com.example.h5traveloto_booking.util

import com.squareup.moshi.Json

data class ErrorResponse (
    @Json(name = "message" )
    val message : String,
    @Json(name = "key")
    val key : String ,
    @Json(name = "log")
    val log : String,
)
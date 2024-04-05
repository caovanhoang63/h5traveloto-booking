package com.example.h5traveloto_booking.auth.data.remote.api

import com.example.h5traveloto_booking.auth.data.dto.SignUpRequestDTO
import com.example.h5traveloto_booking.auth.data.dto.SignUpResponseDTO
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface RegisterApi {
    @POST("register")
    suspend fun register(
        @Body body: SignUpRequestDTO
    ): SignUpResponseDTO

    @GET("ping")
    suspend fun ping() : response

}


// [] <nil>



// SignUpRequestDTO.email = email
//  ->>>>
// {"email" : "213123" , "asdasd" : "sadasd"}


@JsonClass(generateAdapter = true)
data class response(
    @Json(name = "message")
    val message : String
)
package com.example.h5traveloto_booking.main.presentation.data.api.AuthInterceptor

import com.example.h5traveloto_booking.util.SharedPrefManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val sharedPrefManager: SharedPrefManager): Interceptor{


    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

        val token = sharedPrefManager.getToken() ?: return chain.proceed(request.build())
        request.addHeader("Authorization", "Bearer $token")
        return chain.proceed(request.build())
    }
}
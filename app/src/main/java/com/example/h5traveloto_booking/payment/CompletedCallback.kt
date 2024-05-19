package com.example.h5traveloto_booking.payment

import java.io.Serializable

interface VNP_SdkCompletedCallback : Serializable {
    fun sdkAction(var1: String?)
}
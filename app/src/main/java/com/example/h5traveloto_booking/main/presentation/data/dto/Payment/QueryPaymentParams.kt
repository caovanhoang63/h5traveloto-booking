package com.example.h5traveloto_booking.main.presentation.data.dto.Payment

import android.util.Log

data class QueryPaymentParams(
    val bookingId: String?=null,
    val dealId: String?=null,
    val currency:String?=null,
){
    fun toMap(): Map<String,String>{
        val map = mutableMapOf<String,String>()
        if(bookingId != null) map["booking_id"] = "\"$bookingId\""
        if(dealId != null) map["deal_id"] = dealId
        if(currency != null) map["currency"] = currency
        Log.d("Booking View",map.toString())
        return map
    }
}

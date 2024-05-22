package com.example.h5traveloto_booking.main.presentation.data.dto.Booking

class ListUserBookingParams (
    val state: String? = null,
    val startDate: String? = null,
    val endDate: String? = null,
    val hotelId: String? = null,
    val payInHotel: Boolean? = null
) {
    fun toMap() : Map<String,String> {
        val map = mutableMapOf<String,String>()
        if (state != null) map["state"] = state
        if (startDate != null) map["start_date"] = startDate
        if (endDate != null) map["end_date"] = endDate
        if (hotelId != null) map["hotel_id"] = hotelId
        if (payInHotel != null) map["pay_in_hotel"] = payInHotel.toString()
        return map
    }
}
package com.example.h5traveloto_booking.details.presentation.data.`class`

data class ListRoomsParams(
    val hotel_id: String? = null,
    val bed: String? = null,
    val area: Double? = null,
    val max_price: Double? = null,
    val min_price: Double? = null,
    val free_cancel: Boolean? = null,
    val break_fast: Boolean? = null,
    val pay_in_hotel: Boolean? = null,
    val max_customer: Int? = null,
    val limit: Int? = null,
    val page: Int? = null,
    val cursor: String? = null
){
    fun toMap(): Map<String, String> {
        val map = mutableMapOf<String, String>()
        if (hotel_id != null) map["hotel_id"] = hotel_id
        if (bed != null) map["bed"] = bed
        if (area != null) map["area"] = area.toString()
        if (max_price != null) map["max_price"] = max_price.toString()
        if (min_price != null) map["min_price"] = min_price.toString()
        if (free_cancel != null) map["free_cancel"] = free_cancel.toString()
        if (break_fast != null) map["break_fast"] = break_fast.toString()
        if (pay_in_hotel != null) map["pay_in_hotel"] = pay_in_hotel.toString()
        if (max_customer != null) map["max_customer"] = max_customer.toString()
        if (limit != null) map["limit"] = limit.toString()
        if (page != null) map["page"] = page.toString()
        if (cursor != null) map["cursor"] = cursor
        return map
    }
}

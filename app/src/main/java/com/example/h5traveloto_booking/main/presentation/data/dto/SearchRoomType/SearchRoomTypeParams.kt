package com.example.h5traveloto_booking.main.presentation.data.dto.SearchRoomType

class SearchRoomTypeParams {
    var queryTime: Int? = null
    var startDate: String? = null
    var endDate: String? = null
    var hotelId: String? = null

    fun toMap(): Map<String, String> {
        val map = mutableMapOf<String, String>()
        queryTime?.let { map["query_time"] = it.toString() }
        startDate?.let { map["start_date"] = "\"${it}\"" }
        endDate?.let { map["end_date"] =  "\"${it}\"" }
        hotelId?.let { map["hotel_id"] =  "\"${it}\"" }
        return map
    }
}
package com.example.h5traveloto_booking.main.presentation.data.dto.SearchRoomType

class SearchRoomTypeParams {
    var queryTime: Int? = null
    var hotelId: String? = null
    var minPrice: Float? = null
    var maxPrice: Float? = null
    var roomQuantity: Int = 1
    var startDate: String? = null
    var endDate: String? = null
    var adults: Int = 1
    var children: Int = 0


    fun toMap(): Map<String, String> {
        val map = mutableMapOf<String, String>()
        queryTime?.let { map["query_time"] = it.toString() }
        hotelId?.let { map["hotel_id"] =  "\"${it}\"" }
        minPrice?.let { map["min_price"] = it.toString() }
        maxPrice?.let { map["max_price"] = it.toString() }
        roomQuantity.let { map["room_quantity"] = it.toString() }
        startDate?.let { map["start_date"] = "\"${it}\"" }
        endDate?.let { map["end_date"] =  "\"${it}\"" }
        adults.let { map["adults"] = it.toString() }
        children.let { map["children"] = it.toString() }
        return map
    }
}
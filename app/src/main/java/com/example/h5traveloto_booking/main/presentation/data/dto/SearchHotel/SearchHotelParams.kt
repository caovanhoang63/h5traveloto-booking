package com.example.h5traveloto_booking.main.presentation.data.dto.SearchHotel

class SearchHotelParams{
    var isCurrentLocation: Boolean = true
    var searchText: String?= null
    var searchTerm: String?= null
    var endDate: String?= null
    var startDate: String?= null
    var adults: Int?= null
    var id: String?= null
    var roomQuantity: Int?= null
    var children: Int?= null
    var star: Int?= null
    var lat: Double?= null
    var lng: Double?= null
    var listFacility: List<String>?= null
    var limit: Int?= null
    var page: Int?= null
    var cursor: String?= null
    var minPrice: String?= null
    var maxPrice: String?= null

    fun toMap(): Map<String,String>{
        val map = mutableMapOf<String,String>()

        if(searchText != null) map["search_text"] = searchText!! else map["search_text"] = ""
        if(searchTerm != null) map["search_term"] = searchTerm!!
        if(endDate != null) map["end_date"] = endDate!!
        if(startDate != null) map["start_date"] = startDate!!
        if(adults != null) map["adults"] = adults.toString()
        if(id != null) map["id"] = id!! else map["id"] = ""
        if(roomQuantity != null) map["room_quantity"] = roomQuantity.toString()
        if(children != null) map["children"] = children.toString()
        if(star != null) map["star"] = star.toString()
        if(lat != null) map["lat"] = lat.toString() else map["lat"] = ""
        if(lng != null) map["lng"] = lng.toString() else map["lng"] = ""
        if(listFacility != null) map["list_facility"] = listFacility.toString() else map["list_facility"] = "[]"
        if(limit != null) map["limit"] = limit.toString()
        if(page != null) map["page"] = page.toString()
        if(cursor != null) map["cursor"] = cursor!!
        if(minPrice != null) map["min_price"] = minPrice!!
        if(maxPrice != null) map["max_price"] = maxPrice!!

        return map
    }
}

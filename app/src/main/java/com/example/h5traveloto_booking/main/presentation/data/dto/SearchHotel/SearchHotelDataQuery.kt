package com.example.h5traveloto_booking.main.presentation.data.dto.SearchHotel

data class SearchHotelDataQuery(
    val searchText: String = "search_text",
    val searchTerm: String = "search_term",
    val endDate: String = "end_date",
    val startDate: String = "start_date",
    val adults: String = "adults",
    val id: String = "id",
    val roomQuantity: String = "room_quantity",
    val children: String = "children",
    val star: String = "star",
    val lat: String = "lat",
    val lng: String = "lng",
    val listFacility: String = "list_facility",
    val limit: String = "limit",
    val page: String = "page",
    val cursor: String = "cursor",
    val minPrice: String = "min_price",
    val maxPrice: String = "max_price",
)

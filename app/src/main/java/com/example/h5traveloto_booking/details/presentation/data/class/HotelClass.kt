package com.example.h5traveloto_booking.details.presentation.data.`class`

data class HotelClass(
    val id: String,
    val status: Int,
    val created_at: String,
    val updated_at: String,
    val name: String,
    val address: String,
    val hotel_type: Int,
    val hotline: String,
    val star: Int,
    val total_rating: Int,
    val total_room_type: Int,
    val location: Location,
    val province: Province,
    val district: District,
    val ward: Ward,
    val logo: Logo,
    val images: List<Image>,
    val display_price: Int
)

data class Location(
    val lat: Double,
    val lon: Double
)

data class Province(
    val code: String,
    val name: String,
    val full_name: String
)

data class District(
    val code: String,
    val name: String,
    val full_name: String
)

data class Ward(
    val code: String,
    val name: String,
    val full_name: String
)

data class Logo(
    val id: Int,
    val url: String,
    val width: Int,
    val height: Int,
    val cloud_name: String,
    val extension: String
)

data class Image(
    val id: Int,
    val url: String,
    val width: Int,
    val height: Int,
    val cloud_name: String,
    val extension: String
)
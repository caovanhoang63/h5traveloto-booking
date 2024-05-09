package com.example.h5traveloto_booking.details.presentation.data.dto.hotelDetails


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "additional_policies")
    val additionalPolicies: String,
    @Json(name = "cancellation_policy")
    val cancellationPolicy: Int,
    @Json(name = "check_in_time")
    val checkInTime: String,
    @Json(name = "check_out_time")
    val checkOutTime: String,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "distance_to_center_city")
    val distanceToCenterCity: Int,
    @Json(name = "hotel_id")
    val hotelId: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "location_detail")
    val locationDetail: String,
    @Json(name = "minimum_age")
    val minimumAge: Int,
    @Json(name = "number_of_floor")
    val numberOfFloor: Int,
    @Json(name = "pet_policy")
    val petPolicy: String,
    @Json(name = "require_document")
    val requireDocument: Boolean,
    @Json(name = "smoking_policy")
    val smokingPolicy: String,
    @Json(name = "status")
    val status: Int,
    @Json(name = "updated_at")
    val updatedAt: String
)
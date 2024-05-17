package com.example.h5traveloto_booking.main.presentation.data.dto.Search

import com.squareup.moshi.Json

data class SuggestionsDTO(
    @Json(name = "data")
    val suggestions: Suggestions
)

data class Suggestions(
    @Json(name = "hits")
    val hits: List<Suggestion>
)

data class Suggestion(
    @Json(name = "index")
    val index: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "full_name")
    val fullName: String?,
    @Json(name = "score")
    val score: Double,
    @Json(name = "location")
    val location: Location?,
    @Json(name = "province")
    val province: ProvinceSuggest?,
    @Json(name = "district")
    val district: DistrictSuggest?
)

data class Location(
    @Json(name = "lat")
    val lat: Double,
    @Json(name = "lon")
    val lon: Double
)

data class ProvinceSuggest(
    @Json(name = "code")
    val code: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "full_name")
    val fullName: String
)

data class DistrictSuggest(
    @Json(name = "code")
    val code: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "full_name")
    val fullName: String
)

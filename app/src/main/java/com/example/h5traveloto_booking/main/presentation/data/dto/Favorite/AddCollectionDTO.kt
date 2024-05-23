package com.example.h5traveloto_booking.main.presentation.data.dto.Favorite


import com.example.h5traveloto_booking.main.presentation.data.dto.Account.Avatar
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class AddCollectionDTO(
    val cover: Avatar?=null,
    val isPrivate: Boolean?=null,
    val name: String?=null
){
    fun toMap(): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
        if (cover != null) map["cover"] = cover
        if (isPrivate != null) map["is_private"] = isPrivate
        if (name != null) map["name"] = name
        return map
    }
}
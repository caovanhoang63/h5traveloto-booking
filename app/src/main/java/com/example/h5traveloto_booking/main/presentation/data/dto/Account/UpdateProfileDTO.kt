package com.example.h5traveloto_booking.main.presentation.data.dto.Account


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class UpdateProfileDTO(
    val avatar: Avatar? =null,
    val dateOfBirth: String?=null,
    val firstName: String?=null,
    val gender: String?=null,
    val lastName: String?=null,
    val phone: String?=null
){
    fun toMap(): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
        if (avatar != null) map["avatar"] = avatar
        if (dateOfBirth != null) map["date_of_birth"] = dateOfBirth
        if (firstName != null) map["first_name"] = firstName.toString()
        if (gender != null) map["gender"] = gender.toString()
        if (lastName != null) map["last_name"] = lastName.toString()
        if (phone != null) map["phone"] = phone.toString()
        return map
    }
}
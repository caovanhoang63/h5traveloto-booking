package com.example.h5traveloto_booking.share

import androidx.lifecycle.ViewModel
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.Avatar
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.Profile
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ProfileDTO
import com.squareup.moshi.Json
import javax.inject.Singleton


class UserViewModel : ViewModel() {
    var User = user()
}

data class user(
    var createdAt: String? =null,
    var dateOfBirth: String?=null,
    var email: String?=null,
    var firstName: String?=null,
    var gender: String?=null,
    var id: String?=null,
    var lastName: String?=null,
    var phone: String?=null,
    var role: String?=null,
    var status: Int?=null,
    var updatedAt: String?=null,
    var avatar: Avatar?=null
)
@Singleton
val UserShare = UserViewModel()


class TXNViewModel:ViewModel(){
    var TxnID :String = ""
}

@Singleton
val TxnShare = TXNViewModel()
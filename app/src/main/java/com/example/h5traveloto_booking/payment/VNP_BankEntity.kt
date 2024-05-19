package com.example.h5traveloto_booking.payment

import com.google.gson.annotations.SerializedName

class VNP_BankEntity {
    @SerializedName("bank_code")
    var bank_code: String? = null
    @SerializedName("ios_scheme")
    var ios_scheme: String? = null
    @SerializedName("andr_scheme")
    var andr_scheme: String? = null
}
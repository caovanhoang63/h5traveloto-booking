package com.example.h5traveloto_booking.share

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import javax.inject.Inject
import javax.inject.Singleton

class ShareHotelDataViewModel @Inject constructor(

): ViewModel(){
    private val _hotelId = MutableLiveData<Int?>()
    private val _hotelName = MutableLiveData<String?>()
    private val _hotelAddress = MutableLiveData<String?>()
    private val _hotelPrice = MutableLiveData<Int?>()


    fun setHotelData(hotelId: Int, hotelName: String, hotelAddress: String, hotelPrice: Int){
        _hotelId.value = hotelId
        _hotelName.value = hotelName
        _hotelAddress.value = hotelAddress
        _hotelPrice.value = hotelPrice
    }

    fun clearHotelData(){
        _hotelId.value = null
        _hotelName.value = null
        _hotelAddress.value = null
        _hotelPrice.value = null
    }

    fun logHotelData(){
        Log.d("ShareHotelDataViewModel", "Hotel ID: ${_hotelId.value}")
        Log.d("ShareHotelDataViewModel", "Hotel Name: ${_hotelName.value}")
        Log.d("ShareHotelDataViewModel", "Hotel Address: ${_hotelAddress.value}")
        Log.d("ShareHotelDataViewModel", "Hotel Price: ${_hotelPrice.value}")
    }
}

@Singleton
val shareHotelDataViewModel = ShareHotelDataViewModel()
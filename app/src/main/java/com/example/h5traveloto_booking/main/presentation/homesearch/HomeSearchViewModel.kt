package com.example.h5traveloto_booking.main.presentation.homesearch

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.h5traveloto_booking.main.presentation.data.dto.Search.Suggestion
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeSearchViewModel @Inject constructor(

): ViewModel(){
    var dataApiSearch = DataApiSearch(1,0,1,"","",true,
        Suggestion("","","","",0.0,null,null,null),
        0.0,0.0)

    fun setPersonEndRoom(adult: Int, child: Int, room: Int){
        dataApiSearch.adult = adult
        dataApiSearch.child = child
        dataApiSearch.room = room
    }

    fun setStartDateEndDate(startDate: String, endDate: String){
        dataApiSearch.startDate = startDate
        dataApiSearch.endDate = endDate
    }

    fun setIsCurrentLocation(isCurrentLocation: Boolean){
        dataApiSearch.isCurrentLocation = isCurrentLocation
    }

    fun getIsCurrentLocation(): Boolean{
        return dataApiSearch.isCurrentLocation
    }

    fun setLatLongCurrent(lat: Double, lng: Double){
        dataApiSearch.latCurrent = lat
        dataApiSearch.lngCurrent = lng
    }

    fun setLocation(location: Suggestion){
        dataApiSearch.location = location
    }

    fun bookingNow(){
        Log.d("HomeSearchViewModel", dataApiSearch.toString())
    }
}

data class DataApiSearch(
    var adult: Int,
    var child: Int,
    var room: Int,
    var endDate: String,
    var startDate: String,
    var isCurrentLocation: Boolean,
    var location: Suggestion,
    var latCurrent: Double,
    var lngCurrent: Double
)
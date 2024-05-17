package com.example.h5traveloto_booking.main.presentation.homesearch

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h5traveloto_booking.auth.data.remote.api.response
import com.example.h5traveloto_booking.main.presentation.data.dto.Search.Suggestion
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchHotel.SearchHotelDataQuery
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchHotel.SearchHotelParams
import com.example.h5traveloto_booking.main.presentation.domain.usecases.SearchUseCases
import com.example.h5traveloto_booking.share.shareHotelDataViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeSearchViewModel @Inject constructor(
    private val useCases: SearchUseCases
): ViewModel(){
    private var dataApiSearch = DataApiSearch(1,0,1,"","",true,
        Suggestion("","","","",0.0,null,null,null),
        0.0,0.0)
    private val searchHotelParams = SearchHotelParams()
    private val nameDataQuery = SearchHotelDataQuery()

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
        setSearchHotelDataQuery()
        Log.d("HomeSearchViewModel", searchHotelParams.toString())
        Log.d("HomeSearchViewModel", dataApiSearch.toString())
    }

    private fun setSearchHotelDataQuery(){
        shareHotelDataViewModel.setSearchHotelParams(dataApiSearch)
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
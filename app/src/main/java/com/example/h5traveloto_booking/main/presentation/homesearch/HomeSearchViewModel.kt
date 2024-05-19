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
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.utils.today
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import java.time.format.DateTimeFormatter
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

    private var startDate: LocalDate = LocalDate.today()
    private var endDate: LocalDate = LocalDate.today().plus(1, kotlinx.datetime.DateTimeUnit.DAY)

    fun setPersonEndRoom(adult: Int, child: Int, room: Int){
        dataApiSearch.adult = adult
        dataApiSearch.child = child
        dataApiSearch.room = room
    }

    private fun setStartDateEndDate(){
        val startDay = if(startDate.dayOfMonth < 10) "0${startDate.dayOfMonth}" else startDate.dayOfMonth.toString()
        val startMonth = if(startDate.monthNumber < 10) "0${startDate.monthNumber}" else startDate.monthNumber.toString()
        val startYear = startDate.year.toString()
        val endDay = if(endDate.dayOfMonth < 10) "0${endDate.dayOfMonth}" else endDate.dayOfMonth.toString()
        val endMonth = if(endDate.monthNumber < 10) "0${endDate.monthNumber}" else endDate.monthNumber.toString()
        val endYear = endDate.year.toString()
        dataApiSearch.startDate = "\"$startDay-$startMonth-$startYear\""
        dataApiSearch.endDate = "\"$endDay-$endMonth-$endYear\""
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
        setStartDateEndDate()
        setSearchHotelDataQuery()
        Log.d("HomeSearchViewModel", searchHotelParams.toString())
        Log.d("HomeSearchViewModel", dataApiSearch.toString())
    }

    private fun setSearchHotelDataQuery(){
        shareHotelDataViewModel.setSearchHotelParams(dataApiSearch)
    }

    fun getStartDate(): LocalDate{
       return startDate
    }

    fun getEndDate(): LocalDate{
        return endDate
    }

    fun setStartDateEndDate(startDate: LocalDate, endDate: LocalDate){
        this.startDate = startDate
        this.endDate = endDate
        setStartDateEndDate()
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
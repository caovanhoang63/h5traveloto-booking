package com.example.h5traveloto_booking.main.presentation.homesearch

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h5traveloto_booking.main.presentation.data.dto.Search.Suggestion
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchHotel.SearchHotelDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchHotel.SearchHotelDataQuery
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchHotel.SearchHotelParams
import com.example.h5traveloto_booking.main.presentation.domain.usecases.SearchUseCases
import com.example.h5traveloto_booking.share.shareDataHotelDetail
import com.example.h5traveloto_booking.share.shareHotelDataViewModel
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.utils.today
import com.example.h5traveloto_booking.util.ErrorResponse
import com.example.h5traveloto_booking.util.Result
import com.example.h5traveloto_booking.util.SharedPrefManager
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeSearchViewModel @Inject constructor(
    private val useCases: SearchUseCases,
    private val sharedPrefManager: SharedPrefManager
): ViewModel(){
    private var dataApiSearch = DataApiSearch(1,0,1,"","",true,
        Suggestion("","","","",0.0,null,null,null),
        0.0,0.0)
    private val searchHotelParams = SearchHotelParams()
    private var startDate: LocalDate = LocalDate.today()
    private var endDate: LocalDate = LocalDate.today().plus(1, kotlinx.datetime.DateTimeUnit.DAY)

    private val _listHotelViewed = MutableStateFlow<Result<SearchHotelDTO>>(Result.Idle)
    val ListHotelViewed = _listHotelViewed.asStateFlow()

    fun getHotelViewed() = viewModelScope.launch {
        val token = sharedPrefManager.getToken()
        val bearerToken = "Bearer $token"

        useCases.searchViewedHotelsUseCase(bearerToken).onStart {
            Log.d("Home Search ViewModel:", "Start")
            _listHotelViewed.value = Result.Loading
        }
        .catch {
            if(it is HttpException){
                Log.d("Home Search ViewModel:", "catch")
                //Log.d("ChangePassword ViewModel E", it.message.toString())
                Log.d("Home Search ViewModel:", "hehe")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("Home Search ViewModel:", errorResponse.message)
                _listHotelViewed.value = Result.Error(errorResponse.message)
            }
            else if (it is Exception) {
                Log.d("Home Search ViewModel:", it.javaClass.name)
            }
        }
        .collect { res ->
            Log.d("Home Search ViewModel:", "Success")
            _listHotelViewed.value = Result.Success(res)
        }
    }

    fun setPersonEndRoom(adult: Int, child: Int, room: Int){
        dataApiSearch.adult = adult
        dataApiSearch.child = child
        dataApiSearch.room = room
        // Data detail
        shareDataHotelDetail.setAdultsChildrenRoomQuantity(adult, child, room)
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
        shareHotelDataViewModel.setOnClickBooking(true)
        shareDataHotelDetail.LogData()
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

    fun checkDataViewed(): Boolean{
        if(_listHotelViewed.value is Result.Success){
            return (_listHotelViewed.value as Result.Success).data.data?.size != 0
        }
        return false
    }

    fun setStartDateEndDate(startDate: LocalDate, endDate: LocalDate){
        this.startDate = startDate
        this.endDate = endDate
        setStartDateEndDate()
        // Data detail
        shareDataHotelDetail.setStartDateEndDate(startDate, endDate)
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
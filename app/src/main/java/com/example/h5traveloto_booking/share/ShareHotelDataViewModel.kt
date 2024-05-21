package com.example.h5traveloto_booking.share

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchHotel.SearchHotelDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchHotel.SearchHotelParams
import com.example.h5traveloto_booking.main.presentation.homesearch.DataApiSearch
import javax.inject.Inject
import javax.inject.Singleton

class ShareHotelDataViewModel @Inject constructor(

): ViewModel(){
    private val searchHotelParams = SearchHotelParams()
    private var listHotel : SearchHotelDTO?= null

    fun checkExistedData(): Boolean{
        if(listHotel?.data?.size != 0){
            return true
        }else{
            return false
        }
    }

    fun setListHotel(listHotel: SearchHotelDTO){
        this.listHotel = listHotel
    }

    fun getListHotel(): SearchHotelDTO?{
        return listHotel
    }

    fun getSearchHotelParams(): SearchHotelParams{
        Log.d("List Hotel ViewModel", searchHotelParams.toMap().toString())
        return searchHotelParams
    }

    fun isCurrentLocation(): Boolean{
        return searchHotelParams.isCurrentLocation
    }

    fun setIsCurrentLocation(isCurrentLocation: Boolean){
        searchHotelParams.isCurrentLocation = isCurrentLocation
    }

    fun setSearchHotelParams(dataApiSearch: DataApiSearch){
        searchHotelParams.searchTerm = dataApiSearch.location.index
        searchHotelParams.startDate = dataApiSearch.startDate
        searchHotelParams.endDate = dataApiSearch.endDate
        searchHotelParams.adults = dataApiSearch.adult
        searchHotelParams.children = dataApiSearch.child
        searchHotelParams.roomQuantity = dataApiSearch.room
        if(dataApiSearch.isCurrentLocation){
            searchHotelParams.searchTerm = "location"
            searchHotelParams.lat = dataApiSearch.latCurrent
            searchHotelParams.lng = dataApiSearch.lngCurrent

            searchHotelParams.isCurrentLocation = true
        }
        else if(dataApiSearch.location.index == "provinces" || dataApiSearch.location.index == "districts" || dataApiSearch.location.index == "wards"){
            searchHotelParams.searchTerm = dataApiSearch.location.index.dropLast(1)
            searchHotelParams.id = dataApiSearch.location.id

            searchHotelParams.isCurrentLocation = false
        }else{
            searchHotelParams.searchTerm = "location"
            searchHotelParams.id = dataApiSearch.location.province?.code
            searchHotelParams.lat = dataApiSearch.location.location?.lat
            searchHotelParams.lng = dataApiSearch.location.location?.lon

            searchHotelParams.isCurrentLocation = false
        }
        Log.d("Share ViewModel", searchHotelParams.toMap().toString())
    }
    fun setCurrentLocation(lat: Double, lng: Double){
        searchHotelParams.lat = lat
        searchHotelParams.lng = lng
    }
    fun setId(id: String){
        searchHotelParams.id = id
    }
    fun logHotelParams(){
        Log.d("Share ViewModel", searchHotelParams.toMap().toString())
    }
    fun setSearchTerm(searchTerm: String){
        searchHotelParams.searchTerm = searchTerm
    }
}

@Singleton
val shareHotelDataViewModel = ShareHotelDataViewModel()
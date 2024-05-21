package com.example.h5traveloto_booking.account

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.h5traveloto_booking.main.presentation.data.dto.Hotel.ListHotelDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchHotel.Data
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchHotel.SearchHotelDTO
import com.example.h5traveloto_booking.main.presentation.domain.usecases.HotelUseCases
import com.example.h5traveloto_booking.main.presentation.domain.usecases.SearchUseCases
import com.example.h5traveloto_booking.share.shareHotelDataViewModel
import com.example.h5traveloto_booking.util.Result
import com.example.h5traveloto_booking.util.SharedPrefManager
import com.google.android.gms.location.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListHotelsViewModel @Inject constructor(
    private val useCases : HotelUseCases,
    private val sharedPrefManager: SharedPrefManager,
    private val useCaseSearch: SearchUseCases
) : ViewModel() {
    private val _listHotelResponse = MutableStateFlow<Result<ListHotelDTO>>(Result.Idle)
    val ListHotelResponse = _listHotelResponse.asStateFlow()
    private val _listHotelSearch = MutableStateFlow<Result<SearchHotelDTO>>(Result.Idle)
    val ListHotelSearch = _listHotelSearch.asStateFlow()

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            Log.d("List Hotel Location", "Location received")
            for (location in locationResult.locations) {
                Log.d("List Hotel Location", "Callback set Latitude: ${location.latitude}, Longitude: ${location.longitude}")
                shareHotelDataViewModel.setCurrentLocation(location.latitude, location.longitude)
                //shareHotelDataViewModel.setCurrentLocation(10.3547475, 107.0972445)

            }
            getHotelSearch()
            stopLocationUpdates()
        }
    }

    @SuppressLint("MissingPermission")
    public fun startLocationUpdates() {
        locationCallback?.let {
            val locationRequest = LocationRequest.Builder(
                Priority.PRIORITY_HIGH_ACCURACY, 100
            )
                .setWaitForAccurateLocation(true)
                .setMinUpdateIntervalMillis(3000)
                .setMaxUpdateDelayMillis(100)
                .build()
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, it, Looper.getMainLooper())
            Log.d("List Hotel Location", "Requesting location updates")
        }
    }

    public fun initLocationProvider(context: Context) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        Log.d("List Hotel Location", "Init location provider")
    }
    public fun stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    fun getListHotels(

    ) = viewModelScope.launch {
        val token = sharedPrefManager.getToken()
        Log.d("Account ViewModel", "Get token")
        Log.d("Account ViewModel Token", token.toString())
        val bearerToken = "Bearer $token"
        useCases.listHotelUseCase(bearerToken).onStart {
            _listHotelResponse.value = Result.Loading
            Log.d("Account ViewModel", "Loading")

        }.catch {

            Log.d("List Hotel ViewModel", "catch")
            Log.d("List Hotel ViewModel E", it.message.toString() )
            _listHotelResponse.value = Result.Error(it.message.toString())
        }.collect{
            Log.d("Success","Ok")
//            Log.d("Success",it.paging.total.toString())
            _listHotelResponse.value = Result.Success(it)
        }
    }

    fun getHotelSearch() = viewModelScope.launch {
        useCaseSearch.searchHotelUseCase(shareHotelDataViewModel.getSearchHotelParams()).onStart {
            Log.d("List Hotel ViewModel", "Loading")
            _listHotelSearch.value = Result.Loading
        }.catch {
            Log.d("List Hotel ViewModel", it.message.toString())
            _listHotelSearch.value = Result.Error(it.message.toString())
        }.collect {
            Log.d("List Hotel ViewModel", "Success")
            Log.d("List Hotel ViewModel", it.toString())
            _listHotelSearch.value = Result.Success(it)
        }
    }
    fun setStateHotelSearchLoading(){
        _listHotelSearch.value = Result.Loading
    }
    fun setStateHotelSearchError(){
        _listHotelSearch.value = Result.Error("Error")
    }
    fun setStateHotelSearchSuccess(data: SearchHotelDTO){
        _listHotelSearch.value = Result.Success(data)
    }
    fun sortHotelList(selectedOption: String) {
        viewModelScope.launch {
            _listHotelSearch.value.let { result ->
                when (result) {
                    is Result.Success -> {
                        val hotelList = result.data.data
                        val sortedHotelList = when (selectedOption) {
                            "Giá thấp đến cao" -> hotelList?.sortedBy { it.displayPrice }
                            "Giá cao đến thấp" -> hotelList?.sortedByDescending { it.displayPrice }
                            "Xếp hạng cao đến thấp" -> hotelList?.sortedByDescending { it.star }
                            "Xếp hạng thấp đến cao" -> hotelList?.sortedBy { it.star }
                            else -> hotelList
                        }
                        _listHotelSearch.value = Result.Success(result.data.copy(data = sortedHotelList))
                    }

                    else -> {
                        // Xử lý trường hợp Result.Error hoặc Result.Loading nếu cần
                    }
                }
            }
        }
    }
    private val _filteredHotelList = mutableStateOf<List<Data>?>(null)
    val filteredHotelList: State<List<Data>?> = _filteredHotelList
    fun filterHotelList(star: List<Int>) {
        viewModelScope.launch {
            _listHotelSearch.value?.let { result ->
                when (result) {
                    is Result.Success -> {
                        val hotelList = result.data.data
                        val filteredList = hotelList?.filter { star.contains(it.star) }
                        _filteredHotelList.value = filteredList
                    }
                    else -> {
                        // Xử lý trường hợp Result.Error hoặc Result.Loading nếu cần
                    }
                }
            }
        }
    }
}
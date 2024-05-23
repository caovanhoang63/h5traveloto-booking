package com.example.h5traveloto_booking.main.presentation.home
import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h5traveloto_booking.auth.data.remote.api.response
import com.example.h5traveloto_booking.main.presentation.data.dto.Hotel.ListHotelDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Search.District
import com.example.h5traveloto_booking.main.presentation.data.dto.Search.DistrictsDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchHotel.SearchHotelDTO
import com.example.h5traveloto_booking.util.Result

import com.example.h5traveloto_booking.main.presentation.domain.usecases.HotelUseCases
import com.example.h5traveloto_booking.main.presentation.domain.usecases.SearchUseCases
import com.example.h5traveloto_booking.main.presentation.map.LocationProvider
import com.example.h5traveloto_booking.share.shareHotelDataViewModel
import com.example.h5traveloto_booking.util.ErrorResponse
import com.example.h5traveloto_booking.util.SharedPrefManager
import com.google.android.gms.location.*
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases : HotelUseCases,
    private val useCaseLocations: SearchUseCases,
    private val sharedPrefManager: SharedPrefManager,
) :ViewModel (){
    private var context: Context? = null
    private val _listDistrict = MutableStateFlow<Result<DistrictsDTO>>(Result.Idle)
    val listDistrict = _listDistrict.asStateFlow()

    private val _listHotelSearch = MutableStateFlow<Result<SearchHotelDTO>>(Result.Idle)
    val ListHotelSearch = _listHotelSearch.asStateFlow()

    private val _listProminentHotel = MutableStateFlow<Result<SearchHotelDTO>>(Result.Idle)
    val ListProminentHotel = _listProminentHotel.asStateFlow()

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            Log.d("List Hotel Location", "Location received")
            for (location in locationResult.locations) {
                Log.d("List Hotel Location", "Callback set Latitude: ${location.latitude}, Longitude: ${location.longitude}")
                shareHotelDataViewModel.setCurrentLocation(location.latitude, location.longitude)
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

            setStateHotelSearchLoading()
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, it, Looper.getMainLooper())
            Log.d("List Hotel Location", "Requesting location updates")
        }
    }


    public fun initLocationProvider(context: Context) {
        this.context = context
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        Log.d("List Hotel Location", "Init location provider")
    }
    public fun stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    suspend fun getListDistricts() {
        useCaseLocations.listDistrictsUseCase().onStart {

        }
        .catch {
            Log.d("HomeViewModel", "getListDistricts: ${it.message}")
            val currentLocation = District("Vị trí gần tôi", "")
            _listDistrict.value = Result.Success(DistrictsDTO(listOf(currentLocation)))
        }
        .collect { response ->
            Log.d("HomeViewModel", "getListDistricts: ${response.districts}")
            val sorted = response.districts.sortedBy { it.name }
            val currentLocation = District("Vị trí gần tôi", "")
            val list = mutableListOf(currentLocation)
            list.addAll(sorted)
            _listDistrict.value = Result.Success(DistrictsDTO(list))
            shareHotelDataViewModel.logHotelParams()
        }
    }

    fun getHotelSearch() = viewModelScope.launch {
        useCaseLocations.searchHotelUseCase(shareHotelDataViewModel.getSearchHotelParams()).onStart {
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

    fun getProminentHotel() = viewModelScope.launch {
        useCaseLocations.getProminentHotelUseCase(10).onStart {
            Log.d("Home ViewModel Prominent", "Loading")
            _listProminentHotel.value = Result.Loading
        }.catch {
            Log.d("Home ViewModel Prominent", it.message.toString())
            _listProminentHotel.value = Result.Error(it.message.toString())
        }.collect {
            Log.d("Home ViewModel Prominent", "Success")
            Log.d("Home ViewModel Prominent", it.toString())
            _listProminentHotel.value = Result.Success(it)
        }
    }

    fun postClickHotel(hotelId: String) = viewModelScope.launch {
        val token = sharedPrefManager.getToken()
        val bearerToken = "Bearer $token"
        useCases.clickHotelUseCase(bearerToken, hotelId).onStart {
            Log.d("Home ViewModel Post Click", "Loading")
        }.catch {
            if(it is HttpException){
                Log.d("Home ViewModel Post Click", "catch")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("Home ViewModel Post Click", errorResponse.log)
                Log.d("Home ViewModel Post Click", errorResponse.message)
            }
            else if (it is Exception) {
                Log.d("Home ViewModel Post Click ex", it.javaClass.name)
                Log.d("Home ViewModel Post Click ex", it.toString())
            }
        }.collect {
            Log.d("Home ViewModel Post Click", "Success")
            Log.d("Home ViewModel Post Click", it.toString())
        }
    }

    fun setStateHotelSearchLoading(){
        _listHotelSearch.value = Result.Loading
    }
    fun setStateHotelSearchError(){
        _listHotelSearch.value = Result.Error("Error")
    }
    fun setStateHotelSearchIdle(){
        _listHotelSearch.value = Result.Idle
    }

    fun setStateProminentHotelLoading(){
        _listProminentHotel.value = Result.Loading
    }
    fun setStateProminentHotelError(){
        _listProminentHotel.value = Result.Error("Error")
    }
    fun setStateProminentHotelIdle(){
        _listProminentHotel.value = Result.Idle
    }

    fun checkData(): Boolean{
        if(_listHotelSearch.value is Result.Success){
            return (_listHotelSearch.value as Result.Success).data.data?.size != 0
        }
        return false
    }

    fun checkDataProminent(): Boolean{
        if(_listProminentHotel.value is Result.Success){
            return (_listProminentHotel.value as Result.Success).data.data?.size != 0
        }
        return false
    }
}

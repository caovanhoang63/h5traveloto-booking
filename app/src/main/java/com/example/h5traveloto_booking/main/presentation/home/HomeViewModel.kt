package com.example.h5traveloto_booking.main.presentation.home
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h5traveloto_booking.main.presentation.data.dto.Hotel.ListHotelDTO
import com.example.h5traveloto_booking.util.Result

import com.example.h5traveloto_booking.main.presentation.domain.usecases.HotelUseCases
import com.example.h5traveloto_booking.util.SharedPrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases : HotelUseCases,
    private val sharedPrefManager: SharedPrefManager
) :ViewModel (){
    private val _listHotelDataResponse = MutableStateFlow<Result<ListHotelDTO>>(Result.Idle)
    val listHotelDataResponse = _listHotelDataResponse.asStateFlow()

    fun getListHotel(

    ) = viewModelScope.launch {
        val token = sharedPrefManager.getToken()
        Log.d("Home ViewModel", "Get token")
        Log.d("Home ViewModel Token", token.toString())
        val bearerToken = "Bearer $token"
        useCases.listHotelUseCase(bearerToken).onStart {
            _listHotelDataResponse.value = Result.Loading
            Log.d("Home ViewModel", "Loading")

        }.catch {
            Log.d("Home ViewModel", "catch")
            Log.d("Home ViewModel E", it.message.toString() )
       //     _listHotelDataResponse.value = Result.Error(it)
            _listHotelDataResponse.value = Result.Error(it.message.toString())
        }.collect{

            Log.d("Success","Ok")
            Log.d("Success",it.data.size.toString())
            if (it == null ){
                Log.d("Success","NULL")
            }
//            Log.d("Success",it.paging.total.toString())
            _listHotelDataResponse.value = Result.Success(it)

        }
    }
}
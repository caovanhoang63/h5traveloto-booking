package com.example.h5traveloto_booking.account

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ProfileDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Hotel.ListHotelDTO
import com.example.h5traveloto_booking.main.presentation.domain.usecases.AccountUseCases
import com.example.h5traveloto_booking.main.presentation.domain.usecases.HotelUseCases
import com.example.h5traveloto_booking.util.Result
import com.example.h5traveloto_booking.util.SharedPrefManager
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
    private val sharedPrefManager: SharedPrefManager
) : ViewModel() {
    private val _listHotelResponse = MutableStateFlow<Result<ListHotelDTO>>(Result.Idle)
    val ListHotelResponse = _listHotelResponse.asStateFlow()

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
}
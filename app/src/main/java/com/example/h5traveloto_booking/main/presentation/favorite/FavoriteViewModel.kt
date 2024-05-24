package com.example.h5traveloto_booking.main.presentation.favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ProfileDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.CollectionDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.Response
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchHotel.SearchHotelDTO
import com.example.h5traveloto_booking.main.presentation.domain.usecases.CollectionUseCase
import com.example.h5traveloto_booking.main.presentation.domain.usecases.FavoriteUseCases
import com.example.h5traveloto_booking.main.presentation.domain.usecases.SearchUseCases
import com.example.h5traveloto_booking.share.UserShare
import com.example.h5traveloto_booking.util.ErrorResponse
import com.example.h5traveloto_booking.util.Result
import com.example.h5traveloto_booking.util.SharedPrefManager
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel@Inject constructor(
    private val usecases : FavoriteUseCases,
    private val searchUseCases: SearchUseCases,
    private val sharedPrefManager: SharedPrefManager
): ViewModel() {
    private val collectionDataResponse = MutableStateFlow<Result<CollectionDTO>>(Result.Idle)
    val CollectionDataResponse = collectionDataResponse.asStateFlow()

    private val _listHotelViewed = MutableStateFlow<Result<SearchHotelDTO>>(Result.Idle)
    val ListHotelViewed = _listHotelViewed.asStateFlow()

    fun getCollectionData() = viewModelScope.launch {

        usecases.getCollectionUseCase("\"${UserShare.User.id}\"").onStart {
            collectionDataResponse.value = Result.Loading
        }.catch {
            if(it is HttpException){
                Log.d("Favorite ViewModel", "catch")
                //Log.d("ChangePassword ViewModel E", it.message.toString())
                Log.d("Favorite ViewModel", "hehe")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("Favorite ViewModel Error", errorResponse.message)
                Log.d("Favorite ViewModel Error", errorResponse.log)
                collectionDataResponse.value = Result.Error(errorResponse.message)
            }
            else if (it is Exception) {
                Log.d("Favorite ViewModel", it.javaClass.name)
                collectionDataResponse.value = Result.Error("errorResponse.message")

            }
        }.collect{
            Log.d("Favorite ViewModel","Ok")
            Log.d("Success2",it.data.size.toString())
            Log.d("Success2",it.filter.userId)
            collectionDataResponse.value = Result.Success(it)
        }
    }

    fun getHotelViewed() = viewModelScope.launch {
        val token = sharedPrefManager.getToken()
        val bearerToken = "Bearer $token"

        searchUseCases.searchViewedHotelsUseCase(bearerToken).onStart {
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
                    _listHotelViewed.value = Result.Error("errorResponse.message")
                    Log.d("Home Search ViewModel:", it.javaClass.name)
                }
            }
            .collect { res ->
                Log.d("Home Search ViewModel:", "Success")
                _listHotelViewed.value = Result.Success(res)
            }
    }


    private val isSavedResponse = MutableStateFlow<Result<Response>>(Result.Idle)
    val IsSavedResponse = isSavedResponse.asStateFlow()
    fun isSaved(hotelId:String)= viewModelScope.launch {
        usecases.isSavedUseCase(hotelId).onStart {
            isSavedResponse.value = Result.Loading
        }.catch {
            if(it is HttpException){
                Log.d("Favorite ViewModel", "catch")
                //Log.d("ChangePassword ViewModel E", it.message.toString())
                Log.d("Favorite ViewModel", "hehe")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("Favorite ViewModel Error", errorResponse.message)
                Log.d("Favorite ViewModel Error", errorResponse.log)
                isSavedResponse.value = Result.Error(errorResponse.message)
            }
            else if (it is Exception) {
                Log.d("Favorite ViewModel", it.javaClass.name)
                isSavedResponse.value = Result.Error("errorResponse.message")

            }
        }.collect{
            isSavedResponse.value = Result.Success(it)
        }
    }



    private val saveResponse = MutableStateFlow<Result<Response>>(Result.Idle)
    val SaveResponse = saveResponse.asStateFlow()
    fun save(hotelId:String)= viewModelScope.launch {
        usecases.saveUseCase(hotelId).onStart {
            saveResponse.value = Result.Loading
        }.catch {
            if(it is HttpException){
                Log.d("Favorite ViewModel", "catch")
                //Log.d("ChangePassword ViewModel E", it.message.toString())
                Log.d("Favorite ViewModel", "hehe")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("Favorite ViewModel Error", errorResponse.message)
                Log.d("Favorite ViewModel Error", errorResponse.log)
                saveResponse.value = Result.Error(errorResponse.message)
            }
            else if (it is Exception) {
                Log.d("Favorite ViewModel", it.javaClass.name)
                saveResponse.value = Result.Error("errorResponse.message")

            }
        }.collect{
            saveResponse.value = Result.Success(it)
        }
    }
}
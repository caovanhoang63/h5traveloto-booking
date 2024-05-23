package com.example.h5traveloto_booking.details.presentation.bookingdetails

import android.util.Log
import androidx.core.graphics.rotationMatrix
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.BookingResponse
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.CreateBookingDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.IdRespondDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.UserBookingDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.Response
import com.example.h5traveloto_booking.main.presentation.data.dto.Payment.LinkPaymentDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Payment.QueryPaymentParams
import com.example.h5traveloto_booking.main.presentation.data.dto.Payment.SuccessPaymentDTO
import com.example.h5traveloto_booking.main.presentation.domain.usecases.BookingUseCases
import com.example.h5traveloto_booking.main.presentation.domain.usecases.PaymentUseCases
import com.example.h5traveloto_booking.share.TxnShare
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
class BookingScreenViewModel @Inject constructor (
    private val bookingUseCases: BookingUseCases,
    private val sharedPrefManager: SharedPrefManager,
    private val paymentUseCases: PaymentUseCases,
) : ViewModel() {

    private val _BookingIdResponse = MutableStateFlow<Result<IdRespondDTO>>(Result.Idle)
    val BookingIdResponse = _BookingIdResponse.asStateFlow()

    private val _bookingResponse = MutableStateFlow<Result<BookingResponse>>(Result.Idle)
    val BookingResponse = _bookingResponse.asStateFlow()

    fun createBooking (
        bookingData: CreateBookingDTO
    ) = viewModelScope.launch {
        val token = sharedPrefManager.getToken()
        Log.d("Booking ViewModel", "Get token")
        Log.d("Booking ViewModel Token", token.toString())
        val bearerToken = "Bearer $token"
        bookingUseCases.bookingUseCase(bookingData).onStart {
            _BookingIdResponse.value = Result.Loading
            Log.d("Booking ViewModel createBooking", "Loading")
        }.catch {
            if(it is HttpException){
                Log.d("Booking ViewModel createBooking", "catch")
                //Log.d("ChangePassword ViewModel E", it.message.toString())
               val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("Booking ViewModel Error createBooking", errorResponse.message)
                Log.d("Booking ViewModel Error createBooking", errorResponse.log)
                _BookingIdResponse.value = Result.Error(errorResponse.message)
            }
            else if (it is Exception) {
                Log.d("Booking ViewModel createBooking", it.javaClass.name)
                _BookingIdResponse.value = Result.Error("loi roi")
            }
        }.collect {
            Log.d("Booking ViewModel", "UserBookings Success ${it.data.toString()}")
            _BookingIdResponse.value = Result.Success(it)
            getBooking(it.data)
        }
    }
    private val getLinkResponse = MutableStateFlow<Result<LinkPaymentDTO>>(Result.Idle)
    val GetLinkResponse = getLinkResponse.asStateFlow()
    fun getLinkPayment(bookingId:String?,dealId:String?,currency:String = "VND") = viewModelScope.launch {
        paymentUseCases.getLinkPaymentUseCase(bookingId = bookingId.toString(), dealId = dealId, currency = currency).onStart {
            Log.d("Booking ViewModel getlink", "Loading")
            getLinkResponse.value = Result.Loading
        }.catch {
            if(it is HttpException){
                Log.d("Booking ViewModel getlink", "catch")
                //Log.d("ChangePassword ViewModel E", it.message.toString())
                Log.d("Booking ViewModel getlink", "hehe")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("Booking ViewModel Error getlink", errorResponse.message)
                Log.d("Booking ViewModel Error getlink", errorResponse.log)
                getLinkResponse.value = Result.Error(errorResponse.message)
            }
            else if (it is Exception) {
                Log.d("Booking ViewModel getlink", it.javaClass.name)
                getLinkResponse.value = Result.Error("loi roi")
            }
        }.collect{
            getLinkResponse.value = Result.Success(it)
            TxnShare.TxnID = it.data.txnId.toString()
            Log.d("Booking ViewModel getlink", it.data.toString())
        }
    }
    fun getBooking (
        bookingId: String
    ) = viewModelScope.launch {
        val token = sharedPrefManager.getToken()
        Log.d("BookingDetails ViewModel getBooking", "Get token")
        Log.d("BookingDetails ViewModel Token", token.toString())
        val bearerToken = "Bearer $token"

        bookingUseCases.getBookingUseCase(bookingId).onStart {
            _bookingResponse.value = Result.Loading
            Log.d("BookingDetails ViewModel getBooking", "Loading")
        }.catch {
            if(it is HttpException){
                Log.d("BookingDetails ViewModel getBooking", "catch")
                //Log.d("ChangePassword ViewModel E", it.message.toString())
                Log.d("BookingDetails ViewModel getBooking", "hehe")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("BookingDetails ViewModel Error getBooking", errorResponse.message)
                _bookingResponse.value = Result.Error(errorResponse.message)
            }
            else if (it is Exception) {
                Log.d("BookingDetails ViewModel getBooking", it.javaClass.name)
                _bookingResponse.value = Result.Error("loi roi")
            }
        }.collect {
            Log.d("BookingDetails ViewModel getBooking", "BookingDetails Screen Success")
            _bookingResponse.value = Result.Success(it)
            Log.d("Booking View",it.data.toString())
        }
    }



    private val executePaymentResponse = MutableStateFlow<Result<Response>>(Result.Idle)
    val ExecutePaymentResponse = executePaymentResponse.asStateFlow()
    fun executePayment(txnId:String) = viewModelScope.launch {
        paymentUseCases.executePaymentUseCase(txnId).onStart {
            executePaymentResponse.value = Result.Loading
        }.catch {
            if(it is HttpException){
                Log.d("BookingDetails ViewModel execute", "catch")
                //Log.d("ChangePassword ViewModel E", it.message.toString())
                Log.d("BookingDetails ViewModel execute", "hehe")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("BookingDetails ViewModel Error execute", errorResponse.message)
                executePaymentResponse.value = Result.Error(errorResponse.message)
            }
            else if (it is Exception) {
                Log.d("BookingDetails ViewModel execute", it.javaClass.name)
                executePaymentResponse.value = Result.Error("loi roi")
            }
        }.collect{
            Log.d("BookingDetails ViewModel execute", it.toString())

            executePaymentResponse.value = Result.Success(it)
        }
    }

    private val cancelPaymentResponse = MutableStateFlow<Result<Response>>(Result.Idle)
    val CancelPaymentResponse = executePaymentResponse.asStateFlow()
    fun cancelPayment(txnId:String) = viewModelScope.launch {
        paymentUseCases.cancelPaymentUseCase(txnId).onStart {
            cancelPaymentResponse.value = Result.Loading
        }.catch {
            if(it is HttpException){
                Log.d("BookingDetails ViewModel cancel", "catch")
                //Log.d("ChangePassword ViewModel E", it.message.toString())
                Log.d("BookingDetails ViewModel cancel", "hehe")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("BookingDetails ViewModel Error cancel", errorResponse.message)
                cancelPaymentResponse.value = Result.Error(errorResponse.message)
            }
            else if (it is Exception) {
                Log.d("BookingDetails ViewModel cancel", it.javaClass.name)
                cancelPaymentResponse.value = Result.Error("loi roi")
            }
        }.collect{
            Log.d("BookingDetails ViewModel cancel", it.toString())
            cancelPaymentResponse.value = Result.Success(it)
        }
    }

    private val successPaymentResponse = MutableStateFlow<Result<SuccessPaymentDTO>>(Result.Idle)
    val SuccessPaymentResponse = successPaymentResponse.asStateFlow()
    fun SuccessPayment(bookingId:String) = viewModelScope.launch {
        paymentUseCases.successPaymentUseCase(bookingId).onStart {
            successPaymentResponse.value = Result.Loading
        }.catch {
            if(it is HttpException){
                Log.d("BookingDetails ViewModel success", "catch")
                //Log.d("ChangePassword ViewModel E", it.message.toString())
                Log.d("BookingDetails ViewModel success", "hehe")
                val errorResponse = Gson().fromJson(it.response()?.errorBody()!!.string(), ErrorResponse::class.java)
                Log.d("BookingDetails ViewModel Error success", errorResponse.message)
                successPaymentResponse.value = Result.Error(errorResponse.message)
            }
            else if (it is Exception) {
                Log.d("BookingDetails ViewModel success", it.javaClass.name)
                successPaymentResponse.value = Result.Error("loi roi")
            }
        }.collect{
            Log.d("BookingDetails ViewModel success", it.toString())

            successPaymentResponse.value = Result.Success(it)
        }
    }

}
package com.example.h5traveloto_booking.share

import android.util.Log
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.utils.today
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import javax.inject.Singleton

class ShareDataHotelDetail {
    private var hotelId: String = ""
    private var startDate = LocalDate.today()
    private var endDate = LocalDate.today().plus(1, kotlinx.datetime.DateTimeUnit.DAY)
    private var roomQuantity: Int = 1
    private var adults: Int = 1
    private var children: Int = 0

    fun getHotelId(): String{
        return hotelId
    }

    fun getStartDate(): LocalDate{
        return startDate
    }

    fun getEndDate(): LocalDate{
        return endDate
    }

    fun getRoomQuantity(): Int{
        return roomQuantity
    }

    fun getAdults(): Int{
        return adults
    }

    fun getChildren(): Int{
        return children
    }

    fun setHotelId(hotelId: String){
        this.hotelId = hotelId
    }

    fun setStartDateEndDate(startDate: LocalDate, endDate: LocalDate){
        this.startDate = startDate
        this.endDate = endDate
    }

    fun setAdultsChildrenRoomQuantity(adults: Int, children: Int, roomQuantity: Int){
        this.adults = adults
        this.children = children
        this.roomQuantity = roomQuantity
    }

    fun LogData() {
        Log.d("ShareDataHotelDetail", "hotelId: $hotelId, startDate: $startDate, endDate: $endDate, roomQuantity: $roomQuantity, adults: $adults, children: $children")
    }

    private fun setStartDateEndDate(localDate: LocalDate): String{
        val Day = if(localDate.dayOfMonth < 10) "0${localDate.dayOfMonth}" else localDate.dayOfMonth.toString()
        val Month = if(localDate.monthNumber < 10) "0${localDate.monthNumber}" else localDate.monthNumber.toString()
        val Year = localDate.year.toString()
        return "\"$Day-$Month-$Year\""
    }

    fun getStartDateString(): String{
        return setStartDateEndDate(startDate)
    }

    fun getEndDateString(): String{
        return setStartDateEndDate(endDate)
    }
}

@Singleton
val shareDataHotelDetail = ShareDataHotelDetail()
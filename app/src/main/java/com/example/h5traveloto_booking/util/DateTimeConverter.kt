package com.example.h5traveloto_booking.util

import kotlinx.datetime.LocalDate

public fun FromStringtoDate(
    date: String
) : LocalDate {
    val info = date.split("-")
    return if (info.size == 3) LocalDate(info[2].toInt(), info[1].toInt(), info[0].toInt())
    else LocalDate(1990,1,1,)
}

public fun FromDatetoString (
    date: LocalDate
) : String {
    val Day = if(date.dayOfMonth < 10) "0${date.dayOfMonth}" else date.dayOfMonth.toString()
    val Month = if(date.monthNumber < 10) "0${date.monthNumber}" else date.monthNumber.toString()
    val Year = date.year.toString()
    return "\"$Day-$Month-$Year\""
}
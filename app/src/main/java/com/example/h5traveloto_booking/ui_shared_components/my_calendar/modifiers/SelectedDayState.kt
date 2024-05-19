package com.example.h5traveloto_booking.ui_shared_components.my_calendar.modifiers

import kotlinx.datetime.LocalDate

data class SelectedDayState(
    val date : LocalDate,
    val isPickedDay : Boolean = false,
    val isPickedMidDay : Boolean = false
)

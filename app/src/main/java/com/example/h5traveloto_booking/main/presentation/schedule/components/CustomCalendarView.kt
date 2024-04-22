package com.example.h5traveloto_booking.main.presentation.schedule.components

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.himanshoe.kalendar.Kalendar
import com.himanshoe.kalendar.KalendarEvents
import com.himanshoe.kalendar.KalendarType
import com.himanshoe.kalendar.color.KalendarColors
import com.himanshoe.kalendar.ui.component.day.KalendarDayKonfig
import com.himanshoe.kalendar.ui.firey.DaySelectionMode
import io.wojciechosak.calendar.utils.today
import kotlinx.datetime.LocalDate

@Composable
public fun CustomCalendarView() {
//    var selectedDate by remember {
//        mutableStateOf(LocalDate.today())
//    }
    val currentDate = LocalDate.today()
    Kalendar(
        currentDay = currentDate,
        kalendarType = KalendarType.Oceanic,
        showLabel = true,
        events = KalendarEvents(),
        kalendarHeaderTextKonfig = null,
        kalendarColors = KalendarColors.default(),
        kalendarDayKonfig = KalendarDayKonfig.default(),
        daySelectionMode = DaySelectionMode.Single,
        dayContent = null,
        headerContent = null,
        onDayClick = {selectedDate, events ->

        },
        onRangeSelected = {selectedRange, events ->

        },
        onErrorRangeSelected = {error ->

        }
    )
}
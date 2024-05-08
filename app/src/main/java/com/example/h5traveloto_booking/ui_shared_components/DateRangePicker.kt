package com.example.h5traveloto_booking.ui_shared_components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.h5traveloto_booking.main.presentation.schedule.components.MonthHeader
import com.example.h5traveloto_booking.theme.Grey50Color
import com.example.h5traveloto_booking.theme.PrimaryColor
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.animation.CalendarAnimator
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.config.SelectionMode
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.config.rememberCalendarState
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.range.RangeConfig
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.range.RoundedRangeIllustrator
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.utils.today
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.view.CalendarDay
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.view.CalendarView
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.view.HorizontalCalendarView
//import io.wojciechosak.calendar.animation.CalendarAnimator
//import io.wojciechosak.calendar.config.SelectionMode
//import io.wojciechosak.calendar.config.rememberCalendarState
//import io.wojciechosak.calendar.range.RangeConfig
//import io.wojciechosak.calendar.range.RoundedRangeIllustrator
//import io.wojciechosak.calendar.utils.today
//import io.wojciechosak.calendar.view.CalendarView
//import io.wojciechosak.calendar.view.HorizontalCalendarView
import kotlinx.coroutines.launch
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.plus

@OptIn(ExperimentalFoundationApi::class)
@Composable
public fun DateRangePicker(
    onComplete: (
            startDate : LocalDate,
            endDate : LocalDate
            ) -> Unit
) {
    val startDate by remember { mutableStateOf(LocalDate.today()) }
    val selectedDates = remember { mutableStateListOf<LocalDate>() }
    val calendarAnimator by remember { mutableStateOf(CalendarAnimator(startDate)) }

    val coroutineScope = rememberCoroutineScope()

    Dialog(
        onDismissRequest = { /*TODO*/ }
    ) {

    }

    HorizontalCalendarView (
        modifier = Modifier
            .padding(10.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = Grey50Color),
        startDate = startDate,
        calendarAnimator = calendarAnimator
    ) { monthOffset ->
        CalendarView(
            modifier = Modifier
                .padding(10.dp),
            day = { dayState ->
                CalendarDay(
                    state = dayState,
                    modifier = Modifier
                        .padding(0.dp, 5.dp, 0.dp, 5.dp)
                )
            },
            header = { month, year ->
                MonthHeader(
                    month = month,
                    year = year,
                    onLeftClick = {
                        coroutineScope.launch {
                            calendarAnimator.animateTo(
                                target = LocalDate(
                                    startDate.year,
                                    startDate.month.plus(monthOffset.toLong()),
                                    startDate.dayOfMonth
                                ).plus(-1, DateTimeUnit.MONTH)
                            )
                        }
                    },
                    onRightClick = {
                        coroutineScope.launch {
                            calendarAnimator.animateTo(
                                target = LocalDate(
                                    startDate.year,
                                    startDate.month.plus(monthOffset.toLong()),
                                    startDate.dayOfMonth
                                ).plus(1, DateTimeUnit.MONTH)
                            )
                        }
                    }
                )
            },
            config = rememberCalendarState(
                startDate = startDate,
                monthOffset = monthOffset,
                selectedDates = selectedDates
            ),
            selectionMode = SelectionMode.Range,
            onDateSelected = {
                selectedDates.clear()
                selectedDates.addAll(it)
            },
            rangeConfig = RangeConfig(
                color = PrimaryColor,
                rangeIllustrator = RoundedRangeIllustrator(PrimaryColor)
            )
        )
    }
}
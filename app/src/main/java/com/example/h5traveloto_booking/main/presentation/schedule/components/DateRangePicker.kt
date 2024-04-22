package com.example.h5traveloto_booking.main.presentation.schedule.components

import android.util.Log
import androidx.collection.mutableIntSetOf
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.BookingDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.BookingDayState
import com.example.h5traveloto_booking.theme.Grey50Color
import com.example.h5traveloto_booking.theme.PrimaryColor
import com.example.h5traveloto_booking.theme.SecondaryColor
import com.example.h5traveloto_booking.util.localdate_range.LocalDateProgression
import io.wojciechosak.calendar.animation.CalendarAnimator
import io.wojciechosak.calendar.config.CalendarConfig
import io.wojciechosak.calendar.config.DayState
import io.wojciechosak.calendar.config.SelectionMode
import io.wojciechosak.calendar.config.rememberCalendarState
import io.wojciechosak.calendar.utils.toMonthYear
import io.wojciechosak.calendar.utils.today
import io.wojciechosak.calendar.view.CalendarView
import io.wojciechosak.calendar.view.HorizontalCalendarView
import kotlinx.coroutines.launch
import kotlinx.datetime.*

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
public fun DateRangePicker(
    bookingList : List<BookingDTO>
) {
    val startDate by remember { mutableStateOf(LocalDate.today()) }
    val selectedDates = remember { mutableStateListOf<LocalDate>() }
    val mapBookingDayState = remember { mutableStateMapOf<LocalDate, BookingDayState>() }
    var targetDate by remember { mutableStateOf(LocalDate.today()) }

    var calendarAnimator by remember { mutableStateOf(CalendarAnimator(startDate)) }
    val coroutineScope = rememberCoroutineScope()
//    var rememberCalendarStateFix = remember {
//        mutableStateOf (
//            CalendarConfig(
//                minDate = LocalDate(1971, 1, 1),
//                maxDate = startDate.plus(15, DateTimeUnit.YEAR),
//                monthYear = startDate.plus(monthOffsetFix.intValue, DateTimeUnit.MONTH).toMonthYear(),
//                dayOfWeekOffset = 0,
//                showNextMonthDays = true,
//                showPreviousMonthDays = true,
//                showHeader = true,
//                showWeekdays = true,
//                selectedDates = selectedDates
//            )
//        )
//    }

    val rangeColor = SecondaryColor
    val rangeStrokeWidth = 2

    val modifier_startDay = Modifier.drawBehind {
        val strokeWidth = rangeStrokeWidth * density
        val yDown = size.height
        val yUp = -(size.height - 126f)
        drawArc(
            color = rangeColor,
            startAngle = -90f,
            sweepAngle = -180f,
            useCenter = false,
            style = Stroke(
                width = strokeWidth
            )
        )
        drawLine(
            color = rangeColor,
            start =  Offset(size.width/2, yDown),
            end =  Offset(size.width, yDown),
            strokeWidth = strokeWidth
        )
        drawLine(
            color = rangeColor,
            Offset(size.width/2, yUp),
            Offset(size.width, yUp),
            strokeWidth
        )
    }
    val modifier_midDay = Modifier.drawBehind {
        val strokeWidth = rangeStrokeWidth * density
        val yDown = size.height
        val yUp = -(size.height - 126f)
        drawLine(
            color = rangeColor,
            start =  Offset(0f, yDown),
            end =  Offset(size.width, yDown),
            strokeWidth = strokeWidth
        )
        drawLine(
            color = rangeColor,
            Offset(0f, yUp),
            Offset(size.width, yUp),
            strokeWidth
        )
    }
    val modifier_endDay = Modifier.drawBehind {
        val strokeWidth = rangeStrokeWidth * density
        val yDown = size.height
        val yUp = -(size.height - 126f)
        drawArc(
            color = rangeColor,
            startAngle = -90f,
            sweepAngle = 180f,
            useCenter = false,
            style = Stroke(
                width = strokeWidth
            )
        )
        drawLine(
            color = rangeColor,
            start =  Offset(0f, yDown),
            end =  Offset(size.width/2, yDown),
            strokeWidth = strokeWidth
        )
        drawLine(
            color = rangeColor,
            Offset(0f, yUp),
            Offset(size.width/2, yUp),
            strokeWidth
        )
    }
    val modifier_fullDay = Modifier.drawBehind {
        val strokeWidth = rangeStrokeWidth * density
        val yDown = size.height
        val yUp = -(size.height - 126f)
        drawArc(
            color = rangeColor,
            startAngle = -90f,
            sweepAngle = 360f,
            useCenter = false,
            style = Stroke(
                width = strokeWidth
            )
        )
    }
    
    for (booking in bookingList) {
        // Note start date
        if (mapBookingDayState[booking.start_date] == null)
        {
            mapBookingDayState[booking.start_date] = BookingDayState(
                date = booking.start_date,
                is_StartDate = false,
                is_MiddleDate = false,
                is_EndDate = false,
                is_FullDate = false
            )
        }
        mapBookingDayState[booking.start_date]?.booking_id?.add(booking.id)
        mapBookingDayState[booking.start_date]?.is_StartDate = true

        // Note middle date
        for (
            date: LocalDate in
            booking.start_date.plus(DatePeriod(days = 1))..
            booking.end_date.minus(DatePeriod(days = 1))
        ) {
            if (mapBookingDayState[date] == null)
            {
                mapBookingDayState[date] = BookingDayState(
                    date = date,
                    is_StartDate = false,
                    is_MiddleDate = false,
                    is_EndDate = false,
                    is_FullDate = false
                )
            }
            mapBookingDayState[date]?.booking_id?.add(booking.id)
            mapBookingDayState[date]?.is_MiddleDate = true
        }

        // Note end date
        if (mapBookingDayState[booking.end_date] == null)
        {
            mapBookingDayState[booking.end_date] = BookingDayState(
                date = booking.end_date,
                is_StartDate = false,
                is_MiddleDate = false,
                is_EndDate = false,
                is_FullDate = false
            )
        }
        mapBookingDayState[booking.end_date]?.booking_id?.add(booking.id)
        mapBookingDayState[booking.end_date]?.is_EndDate = true;

        // Note full date
        if (booking.start_date == booking.end_date) {
            if (mapBookingDayState[booking.start_date] == null)
            {
                mapBookingDayState[booking.start_date] = BookingDayState(
                    date = booking.start_date,
                    is_StartDate = false,
                    is_MiddleDate = false,
                    is_EndDate = false,
                    is_FullDate = false
                )
            }
            mapBookingDayState[booking.start_date]?.booking_id?.add(booking.id)
            mapBookingDayState[booking.start_date]?.is_FullDate = true
        }
    }

    HorizontalCalendarView(
        modifier = Modifier
            .padding(10.dp)
            .background(color = Grey50Color)
            .clip(shape = RoundedCornerShape(8.dp)),
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
                        .then(
                            if (mapBookingDayState[dayState.date] != null) {
                                Modifier
                                    .then(
                                        if (mapBookingDayState[dayState.date]!!.is_StartDate) {
                                            modifier_startDay
                                        } else Modifier
                                    )
                                    .then(
                                        if (mapBookingDayState[dayState.date]!!.is_MiddleDate) {
                                            modifier_midDay
                                        } else Modifier
                                    )
                                    .then(
                                        if (mapBookingDayState[dayState.date]!!.is_EndDate) {
                                            modifier_endDay
                                        } else Modifier
                                    )
                                    .then(
                                        if (mapBookingDayState[dayState.date]!!.is_FullDate) {
                                            modifier_fullDay
                                        } else Modifier
                                    )
                            } else {
                                Modifier
                            }
                        )
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
//            selectionMode = SelectionMode.Range,
            onDateSelected = {
                selectedDates.clear()
                selectedDates.addAll(it)
                Log.d("SelectedDate", selectedDates.toList().toString())
            },
//            rangeConfig =
//                RangeConfig(
//                    color = Color.Red,
//                    rangeIllustrator = RoundedRangeIllustrator(Color.Red)
//                )
        )
    }
}

// LocalDate CloseRange Operator
operator fun LocalDate.rangeTo(other: LocalDate) = LocalDateProgression(this, other)
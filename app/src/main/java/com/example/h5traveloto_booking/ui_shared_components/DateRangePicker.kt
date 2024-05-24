package com.example.h5traveloto_booking.ui_shared_components

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.wear.compose.material.Button
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.BookingDayState
import com.example.h5traveloto_booking.main.presentation.schedule.components.MonthHeader
import com.example.h5traveloto_booking.theme.*
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.animation.CalendarAnimator
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.config.CalendarConstants.MIN_DATE
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.config.SelectionMode
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.config.rememberCalendarState
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.modifiers.*
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.range.RangeConfig
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.range.RoundedRangeIllustrator
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.utils.copy
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.utils.today
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.view.CalendarDay
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.view.CalendarView
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.view.HorizontalCalendarView
import com.example.h5traveloto_booking.util.localdate_range.LocalDateProgression
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

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
public fun DateRangePicker(
    start : LocalDate,
    end : LocalDate,
    onDismiss : () -> Unit,
    onCompleted: (
            startDate : LocalDate,
            endDate : LocalDate
            ) -> Unit
) {
    val toDay by remember { mutableStateOf(LocalDate.today()) }
    val selectedDates = remember { mutableStateListOf<LocalDate>() }
    val mapPickedDayState = mutableMapOf<LocalDate, BookingDayState>()
    val calendarAnimator = CalendarAnimator(toDay)

    val startDate = remember { mutableStateOf(start) }
    val endDate = remember { mutableStateOf(end) }
    val coroutineScope = rememberCoroutineScope()

    val rangeColor = SecondaryColor
    val rangeStrokeWidth = 2
    val weekDays = listOf(
        "Thứ 2",
        "Thứ 3",
        "Thứ 4",
        "Thứ 5",
        "Thứ 6",
        "Thứ 7",
        "Chủ nhật"
    )

    if (endDate.value == MIN_DATE && startDate.value != MIN_DATE) {
        mapPickedDayState[startDate.value] = BookingDayState(
            date = startDate.value,
            is_FullDate = true,
            is_MiddleDate = false,
            is_StartDate = false,
            is_EndDate = false
        )
    }
    for (day in startDate.value..endDate.value) {
        if (startDate.value == endDate.value) {
            mapPickedDayState[startDate.value] = BookingDayState(
                date = startDate.value,
                is_FullDate = true,
                is_MiddleDate = false,
                is_StartDate = false,
                is_EndDate = false
            )
        }
        else if (day == startDate.value) {
            mapPickedDayState[day] = BookingDayState(
                date = day,
                is_FullDate = false,
                is_MiddleDate = false,
                is_StartDate = true,
                is_EndDate = false
            )
        } else if (day == endDate.value) {
            mapPickedDayState[day] = BookingDayState(
                date = day,
                is_FullDate = false,
                is_MiddleDate = false,
                is_StartDate = false,
                is_EndDate = true
            )
        } else {
            mapPickedDayState[day] = BookingDayState(
                date = day,
                is_FullDate = false,
                is_MiddleDate = true,
                is_StartDate = false,
                is_EndDate = false
            )
        }
    }

    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card (
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        ) {
            Column {
                HorizontalCalendarView (
                    modifier = Modifier
                        .height(435.dp)
                        .padding(10.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Grey50Color),
                    startDate = toDay,
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
                                        if (mapPickedDayState[dayState.date] != null) {
                                            Modifier
                                                .then(
                                                    if (mapPickedDayState[dayState.date]!!.is_StartDate) {
                                                        Modifier.RangeStartDate(rangeStrokeWidth, rangeColor)
                                                    } else Modifier
                                                )
                                                .then(
                                                    if (mapPickedDayState[dayState.date]!!.is_EndDate) {
                                                        Modifier.RangeEndDay(rangeStrokeWidth, rangeColor)
                                                    } else Modifier
                                                )
                                                .then(
                                                    if (mapPickedDayState[dayState.date]!!.is_MiddleDate) {
                                                        Modifier.RangeMidDay(rangeStrokeWidth, rangeColor)
                                                    } else Modifier
                                                )
                                                .then(
                                                    if (mapPickedDayState[dayState.date]!!.is_FullDate) {
                                                        Modifier.RangeFullDay(rangeStrokeWidth, rangeColor)
                                                    } else Modifier
                                                )
                                        } else Modifier
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
                                                toDay.year,
                                                toDay.month.plus(monthOffset.toLong()),
                                                toDay.dayOfMonth
                                            ).plus(-1, DateTimeUnit.MONTH)
                                        )
                                    }
                                },
                                onRightClick = {
                                    coroutineScope.launch {
                                        calendarAnimator.animateTo(
                                            target = LocalDate(
                                                toDay.year,
                                                toDay.month.plus(monthOffset.toLong()),
                                                toDay.dayOfMonth
                                            ).plus(1, DateTimeUnit.MONTH)
                                        )
                                    }
                                },
                                onComplete = { monthSelected, yearSelected ->
                                    coroutineScope.launch {
                                        calendarAnimator.animateTo(
                                            target = LocalDate(
                                                yearSelected,
                                                monthSelected,
                                                toDay.dayOfMonth
                                            )
                                        )
                                    }
                                }
                            )
                        },
                        config = rememberCalendarState(
                            minDate = toDay,
                            maxDate = toDay.plus(2, DateTimeUnit.YEAR),
                            startDate = toDay,
                            monthOffset = monthOffset,
                            selectedDates = selectedDates
                        ),
                        horizontalArrangement = Arrangement.spacedBy(0.dp),
                        verticalArrangement = Arrangement.spacedBy(0.dp),
                        selectionMode = SelectionMode.Multiply(1),
                        onDateSelected = {
                            if (it[0] in LocalDate.today()..LocalDate.today().plus(15, DateTimeUnit. YEAR)) {
                                if (endDate.value != MIN_DATE) {
                                    startDate.value = it[0]
                                    endDate.value = MIN_DATE
                                } else {
                                    if (it[0] > startDate.value) {
                                        endDate.value = it[0]
                                    } else {
                                        startDate.value = it[0]
                                    }
                                }
                            }
//                            selectedDates.clear()
//                            selectedDates.addAll(it)
                        }
                    )
                }

                Row (
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxWidth(0.33f)
                    ) {
                        Text(
                            text = "Bắt đầu",
                            color = Grey500Color,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = if (startDate.value == MIN_DATE) ""
                            else "${weekDays.getOrNull(startDate.value.dayOfWeek.ordinal)}, ${startDate.value.dayOfMonth}/${startDate.value.monthNumber}/${startDate.value.year}",
                            fontWeight = FontWeight.Normal,
                            color = Grey500Color,
                            fontSize = 12.sp
                        )
                    }
                    Column (
                        modifier = Modifier
                            .fillMaxWidth(0.55f)
                    ) {
                        Text(
                            text = "Kết thúc",
                            color = Grey500Color,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = if (endDate.value == MIN_DATE) ""
                                else "${weekDays.getOrNull(endDate.value.dayOfWeek.ordinal)}, ${endDate.value.dayOfMonth}/${endDate.value.monthNumber}/${endDate.value.year}",
                            fontWeight = FontWeight.Normal,
                            color = Grey500Color,
                            fontSize = 12.sp
                        )
                    }
                    Button(
                        onClick = {
                            onCompleted(startDate.value,endDate.value)
                        },
                        colors = ButtonColors(
                            containerColor = OrangeColor,
                            contentColor = Color.White,
                            disabledContainerColor = Grey500Color,
                            disabledContentColor = Color.White
                        ),
                        enabled = (endDate.value != MIN_DATE),
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Tiếp tục",
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}

// LocalDate CloseRange Operator
operator fun LocalDate.rangeTo(other: LocalDate) = LocalDateProgression(this, other)
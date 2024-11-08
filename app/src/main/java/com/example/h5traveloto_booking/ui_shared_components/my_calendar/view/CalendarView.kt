package com.example.h5traveloto_booking.ui_shared_components.my_calendar.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.h5traveloto_booking.theme.Grey500Color
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.config.CalendarConfig
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.config.DayState
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.config.MonthYear
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.config.SelectionMode
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.modifiers.drawRange
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.modifiers.passTouchGesture
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.range.RangeConfig
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.utils.monthLength
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.utils.today
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.view.MonthHeader
//import io.wojciechosak.calendar.config.CalendarConfig
//import io.wojciechosak.calendar.config.DayState
//import io.wojciechosak.calendar.config.MonthYear
//import io.wojciechosak.calendar.config.SelectionMode
//import io.wojciechosak.calendar.modifiers.drawRange
//import io.wojciechosak.calendar.modifiers.passTouchGesture
//import io.wojciechosak.calendar.range.RangeConfig
//import io.wojciechosak.calendar.utils.monthLength
//import io.wojciechosak.calendar.utils.today
//import io.wojciechosak.calendar.view.MonthHeader
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.plus

/**
 * Composable function to display a single month calendar.
 *
 * @param config A mutable state representing the configuration of the calendar.
 * @param horizontalArrangement Arrangement for horizontal alignment of items in the calendar grid. Default is [Arrangement.SpaceEvenly].
 * @param verticalArrangement Arrangement for vertical alignment of items in the calendar grid. Default is [Arrangement.SpaceEvenly].
 * @param isActiveDay A lambda function to determine if a date is considered active. Default is a comparison with the current date.
 * @param day A composable function to display a single calendar day.
 * @param header A composable function to display the header of the calendar (e.g., month and year).
 * @param dayOfWeekLabel A composable function to display labels for the days of the week.
 * @param selectionMode The selection mode for the calendar dates. Default is [SelectionMode.Single].
 * @param onDateSelected A callback function invoked when a date is selected.
 * @param rangeConfig Configuration for rendering date range selections.
 * @param modifier Modifier for styling and layout of the calendar.
 */
@Composable
fun CalendarView(
    config: MutableState<CalendarConfig>,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceEvenly,
    verticalArrangement: Arrangement.Vertical = Arrangement.SpaceEvenly,
    isActiveDay: (LocalDate) -> Boolean = { LocalDate.today() == it },
    day: @Composable (DayState) -> Unit = { dayState ->
        CalendarDay(dayState)
    },
    header: @Composable (month: Month, year: Int) -> Unit = { month, year ->
        MonthHeader(month, year)
    },
    dayOfWeekLabel: @Composable (dayOfWeek: DayOfWeek) -> Unit = { dayOfWeek ->
        val days = listOf(
            "T2",
            "T3",
            "T4",
            "T5",
            "T6",
            "T7",
            "CN"
        )
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "${days.getOrNull(dayOfWeek.ordinal)}",
//            dayOfWeek.name.substring(IntRange(0, 2)),
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(0.dp,0.dp,0.dp,10.dp)
            )
            HorizontalDivider(
                modifier = Modifier
                    .padding(0.dp,0.dp,0.dp,2.dp),
                thickness = 1.dp,
                color = Grey500Color
            )
        }
    },
    selectionMode: SelectionMode = SelectionMode.Single,
    onDateSelected: (List<LocalDate>) -> Unit = {},
    rangeConfig: RangeConfig? = null,
    modifier: Modifier = Modifier,
) {
    val yearMonth by remember { mutableStateOf(config.value.monthYear) }
    val daysInCurrentMonth by remember {
        mutableStateOf(
            monthLength(
                year = yearMonth.year,
                month = yearMonth.month,
            ),
        )
    }
    val previousMonthDays by remember { mutableStateOf(calculateVisibleDaysOfPreviousMonth(yearMonth)) }
    val nextMonthDays by remember {
        mutableStateOf(
            if (config.value.showNextMonthDays) {
                calculateVisibleDaysOfNextMonth(
                    yearMonth,
                )
            } else {
                0
            },
        )
    }
    Column {
        if (config.value.showHeader) {
            header(yearMonth.month, yearMonth.year)
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            horizontalArrangement = horizontalArrangement,
            verticalArrangement = verticalArrangement,
            userScrollEnabled = false,
            modifier = modifier,
        ) {
            val state = config.value
            val weekDaysCount = if (state.showWeekdays) 7 else 0

            items(previousMonthDays + daysInCurrentMonth + nextMonthDays + weekDaysCount) { iteration ->
                Item(
                    iteration = iteration,
                    config = config,
                    weekDaysCount = weekDaysCount,
                    previousMonthDays = previousMonthDays,
                    daysInCurrentMonth = daysInCurrentMonth,
                    dayOfWeekLabel = dayOfWeekLabel,
                    yearMonth = yearMonth,
                    state = state,
                    selectionMode = selectionMode,
                    onDateSelected = onDateSelected,
                    isActiveDay = isActiveDay,
                    rangeConfig = rangeConfig,
                    day = day,
                )
            }
        }
    }
}

@Composable
private fun Item(
    iteration: Int,
    config: MutableState<CalendarConfig>,
    weekDaysCount: Int,
    previousMonthDays: Int,
    daysInCurrentMonth: Int,
    dayOfWeekLabel: @Composable (DayOfWeek) -> Unit,
    yearMonth: MonthYear,
    state: CalendarConfig,
    selectionMode: SelectionMode,
    onDateSelected: (List<LocalDate>) -> Unit,
    isActiveDay: (LocalDate) -> Boolean,
    rangeConfig: RangeConfig?,
    day: @Composable (DayState) -> Unit,
) {
    val isWeekdayLabel = state.showWeekdays && iteration < weekDaysCount
    val previousMonthDay =
        iteration >= weekDaysCount && iteration < weekDaysCount + previousMonthDays
    val nextMonthDay =
        iteration >= weekDaysCount + previousMonthDays + daysInCurrentMonth
    var newDate = LocalDate(year = yearMonth.year, month = yearMonth.month, dayOfMonth = 1)

    if (previousMonthDay && config.value.showPreviousMonthDays) {
        newDate = newDate.plus(iteration - weekDaysCount - previousMonthDays, DateTimeUnit.DAY)
    } else if (nextMonthDay && config.value.showNextMonthDays) {
        newDate = newDate
            .plus(1, DateTimeUnit.MONTH)
            .plus(
                iteration - previousMonthDays - weekDaysCount - daysInCurrentMonth,
                DateTimeUnit.DAY,
            )
    } else if (!isWeekdayLabel) {
        newDate = newDate.plus(iteration - previousMonthDays - weekDaysCount, DateTimeUnit.DAY)
    }
    newDate = newDate.plus(state.dayOfWeekOffset, DateTimeUnit.DAY)

    if (state.showWeekdays && iteration + state.dayOfWeekOffset < 7 + state.dayOfWeekOffset) {
        val dayOfWeekIndex =
            if (iteration + state.dayOfWeekOffset >= DayOfWeek.entries.size) {
                iteration + state.dayOfWeekOffset - DayOfWeek.entries.size
            } else if (iteration + state.dayOfWeekOffset < 0) {
                DayOfWeek.entries.size + iteration + state.dayOfWeekOffset
            } else {
                iteration + state.dayOfWeekOffset
            }
        dayOfWeekLabel(DayOfWeek.entries[dayOfWeekIndex])
    } else if ((!state.showPreviousMonthDays && previousMonthDay) || (!state.showNextMonthDays && nextMonthDay)) {
        Text("")
    } else {
        val selectedDates = config.value.selectedDates
        Box(
            modifier = Modifier
                .passTouchGesture {
                    val selectionList = selectDate(
                        date = newDate,
                        mode = selectionMode,
                        list = config.value.selectedDates,
                    )
                    config.value = config.value.copy(selectedDates = selectionList)
                    onDateSelected(selectionList)
                }
                .drawRange(
                    selectedDates = selectedDates,
                    date = newDate,
                    config = rangeConfig,
                ),
        ) {
            day(
                DayState(
                    date = newDate,
                    isActiveDay = isActiveDay(newDate),
                    isForPreviousMonth = previousMonthDay,
                    isForNextMonth = nextMonthDay,
                    enabled = newDate >= state.minDate && newDate <= state.maxDate,
                ),
            )
        }
    }
}

private fun selectDate(
    date: LocalDate,
    mode: SelectionMode,
    list: List<LocalDate>,
): List<LocalDate> {
    if (list.firstOrNull() == date) return list
    val result = mutableListOf<LocalDate>()
    result.addAll(list)
    when (mode) {
        is SelectionMode.Multiply -> {
            result.add(0, date)
            if (result.size > mode.bufferSize) {
                result.removeLast()
            }
        }

        SelectionMode.Range -> {
            result.add(0, date)
            if (result.size > 2) {
                result.clear()
                result.add(0, date)
            }
        }

        SelectionMode.Single -> {
            result.clear()
            result.add(date)
        }
    }
    return result
}

private fun calculateVisibleDaysOfPreviousMonth(monthYear: MonthYear): Int {
    val (month, year) = monthYear
    return LocalDate(year = year, month = month, dayOfMonth = 1).dayOfWeek.ordinal
}

private fun calculateVisibleDaysOfNextMonth(monthYear: MonthYear): Int {
    val (month, year) = monthYear
    val daysInMonth = monthLength(month, year)
    val lastMonthDay = LocalDate(year = year, month = month, dayOfMonth = daysInMonth)
    return 6 - lastMonthDay.dayOfWeek.ordinal
}
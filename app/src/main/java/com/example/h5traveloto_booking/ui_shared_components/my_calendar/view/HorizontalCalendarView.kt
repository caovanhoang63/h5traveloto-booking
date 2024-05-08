package com.example.h5traveloto_booking.ui_shared_components.my_calendar.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.animation.CalendarAnimator
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.config.CalendarConstants.INITIAL_PAGE_INDEX
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.config.CalendarConstants.MAX_PAGES
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.config.rememberCalendarState
import kotlinx.coroutines.delay
//import io.wojciechosak.calendar.animation.CalendarAnimator
//import io.wojciechosak.calendar.config.CalendarConstants.INITIAL_PAGE_INDEX
//import io.wojciechosak.calendar.config.CalendarConstants.MAX_PAGES
//import io.wojciechosak.calendar.config.rememberCalendarState
import kotlinx.datetime.LocalDate
import kotlin.math.abs

/**
 * Composable function to display a horizontal calendar view.
 *
 * @param startDate The start date of the calendar.
 * @param pagerState The PagerState used to control the horizontal paging behavior of the calendar.
 * @param modifier The modifier for styling and layout of the calendar.
 * @param pageSize The size of each page in the calendar. Default is [PageSize.Fill].
 * @param beyondBoundsPageCount The number of pages to keep loaded beyond the visible bounds. Default is 0.
 * @param contentPadding The padding applied around the content of the day cell.
 * @param calendarAnimator The animator used for animating calendar transitions.
 * @param calendarView The composable function to display the content of each calendar page.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalCalendarView(
    startDate: LocalDate,
    pagerState: PagerState = rememberPagerState(
        initialPage = INITIAL_PAGE_INDEX,
        pageCount = { MAX_PAGES },
    ),
    modifier: Modifier = Modifier,
    pageSize: PageSize = PageSize.Fill,
    beyondBoundsPageCount: Int = 0,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    calendarAnimator: CalendarAnimator = CalendarAnimator(startDate),
    calendarView: @Composable (monthOffset: Int) -> Unit = {
        CalendarView(
            day = { dayState ->
                CalendarDay(
                    state = dayState,
                    onClick = { },
                )
            },
            config = rememberCalendarState(
                startDate = startDate,
                monthOffset = it,
            ),
        )
    },
) {
    HorizontalPager(
        state = pagerState,
        modifier = modifier,
        pageSize = pageSize,
        beyondBoundsPageCount = beyondBoundsPageCount,
        contentPadding = contentPadding,
    ) { page ->
        @Suppress("NAME_SHADOWING")
        val page by rememberUpdatedState(newValue = page)
        val authorizedPage = {
            abs(pagerState.settledPage - page) <= beyondBoundsPageCount
        }
        val authorizedTiming by produceState(initialValue = false) {
            while (pagerState.isScrollInProgress) delay(50)
            if (abs(pagerState.settledPage - page) > 0) {
                delay(1000)
                while (pagerState.isScrollInProgress) delay(50)
            }
            value = true
        }
        // Conditions to show content
        val showContent by remember {
            derivedStateOf {
                authorizedPage() && authorizedTiming
            }
        }

        if (showContent) {
            val index = page - INITIAL_PAGE_INDEX
            calendarAnimator.updatePagerState(pagerState)
            LaunchedEffect(Unit) {
                calendarAnimator.setAnimationMode(CalendarAnimator.AnimationMode.MONTH)
            }
            Column { calendarView(index) }
        } else {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}
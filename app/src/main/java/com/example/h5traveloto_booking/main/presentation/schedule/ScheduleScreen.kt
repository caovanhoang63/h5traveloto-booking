package com.example.h5traveloto_booking.main.presentation.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.BookingDTO
import com.example.h5traveloto_booking.main.presentation.schedule.components.BookingCalendar
import com.example.h5traveloto_booking.main.presentation.schedule.components.DateRangePicker
import com.example.h5traveloto_booking.main.presentation.schedule.components.BookingCard
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.ui_shared_components.ClickableText
import com.example.h5traveloto_booking.ui_shared_components.YSpacer
import kotlinx.datetime.LocalDate


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ScheduleScreen (navController: NavController) {
    val scheduleNavController = rememberNavController()

    val bookingList : List<BookingDTO> = listOf(
        BookingDTO(
            "1",
            LocalDate(2024,4,19),
            LocalDate(2024,4,20)
        ),
        BookingDTO(
            "2",
            LocalDate(2024,4,1),
            LocalDate(2024,4,4)
        ),
        BookingDTO(
            "3",
            LocalDate(2024,4,3),
            LocalDate(2024,4,3)
        ),
        BookingDTO(
            "4",
            LocalDate(2024,4,17),
            LocalDate(2024,4,20)
        ),
        BookingDTO(
            "5",
            LocalDate(2024,4,30),
            LocalDate(2024,5,6)
        )
    )

    NavHost (
        navController = scheduleNavController,
        startDestination = Screens.ScheduleCalendarScreen.name
    ) {
        composable(route = Screens.ScheduleCalendarScreen.name) {
            CalendarScreen(
                bookingList = bookingList,
                navController = scheduleNavController
            )
        }
        composable(route = Screens.ScheduleBookingScreen.name) {
            BookingScreen(
                bookingList = bookingList,
                navController = scheduleNavController
            )
        }
    }
}
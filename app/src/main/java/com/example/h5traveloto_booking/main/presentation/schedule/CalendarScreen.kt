package com.example.h5traveloto_booking.main.presentation.schedule

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.BookingDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.CreateBookingDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.UserBookingDTO
import com.example.h5traveloto_booking.main.presentation.schedule.components.BookingCalendar
import com.example.h5traveloto_booking.main.presentation.schedule.components.BookingCard
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.share.shareDataHotelDetail
import com.example.h5traveloto_booking.ui_shared_components.BoldText
import com.example.h5traveloto_booking.ui_shared_components.ClickableText
import com.example.h5traveloto_booking.ui_shared_components.YSpacer
import com.example.h5traveloto_booking.ui_shared_components.DateRangePicker
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.config.CalendarConstants.MIN_DATE
import com.example.h5traveloto_booking.util.Result
import com.google.gson.Gson
import io.wojciechosak.calendar.utils.today
import kotlinx.coroutines.delay
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun CalendarScreen (
    navController: NavController,
    parentNavController: NavController,
    viewModel: ScheduleViewModel = hiltViewModel()
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())


    LaunchedEffect(Unit) {
        viewModel.getUserBookings(state = "paid")
    }
    val listCalendarBookingResponse = viewModel.UserBookingsResponse.collectAsState().value
    val listCalendarBooking = remember {
        mutableStateOf(listOf<UserBookingDTO>())
    }

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Row (
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BoldText(text = "Lịch Trình")
            }
        }
    ) { innerPadding ->
        when(listCalendarBookingResponse) {
            is Result.Loading -> {
                Log.d("Schedule Screen", "Calendar is loading")
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator()
                }
            }
            is Result.Error -> {
                Log.d("Schedule Screen", "Calendar error")
            }
            is Result.Success -> {
                Log.d("Schedule Screen", "Calendar Success")
                listCalendarBooking.value = listCalendarBookingResponse.data.data
                LazyColumn (
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(horizontal = 10.dp)
                ) {
                    item {
                        BookingCalendar(
                            bookingList = listCalendarBooking.value,
                            navController = parentNavController
                        )
                        YSpacer(height = 5)
                    }
                    item {
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        ) {
                            Row (
                                modifier = Modifier
                                    .fillMaxWidth(0.5f),
                                horizontalArrangement = Arrangement.Start
                            ) {
                                BoldText(text = "Danh sách đặt phòng")
                            }
                            Row (
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                ClickableText(text = "Xem thêm") {
                                    navController.navigate(Screens.ScheduleBookingScreen.name)
                                }
                            }
                        }
                    }
                    items(listCalendarBooking.value) { bookingData ->
                        BookingCard(
                            false,
                            bookingData = bookingData,
                            navController = parentNavController
                        )
                        YSpacer(height = 10)
                    }
                }
            }
            else -> Unit
        }

    }
}
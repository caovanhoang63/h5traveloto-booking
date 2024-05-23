package com.example.h5traveloto_booking.main.presentation.schedule

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.h5traveloto_booking.auth.domain.models.User
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.BookingDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.HotelDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.UserBookingDTO
import com.example.h5traveloto_booking.main.presentation.schedule.components.BookingCalendar
import com.example.h5traveloto_booking.main.presentation.schedule.components.BookingCard
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.share.UserShare
import com.example.h5traveloto_booking.ui_shared_components.ClickableText
import com.example.h5traveloto_booking.ui_shared_components.YSpacer
import com.example.h5traveloto_booking.util.Result
import kotlinx.datetime.LocalDate


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ScheduleScreen (
    navController: NavController,
    viewModel: ScheduleViewModel = hiltViewModel()
) {
    val isHaveBookings = remember {
        mutableStateOf(false)
    }
    val scheduleNavController = rememberNavController()
    val userBookingList: MutableList<UserBookingDTO> = arrayListOf()

//    LaunchedEffect(Unit) {
//        viewModel.getUserBookings(state = "pending")
//    }
//    val UserBookingsResponse = viewModel.UserBookingsResponse.collectAsState().value
//
//    when (UserBookingsResponse) {
//        is Result.Error -> {
//            Log.d("UserBookings ", "Error at UserBookings")
//        }
//        is Result.Success -> {
//            Log.d("UserBookings", "Success")
//            if (UserBookingsResponse.data.data.isEmpty()) {
//                isHaveBookings.value = false
//            } else {
//                UserBookingsResponse.data.data.forEach { userBookingDTO ->
//                    userBookingList.add(userBookingDTO)
//                    Log.d("UserBookings", userBookingDTO.hotel.name)
//                }
//            }
//        }
//        else -> Unit
//    }



    NavHost (
        navController = scheduleNavController,
        startDestination = Screens.ScheduleCalendarScreen.name
    ) {
        composable(route = Screens.ScheduleCalendarScreen.name) {
            CalendarScreen(
                navController = scheduleNavController,
                parentNavController = navController
            )
        }
        composable(route = Screens.ScheduleBookingScreen.name) {
            BookingScreen(
                navController = scheduleNavController,
                parentNavController = navController
            )
        }
    }
}
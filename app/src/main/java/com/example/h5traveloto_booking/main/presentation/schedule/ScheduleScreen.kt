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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.BookingDTO
import com.example.h5traveloto_booking.main.presentation.schedule.components.DateRangePicker
import com.example.h5traveloto_booking.main.presentation.schedule.components.BookingCard
import com.example.h5traveloto_booking.main.presentation.schedule.components.CustomCalendarView
import com.example.h5traveloto_booking.ui_shared_components.PrimaryIconButton
import com.example.h5traveloto_booking.ui_shared_components.YSpacer
import kotlinx.datetime.LocalDate


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ScheduleScreen (navController: NavController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val list = List(3) {}
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
    Scaffold(
        modifier = Modifier.
            nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                ),
                title = {
                    Text(
                        text = "Schedule",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
//                navigationIcon = {
//                    IconButton(onClick = { /*TODO*/ }) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBackIosNew,
//                            contentDescription = "Back things"
//                        )
//                    }
//                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Action things"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        Column (
            modifier = Modifier.
                padding(innerPadding)
        ) {
            DateRangePicker(bookingList)
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding),
            ) {
                items(list) {
                    YSpacer(height = 10)
                    BookingCard()
                    YSpacer(height = 10)
                }
            }
        }
    }
}
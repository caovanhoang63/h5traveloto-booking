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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.material.Text
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.BookingDTO
import com.example.h5traveloto_booking.main.presentation.schedule.components.BookingCard
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.ui_shared_components.BoldText
import com.example.h5traveloto_booking.ui_shared_components.PrimaryIconButton
import com.example.h5traveloto_booking.ui_shared_components.YSpacer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun BookingScreen (
    bookingList: List<BookingDTO>,
    navController: NavController
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val list = List(3) {}

    Scaffold (
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Row (
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box (
                    modifier = Modifier
                        .fillMaxWidth(0.2f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    PrimaryIconButton(
                        DrawableId = R.drawable.backarrow48,
                        onClick = {
                            navController.popBackStack()
                        },
                        alt = ""
                    )
                }
                Box (
                    modifier = Modifier
                        .fillMaxWidth(0.75f),
                    contentAlignment = Alignment.Center
                ) {
                    BoldText(text = "Danh sách đặt phòng")
                }

            }
        }
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 10.dp)
        ) {
            LazyColumn {
                items(list) {
                    BookingCard(true)
                    YSpacer(height = 10)
                }
            }
        }
    }
}
package com.example.h5traveloto_booking.main.presentation.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.h5traveloto_booking.main.presentation.schedule.components.DateRangePicker
import com.example.h5traveloto_booking.main.presentation.schedule.components.HotelCard
import com.example.h5traveloto_booking.ui_shared_components.YSpacer


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ScheduleScreen (navController: NavController) {
    Column (
        Modifier.background(Color.White)
    ) {
        HotelCard()
        YSpacer(height = 20)
        HotelCard()
    }
}
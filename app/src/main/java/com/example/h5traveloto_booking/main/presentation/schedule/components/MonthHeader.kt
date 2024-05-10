package com.example.h5traveloto_booking.main.presentation.schedule.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Icon
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.view.MonthYearPicker
import kotlinx.datetime.Month

@Composable
public fun MonthHeader (
    month : Month,
    year: Int,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit,
    onComplete: (month : Month, year : Int) -> Unit
) {
    val months =
        listOf(
            "Tháng 1",
            "Tháng 2",
            "Tháng 3",
            "Tháng 4",
            "Tháng 5",
            "Tháng 6",
            "Tháng 7",
            "Tháng 8",
            "Tháng 9",
            "Tháng 10",
            "Tháng 11",
            "Tháng 12",
        )
    val showMonthYearPicker = remember {
        mutableStateOf(false)
    }

    if (showMonthYearPicker.value) {
        MonthYearPicker(
            month = month,
            year = year,
            onComplete = { monthSelected, yearSelected ->
                onComplete(monthSelected, yearSelected)
            },
            onDismiss = {
                showMonthYearPicker.value = false
            }
        )
    }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp, 0.dp, 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = onLeftClick) {
            Icon(
                imageVector = Icons.Filled.ArrowBackIosNew,
                tint = Color.Black,
                contentDescription = "Previous Month"
            )
        }
        Row (
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth(0.85f)
        ) {
            Text(
                text = "${months.getOrNull(month.ordinal)} ${year}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        showMonthYearPicker.value = true
                    }
            )
//            BoldText(text = "${months.getOrNull(month.ordinal)} ${year}")
        }
        IconButton(onClick = onRightClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                tint = Color.Black,
                contentDescription = "Continuous Month"
            )
        }
    }
}
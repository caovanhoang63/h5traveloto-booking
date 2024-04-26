package com.example.h5traveloto_booking.main.presentation.schedule.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Icon
import com.example.h5traveloto_booking.ui_shared_components.XSpacer
import kotlinx.datetime.Month

@Composable
public fun MonthHeader (
    month : Month,
    year: Int,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
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
                text = month.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            XSpacer(width = 10)
            Text(
                text = year.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
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
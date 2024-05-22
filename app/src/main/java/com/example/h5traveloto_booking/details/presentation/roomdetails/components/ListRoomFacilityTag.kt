package com.example.h5traveloto_booking.details.presentation.roomdetails.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.h5traveloto_booking.ui_shared_components.XSpacer

@Composable
fun ListRoomFacilityTag(iconID:Int, text: String,color: Color) {
    Row(

        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconID),
            contentDescription = "",
            tint = color,
            modifier = Modifier.size(20.dp)
        )
        XSpacer(width = 8)
        Text(
            text = text,
            style = TextStyle(
                color = color,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            )
        )
    }
}
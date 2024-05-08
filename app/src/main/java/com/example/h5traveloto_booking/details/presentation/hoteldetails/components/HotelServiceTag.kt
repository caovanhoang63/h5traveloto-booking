package com.example.h5traveloto_booking.details.presentation.hoteldetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.h5traveloto_booking.theme.Grey50Color
import com.example.h5traveloto_booking.ui_shared_components.XSpacer

@Composable
fun HotelServiceTag(DrawableId: Int, alt: String, modifier: Modifier = Modifier, text: String,iconColor: Color) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Grey50Color)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = DrawableId),
            contentDescription = alt,
            tint = iconColor,
            modifier = Modifier.size(20.dp)
        )
        XSpacer(width = 8)
        Text(
            text = text,
            style = TextStyle(
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            )
        )
    }
}
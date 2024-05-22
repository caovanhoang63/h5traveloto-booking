package com.example.h5traveloto_booking.ui_shared_components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.h5traveloto_booking.theme.Grey100Color
import com.example.h5traveloto_booking.theme.Grey500Color
import com.example.h5traveloto_booking.theme.SecondaryColor

@Composable
fun GreyText(text : String, modifier: Modifier = Modifier ) {
    Text(
        modifier = Modifier.then(modifier),
        fontSize = 12.sp,
        fontWeight =  FontWeight.Medium,
        color = Grey500Color,
        text = text
    )
}
@Composable
fun GreyText16(text : String, modifier: Modifier = Modifier ) {
    Text(
        modifier = Modifier.then(modifier),
        fontSize = 16.sp,
        fontWeight =  FontWeight.Medium,
        color = Grey500Color,
        text = text
    )
}
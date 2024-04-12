package com.example.h5traveloto_booking.ui_shared_components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun BoldText(text : String ) {
    Text(
        fontSize = 16.sp,
        fontWeight =  FontWeight.Bold,
        text = text
    )
}

@Composable
fun BoldText14(text: String ) {
    Text(
        fontSize = 14.sp,
        fontWeight =  FontWeight.Bold,
        text = text
    )
}
package com.example.h5traveloto_booking.details.presentation.hoteldetails.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun NotFoundReview(){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            text = "Không tìm thấy đánh giá nào",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp),
        )
    }
}
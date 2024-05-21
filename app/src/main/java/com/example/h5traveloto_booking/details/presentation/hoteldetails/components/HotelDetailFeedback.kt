package com.example.h5traveloto_booking.details.presentation.hoteldetails.components

import ExpandingText
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.h5traveloto_booking.theme.Grey50Color

@Composable
fun HotelDetailFeedback(text: String, author: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Grey50Color, //Card background color
        ),
    ) {
        LazyColumn(Modifier.padding(15.dp)){
            item {
                Text(text = author, textAlign = androidx.compose.ui.text.style.TextAlign.End)
                ExpandingText(longText = text, minimizedMaxLines = 3, )
            }
        }

    }
}
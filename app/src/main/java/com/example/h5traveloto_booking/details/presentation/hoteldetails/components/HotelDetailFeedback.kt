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
import androidx.compose.ui.unit.sp
import com.example.h5traveloto_booking.theme.Grey500Color
import com.example.h5traveloto_booking.theme.Grey50Color
import com.example.h5traveloto_booking.theme.PrimaryColor
import com.example.h5traveloto_booking.ui_shared_components.GreyText
import com.example.h5traveloto_booking.ui_shared_components.GreyText16
import com.example.h5traveloto_booking.ui_shared_components.YSpacer

@Composable
fun HotelDetailFeedback(text: String, author: String, rating: String, createdDate: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Grey50Color, //Card background color
        ),
    ) {
        LazyColumn(Modifier.padding(15.dp)) {
            item {
                Text(
                    text = author,
                    textAlign = androidx.compose.ui.text.style.TextAlign.End,
                    style = androidx.compose.ui.text.TextStyle(
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                        fontSize = 20.sp
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        GreyText16(text = "Đánh giá: ")
                        MultiColorText(
                            Triple(rating, PrimaryColor, androidx.compose.ui.text.font.FontWeight.Bold),
                            Triple("/10", Grey500Color, androidx.compose.ui.text.font.FontWeight.Normal)
                        )
                    }
                    GreyText16(text = createdDate)
                }
                ExpandingText(longText = text, minimizedMaxLines = 3)
            }
        }

    }
}
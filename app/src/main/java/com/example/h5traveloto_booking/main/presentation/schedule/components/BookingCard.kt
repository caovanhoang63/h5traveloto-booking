package com.example.h5traveloto_booking.main.presentation.schedule.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.h5traveloto_booking.theme.GreenColor
import com.example.h5traveloto_booking.theme.Grey500Color
import com.example.h5traveloto_booking.theme.PrimaryColor
import com.example.h5traveloto_booking.ui_shared_components.YSpacer

@Composable
public fun BookingCard(
//    hotelName: String,
//    location: String,
//    date: String,
//    price: Int
) {
    Card (
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
//            .clip(shape = RoundedCornerShape(8.dp))
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Row (
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ){
            AsyncImage(
                modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .clip(shape = RoundedCornerShape(4.dp)),
                model = "https://cdn.vietnambiz.vn/2019/11/4/dd32d9b188d86d6d8dc40d1ff9a0ebf6-15728512315071030248829.jpg",
                contentDescription = "Cover Image",
                contentScale = ContentScale.Crop
            )
            Column (
                modifier = Modifier.
//                    background(Color.Red).
                    fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ){
                Row (
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        modifier = Modifier.
                            fillMaxWidth(0.55f),
                        text = "Asteria Hotel",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Row {
                        Text(
                            text = "$" + 164,
                            color = PrimaryColor,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = " /night",
                            color = Grey500Color
                        )
                    }
                }
                YSpacer(height = 5)
                Row {
                    Text(
                        text = "Wilora NT 0872, Australia",
                        color = Grey500Color
                    )
                }
                YSpacer(height = 5)
                Row {
                    Text(
                        text = "19 October 2022",
                        color = Grey500Color,
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                    )
                    Text(
                        text = "Success",
                        color = GreenColor,
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}
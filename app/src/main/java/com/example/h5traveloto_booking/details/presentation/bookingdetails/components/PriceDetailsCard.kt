package com.example.h5traveloto_booking.details.presentation.bookingdetails.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.CreateBookingDTO
import com.example.h5traveloto_booking.theme.Grey100Color
import com.example.h5traveloto_booking.theme.Grey500Color
import com.example.h5traveloto_booking.theme.Grey50Color
import com.example.h5traveloto_booking.ui_shared_components.XSpacer

@Composable
fun PriceDetailsCard (
    bookingData:CreateBookingDTO,
    roomName:String,
    roomPrice:Int,
) {
    val showPriceDetails = remember {
        mutableStateOf(false)
    }
    val angle: Float by animateFloatAsState(
        targetValue = if (showPriceDetails.value) 180f else 0f,
        animationSpec = tween(durationMillis = 300),
        label = "angle"
    )

    Text(
        text = "Chi tiết giá tiền",
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(12.dp)
    )
    Card (
        colors = CardDefaults.cardColors(
            containerColor = Grey50Color,
            contentColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp, 0.dp, 12.dp, 0.dp)
    ) {
        Row {
            Row (
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(12.dp)
                    .clickable(
                        onClick = {
                            showPriceDetails.value = !showPriceDetails.value
                        }
                    )
            ) {
                Image(
                    painter = painterResource(R.drawable.arrowdown),
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp)
                        .graphicsLayer {
                            rotationZ = angle
                            transformOrigin = TransformOrigin(0.5f, 0.5f)
                        }
                )
                XSpacer(10)
                Text(
                    text = "Tổng tiền",
                    fontSize = 16.sp
                )
            }
            Text(
                text = "${bookingData.price?.toLong()} VND",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                textAlign = TextAlign.End
            )
        }
        if (showPriceDetails.value) {
            Divider(
                modifier = Modifier
                    .fillMaxWidth(),
                color = Grey100Color,
                thickness = 1.dp
            )
            Column {
                ObjectAndPrice(
                    text = "(${bookingData.roomQuantity}x) ${roomName}",
                    price = bookingData.price?.toLong() ?: 0
                )
            }
        }
    }
}

@Composable
fun ObjectAndPrice (
    text: String,
    price: Long
) {
    Row (
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = Grey500Color,
            modifier = Modifier
                .fillMaxWidth(0.5f)
        )
        Text(
            text = "${price.toString()} VND",
            fontSize = 14.sp,
            color = Grey500Color,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
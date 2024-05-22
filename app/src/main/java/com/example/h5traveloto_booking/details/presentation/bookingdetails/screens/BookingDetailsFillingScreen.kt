package com.example.h5traveloto_booking.details.presentation.bookingdetails.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.details.presentation.bookingdetails.components.BookingSummaryCard
import com.example.h5traveloto_booking.details.presentation.bookingdetails.components.ContactDetailsCard
import com.example.h5traveloto_booking.details.presentation.bookingdetails.components.PriceDetailsCard
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.CreateBookingDTO
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.share.UserShare
import com.example.h5traveloto_booking.theme.*
import com.example.h5traveloto_booking.ui_shared_components.PrimaryIconButton
import com.example.h5traveloto_booking.ui_shared_components.XSpacer
import com.example.h5traveloto_booking.ui_shared_components.YSpacer

@Composable
fun BookingDetailsFillingScreen(
    navController: NavController,
    parentNavController: NavController,
    bookingData: CreateBookingDTO
) {
    Scaffold(
        topBar = {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.2f)
                ) {
                    PrimaryIconButton(
                        DrawableId = R.drawable.backbutton,
                        onClick = { parentNavController.popBackStack() },
                        alt = ""
                    )
                }
                Text(
                    text = "Điền thông tin đặt phòng",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                )

            }
        },
        bottomBar = {
            Button(
                onClick = { navController.navigate(Screens.BookingDetailsReviewScreen.name) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(horizontal = 24.dp, vertical = 10.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(OrangeColor)
            ) {
                Text(
                    text = "Tiếp tục",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    ) { innerPadding ->
        LazyColumn (
            modifier = Modifier
                .padding(innerPadding)
        ) {
            item {
                Row (
                    modifier = Modifier
                        .background(color = Grey50Color)
                        .fillMaxWidth()
                        .padding(24.dp, 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.user),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(30.dp)
                    )
                    XSpacer(20)
                    Column {
                        Text(
                            "Đăng nhập bởi ${UserShare.User.lastName} ${UserShare.User.firstName}",
                            fontSize = 16.sp
                        )
                        if (!UserShare.User.phone.isNullOrEmpty()) {
                            Text(
                                "${UserShare.User.phone}",
                                color = Grey500Color,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
            item { YSpacer(20) }
            item {
                BookingSummaryCard(bookingData)
            }
            item { YSpacer(20) }
            item {
                ContactDetailsCard()
            }
            item { YSpacer(20) }
            item {
                PriceDetailsCard()
            }
        }
    }
}
package com.example.h5traveloto_booking.payment.PaymentFailed

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.CreateBookingDTO
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.share.shareDataHotelDetail
import com.example.h5traveloto_booking.ui_shared_components.BoldText
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentFailedScreen(navController: NavController) {
    val bookingData = CreateBookingDTO(
        hotelId = shareDataHotelDetail.getHotelId(),
        roomTypeId = shareDataHotelDetail.getRoomTypeId(),
        roomQuantity = shareDataHotelDetail.getRoomQuantity(),
        adults = shareDataHotelDetail.getAdults(),
        children = shareDataHotelDetail.getChildren(),
        startDate = shareDataHotelDetail.getStartDateString(),
        endDate = shareDataHotelDetail.getEndDateString()
    )
    Scaffold(
        topBar = {
            Row(
                Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                BoldText(
                    text = "Thanh toán thất bại",
                    //  fontWeight = FontWeight.Bold,
                    // fontSize = 20.sp)
                )
            }
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Thanh toán của bạn đã thất bại!",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = {

                        navController.popBackStack(route = "${Screens.BookingDetailsReviewScreen.name}/${Gson().toJson(bookingData)}",
                            inclusive = true)
                    }
                    ) {
                        Text("Thử lại")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = {

                        navController.popBackStack(route = Screens.MainScreen.name,
                            inclusive = false)
                    }
                    ) {
                        Text("Trở về trang chủ")
                    }
                }
            }
        }
    )
}
package com.example.h5traveloto_booking.details.presentation.bookingdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.theme.*
import com.example.h5traveloto_booking.ui_shared_components.BoldText
import com.example.h5traveloto_booking.ui_shared_components.PrimaryIconButton
import com.example.h5traveloto_booking.ui_shared_components.XSpacer
import com.example.h5traveloto_booking.ui_shared_components.YSpacer
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton

@Composable
fun BookingDetailsFillingScreen(
    navController: NavController,

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
                        onClick = { navController.popBackStack() },
                        alt = ""
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                ) {
                    Text(
                        text = "Điền thông tin đặt phòng",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
        },
        bottomBar = {
            Button(
                onClick = { navController.navigate(Screens.ListRooms.name) },
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
        Column (
            modifier = Modifier
                .padding(innerPadding)
        ) {
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
                        "Đăng nhập bởi Mai Hoàng Hưng",
                        fontSize = 16.sp
                    )
                    Text(
                        "+84012345678",
                        color = Grey500Color,
                        fontSize = 14.sp
                    )
                }
            }
            YSpacer(20)
            BookingSummaryCard()
            YSpacer(20)
            ContactDetailsCard()
            YSpacer(20)
            PriceDetailsCard()
        }
    }
}
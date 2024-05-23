package com.example.h5traveloto_booking.details.presentation.bookingdetails.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.CreateBookingDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchHotel.*
import com.example.h5traveloto_booking.share.ShareDataHotelDetail
import com.example.h5traveloto_booking.share.shareDataHotelDetail
import com.example.h5traveloto_booking.theme.*
import com.example.h5traveloto_booking.ui_shared_components.BoldText
import com.example.h5traveloto_booking.ui_shared_components.BoldText14
import com.example.h5traveloto_booking.ui_shared_components.XSpacer
import com.squareup.moshi.Json

@Composable
fun BookingSummaryCard (
    bookingData: CreateBookingDTO
) {
    val hotelInfor = shareDataHotelDetail.getHotelDetails()
    val dayOfWeek = listOf(
        "Thứ 2",
        "Thứ 3",
        "Thứ 4",
        "Thứ 5",
        "Thứ 6",
        "Thứ 7",
        "Chủ nhật"
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
        Column (
            modifier = Modifier
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row {
                Icon(
                    painter = painterResource(R.drawable.hotel),
                    contentDescription = "",
                    tint = PrimaryColor,
                    modifier = Modifier.size(20.dp)
                )
                XSpacer(10)
                BoldText(hotelInfor!!.name)
            }
            Row {
                Text(
                    text = "Check-in",
                    fontSize = 14.sp
                )
                XSpacer(20)
                Text(
//                    text = "Thứ 2, 20-5-2024 (14:00)",
                    text = "${dayOfWeek.getOrNull(shareDataHotelDetail.getStartDate().dayOfWeek.ordinal)}, ${shareDataHotelDetail.getStartDateString()} (14:00)",
                    fontSize = 14.sp
                )
            }
            Row {
                Text(
                    text = "Check-out",
                    fontSize = 14.sp
                )
                XSpacer(20)
                Text(
                    text = "${dayOfWeek.getOrNull(shareDataHotelDetail.getEndDate().dayOfWeek.ordinal)}, ${shareDataHotelDetail.getEndDateString()} (12:00)",
                    fontSize = 14.sp
                )
            }
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth(),
            color = Grey100Color,
            thickness = 1.dp
        )
        Column (
            modifier = Modifier
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = "(1x) Phòng vjp kinh",
                fontSize = 14.sp
            )
            Text(
                text = "Có bữa sáng",
                fontSize = 14.sp,
                color = DarkGreenColor
            )
            Text(
                text = "1 king bed",
                fontSize = 14.sp,
                color = Grey500Color
            )
            Text(
                text = "2 Adult(s) / room",
                fontSize = 14.sp,
                color = Grey500Color
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth(),
            color = Grey100Color,
            thickness = 1.dp
        )
        Column (
            modifier = Modifier
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row {
                Icon(
                    painter = painterResource(R.drawable.check),
                    contentDescription = "",
                    tint = SecondaryColor,
                    modifier = Modifier.size(20.dp)
                )
                XSpacer(10)
                Text(
                    text = "Thanh toán tại khách sạn",
                    fontSize = 14.sp,
                    color = SecondaryColor,
                )
            }
            Row {
                Icon(
                    painter = painterResource(R.drawable.not_apply),
                    contentDescription = "",
                    tint = Grey500Color,
                    modifier = Modifier.size(20.dp)
                )
                XSpacer(10)
                Text(
                    text = "Được hủy phòng",
                    fontSize = 14.sp,
                )
            }
        }
    }
}
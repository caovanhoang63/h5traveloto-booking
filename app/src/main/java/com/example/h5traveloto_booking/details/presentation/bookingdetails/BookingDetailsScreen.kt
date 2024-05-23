package com.example.h5traveloto_booking.details.presentation.bookingdetails

import androidx.compose.animation.core.animateValueAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.details.presentation.bookingdetails.components.ObjectAndPrice
import com.example.h5traveloto_booking.details.presentation.bookingdetails.screens.BookingDetailsScreenViewModel
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.BookingDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.UserBookingDTO
import com.example.h5traveloto_booking.share.UserShare
import com.example.h5traveloto_booking.share.shareDataHotelDetail
import com.example.h5traveloto_booking.theme.*
import com.example.h5traveloto_booking.ui_shared_components.PrimaryIconButton
import com.example.h5traveloto_booking.ui_shared_components.XSpacer
import com.example.h5traveloto_booking.ui_shared_components.YSpacer
import kotlinx.datetime.minus
import kotlinx.datetime.plus

@Composable
fun BookingDetailsScreen (
    userBookingData: UserBookingDTO,
    navController: NavController,
) {
    shareDataHotelDetail.setHotelId(userBookingData.hotel.id)
    shareDataHotelDetail.setRoomTypeId(userBookingData.roomTypeId)
//    val roomInfo = shareDataHotelDetail.getRoomDTO()
    Scaffold (
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
                Text(
                    text = "Chi tiết đặt phòng",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                )

            }
        },
        bottomBar = {

        }
    ) { innerPadding ->
        LazyColumn (
            modifier = Modifier
                .padding(innerPadding)
        ) {
            item {
                Column (
                    modifier = Modifier
                        .background(color = Grey50Color)
                        .fillMaxWidth()
                        .padding(24.dp, 24.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = userBookingData.hotel.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth(),
                        color = Grey100Color,
                        thickness = 2.dp
                    )
                    Row (
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column (
                            verticalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Text(
                                text = "Check-in",
                                color = Grey500Color,
                                fontSize = 14.sp
                            )
                            Text(
                                text = "Thứ ${shareDataHotelDetail.getStartDate().dayOfWeek.value}, ${shareDataHotelDetail.getStartDate().dayOfMonth} Thg ${shareDataHotelDetail.getStartDate().monthNumber}",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = shareDataHotelDetail.getCheckInTime(),
                                color = Grey500Color,
                                fontSize = 14.sp
                            )
                        }
                        Column (
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.moon),
                                contentDescription = "",
                                tint = PrimaryColor,
                                modifier = Modifier
                                    .size(30.dp)
                            )
                            YSpacer(20)
                            Text(
                                text = "${shareDataHotelDetail.getEndDate().minus(shareDataHotelDetail.getStartDate()).days} night(s)",
                                color = Grey500Color,
                                fontSize = 14.sp
                            )
                        }
                        Column (
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(
                                text = "Check-in",
                                color = Grey500Color,
                                fontSize = 14.sp,
                                textAlign = TextAlign.End
                            )
                            Text(
                                text = "Thứ ${shareDataHotelDetail.getEndDate().dayOfWeek.value}, ${shareDataHotelDetail.getEndDate().dayOfMonth} Thg ${shareDataHotelDetail.getEndDate().monthNumber}",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.End
                            )
                            Text(
                                text = shareDataHotelDetail.getCheckOutTime(),
                                fontSize = 14.sp,
                                color = Grey500Color,
                                textAlign = TextAlign.End
                            )
                        }
                    }
                }
            }
            item {
                YSpacer(20)
            }
            item {
                Column (
                    modifier = Modifier
                        .background(color = Grey50Color)
                        .fillMaxWidth()
                        .padding(24.dp, 24.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
//                        text = "(${userBookingData.roomQuantity}x) ${roomInfo.name}",
                        text = "(${userBookingData.roomQuantity}x) Ten phong",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Row (
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Loại giường",
                            fontSize = 14.sp,
                            color = Grey500Color,
                            modifier = Modifier
                                .fillMaxWidth(0.3f)
                        )
                        Column (
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
//                            if (roomInfo.bed.double > 0) {
//                                Text(
//                                    text = "${roomInfo.bed.double} double bed",
//                                    fontSize = 14.sp,
//                                )
//                            }
//                            if (roomInfo.bed.king > 0) {
//                                Text(
//                                    text = "${roomInfo.bed.king} king bed",
//                                    fontSize = 14.sp,
//                                )
//                            }
//                            if (roomInfo.bed.queen > 0) {
//                                Text(
//                                    text = "${roomInfo.bed.queen} queen bed",
//                                    fontSize = 14.sp,
//                                )
//                            }
//                            if (roomInfo.bed.single > 0) {
//                                Text(
//                                    text = "${roomInfo.bed.single} single bed",
//                                    fontSize = 14.sp,
//                                )
//                            }
                            Text(
                                text = "1 double bed",
                                fontSize = 14.sp,
                            )
                        }

                    }
                    Row (
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Số lượng người",
                            fontSize = 14.sp,
                            color = Grey500Color,
                            modifier = Modifier
                                .fillMaxWidth(0.3f)
                        )
                        Column (
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
//                            Text(
//                                text = "${roomInfo.maxCustomer} người/phòng",
//                                fontSize = 14.sp,
//                            )
                            Text(
                                text = "1 nguoi / phong",
                                fontSize = 14.sp
                            )
                            Text(
                                text = "(${userBookingData.adults} người lớn, ${userBookingData.children} trẻ em trong ${userBookingData.roomQuantity} phòng)",
                                fontSize = 14.sp,
                            )
                        }
                    }
                    Row (
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth(0.3f)
                                .heightIn(0.dp, 80.dp)
                                .clip(shape = RoundedCornerShape(4.dp)),
//                            model = if (roomInfo.images != null && roomInfo.images.isNotEmpty()) roomInfo.images[0].url else R.drawable.default_img,
                            model = R.drawable.default_img,
                            contentDescription = "Cover Image",
                            contentScale = ContentScale.Crop
                        )
                        Column (
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Row {
                                Icon(
                                    painter = painterResource(R.drawable.lunch),
                                    contentDescription = "",
                                    tint = DarkGreenColor,
                                    modifier = Modifier
                                        .size(20.dp)
                                )
                                XSpacer(10)
                                Text(
                                    text = "Bữa sáng miễn phí",
                                    color = DarkGreenColor,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
            }
            item {
                YSpacer(20)
            }
            item {
                Column (
                    modifier = Modifier
                        .background(color = Grey50Color)
                        .fillMaxWidth()
                        .padding(24.dp, 24.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Thông tin khách hàng",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Column (
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = "Tên khách hàng",
                            color = Grey500Color,
                            fontSize = 14.sp
                        )
                        Row {
                            Icon(
                                painter = painterResource(R.drawable.useralt),
                                contentDescription = "",
                                tint = Grey500Color,
                                modifier = Modifier
                                    .size(20.dp)
                            )
                            XSpacer(10)
                            Text(
                                text = "${UserShare.User.lastName.toString()} ${UserShare.User.firstName.toString()}",
                                fontSize = 16.sp
                            )
                        }
                    }
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth(),
                        color = Grey100Color,
                        thickness = 2.dp
                    )
                    Column (
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = "Email",
                            color = Grey500Color,
                            fontSize = 14.sp
                        )
                        Row {
                            Icon(
                                painter = painterResource(R.drawable.email),
                                contentDescription = "",
                                tint = Grey500Color,
                                modifier = Modifier
                                    .size(20.dp)
                            )
                            XSpacer(10)
                            Text(
                                text = UserShare.User.email.toString(),
                                fontSize = 16.sp
                            )
                        }
                    }
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth(),
                        color = Grey100Color,
                        thickness = 2.dp
                    )
                    Column (
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = "Số điện thoại",
                            color = Grey500Color,
                            fontSize = 14.sp
                        )
                        Row {
                            Icon(
                                painter = painterResource(R.drawable.telephone),
                                contentDescription = "",
                                tint = Grey500Color,
                                modifier = Modifier
                                    .size(20.dp)
                            )
                            XSpacer(10)
                            Text(
                                text = UserShare.User.phone.toString(),
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
            item {
                YSpacer(20)
            }
            item {
                Column (
                    modifier = Modifier
                        .background(color = Grey50Color)
                        .fillMaxWidth()
                        .padding(24.dp, 24.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Tổng tiền",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    ObjectAndPrice(
//                        text = "(${userBookingData.roomQuantity}x) ${roomInfo.name}",
                        text = "(${userBookingData.roomQuantity}x) Ten phong",
//                        price = roomInfo.price.toLong() * userBookingData.roomQuantity
                        price = (1000000 * userBookingData.roomQuantity).toLong()
                    )
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth(),
                        color = Grey100Color,
                        thickness = 2.dp
                    )
                    Row (
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Total",
                            fontSize = 16.sp,
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                        )
                        Text(
                            text = "${userBookingData.totalAmount} VND",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}
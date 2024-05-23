package com.example.h5traveloto_booking.details.presentation.bookingdetails.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.details.presentation.bookingdetails.BookingScreenViewModel
import com.example.h5traveloto_booking.details.presentation.bookingdetails.components.ObjectAndPrice
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.CreateBookingDTO
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.theme.*
import com.example.h5traveloto_booking.ui_shared_components.PrimaryIconButton
import com.example.h5traveloto_booking.ui_shared_components.XSpacer
import com.example.h5traveloto_booking.ui_shared_components.YSpacer

@Composable
fun BookingReviewScreen (
    navController: NavController,
    bookingData: CreateBookingDTO,
    viewModel: BookingScreenViewModel = hiltViewModel()
) {
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
                    text = "Kiểm tra thông tin",
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
                onClick = {
                    viewModel.createBooking(bookingData)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(horizontal = 24.dp, vertical = 10.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(OrangeColor)
            ) {
                Text(
                    text = "Thanh toán",
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
                Column (
                    modifier = Modifier
                        .background(color = Grey50Color)
                        .fillMaxWidth()
                        .padding(24.dp, 24.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Đâu cũng được hotel",
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
                                text = "Thứ 2, 20 tháng 5",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "14:00",
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
                                text = "1 night(s)",
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
                                text = "Thứ 3, 21 tháng 5",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.End
                            )
                            Text(
                                text = "12:00",
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
                        text = "(1x) Phòng vjp kinh",
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
                        Text(
                            text = "1 king bed",
                            fontSize = 14.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
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
                            Text(
                                text = "2 người/phòng",
                                fontSize = 14.sp,
                            )
                            Text(
                                text = "(tổng cộng 2 người trong 1 phòng)",
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
                            model = "https://cdn.vietnambiz.vn/2019/11/4/dd32d9b188d86d6d8dc40d1ff9a0ebf6-15728512315071030248829.jpg",
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
                                text = "Mai Hoang Hung",
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
                                text = "mhhung0811@gmai.com",
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
                                text = "012345678",
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
                        text = "(1x) Phòng vjp kinh",
                        price = 1000000
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
                            text = "1000000 VND",
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
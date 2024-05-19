package com.example.h5traveloto_booking.details.presentation.hoteldetails

import ExpandingText
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.account.ListHotelsViewModel
import com.example.h5traveloto_booking.details.presentation.hoteldetails.components.*

import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.theme.Grey500Color
import com.example.h5traveloto_booking.theme.PrimaryColor
import com.example.h5traveloto_booking.ui_shared_components.*
import com.example.h5traveloto_booking.util.Result
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton

@SuppressLint("InvalidColorHexValue")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotelDetailsScreen(navController: NavController, viewModel: HotelDetailsScreenViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.getHotelDetails()
    }
    val HotelDetailsResponse = viewModel.HotelDetailsResponse.collectAsState().value

    when (HotelDetailsResponse) {/*is Result.Loading -> {
            Log.d("List Hotel ", "dang load")

            // Hieu ung load
            Box( contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()

            }
        }*/

        is Result.Error -> {
            Log.d("HotelDetails ", "loi roi")
        }

        is Result.Success -> {
            Log.d("HotelDetails", HotelDetailsResponse.data.data.description)
        }

        else -> Unit
    }

    val imgURL =
        "https://media.istockphoto.com/id/104731717/photo/luxury-resort.jpg?s=612x612&w=0&k=20&c=cODMSPbYyrn1FHake1xYz9M8r15iOfGz9Aosy9Db7mI="
    Scaffold(
        bottomBar = {
            PrimaryButton(
                onClick = { navController.navigate(Screens.ListRooms.name) },
                text = "Chọn Phòng",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(horizontal = 24.dp, vertical = 10.dp)
            )
        },
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PrimaryIconButton(
                    DrawableId = R.drawable.backbutton, onClick = { navController.popBackStack() }, alt = ""
                )
                BoldText(text = "Chi tiết khách sạn")
                PrimaryIconButton(
                    DrawableId = R.drawable.message_circle,
                    onClick = { navController.navigate(Screens.ChatScreen.name) },
                    alt = ""
                )
            }
        },

        ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp),
            ) {
                item {
                    Box(
                        contentAlignment = Alignment.TopEnd,
                    ) {
                        AsyncImage(
                            model = imgURL,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(246.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop,
                        )
                        IconButton(modifier = Modifier
                            .padding(8.dp)
                            .offset(x = (-8).dp, y = 8.dp)
                            .width(32.dp)
                            .height(32.dp)
                            .background(
                                color = Color.White, shape = RoundedCornerShape(50.dp)
                            ), onClick = { /*TODO*/ }, content = {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "favourite",
                                tint = Color.Red,
                            )
                        })
                    }

                    YSpacer(height = 16)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(36.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(0.8f),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            items(1) {

                                HotelServiceTag(
                                    DrawableId = R.drawable.wifi,
                                    alt = "wifi",
                                    text = "Free Wifi",
                                    iconColor = Color.Black
                                )
                                HotelServiceTag(
                                    DrawableId = R.drawable.coffee,
                                    alt = "coffee",
                                    text = "Free Breakfast",
                                    iconColor = Color.Black
                                )
                                HotelServiceTag(
                                    DrawableId = R.drawable.wifi,
                                    alt = "wifi",
                                    text = "Free Wifi",
                                    iconColor = Color.Black
                                )
                                HotelServiceTag(
                                    DrawableId = R.drawable.coffee,
                                    alt = "coffee",
                                    text = "Free Breakfast",
                                    iconColor = Color.Black
                                )
                                HotelServiceTag(
                                    DrawableId = R.drawable.wifi,
                                    alt = "wifi",
                                    text = "Free Wifi",
                                    iconColor = Color.Black
                                )
                                HotelServiceTag(
                                    DrawableId = R.drawable.coffee,
                                    alt = "coffee",
                                    text = "Free Breakfast",
                                    iconColor = Color.Black
                                )
                            }
                        }
//
                        HotelServiceTag(
                            DrawableId = R.drawable.star,
                            alt = "rating",
                            text = "5.0",
                            iconColor = Color(0xffffe234)
                        )
                    }
                    YSpacer(height = 16)
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        BoldText(text = "Khách sạn Palace Sài Gòn")
                        MultiColorText(
                            Triple("$165.3", PrimaryColor, FontWeight.Bold),
                            Triple(" /đêm", Grey500Color, FontWeight.Normal)
                        )
                    }
                    YSpacer(height = 8)
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.location),
                            contentDescription = "Location",
                            contentScale = ContentScale.Crop
                        )
                        XSpacer(width = 10)
                        GreyText(text = "56 - 66 Nguyễn Huệ, Bến Nghé, Quận 1")
                    }
                    YSpacer(height = 16)
                    BoldText(text = "Mô Tả Khách Sạn")
                    YSpacer(height = 12)
                    ExpandingText(
                        longText = "Palace Hotel Saigon là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú. Hãy sẵn sàng đón nhận những giây phút vô giá khó phai trong suốt kỳ nghỉ của quý khách tại Palace Hotel Saigon."
                    )
                    YSpacer(height = 16)
                    HorizontalDivider(color = Color.Black, thickness = 0.1.dp)
                    YSpacer(height = 8)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        BoldText(text = "Đánh Giá")
                        PrimaryText(text = "Xem tất cả", modifier = Modifier.clickable { })
                    }
                    YSpacer(height = 12)
                    HotelDetailFeedback(
                        text = "The Aston Vill Hotel is a 5-star hotel located in the heart of the city. The hotel is a 5-minute walk from the city center and a 10-minute walk from the beach. The hotel offers a variety of amenities, including a spa, fitness center, and swimming pool. The hotel also has a restaurant and bar, where guests can enjoy a variety of dishes and drinks.",
                        author = "John Doe"
                    )
                    YSpacer(height = 16)
                    HorizontalDivider(color = Color.Black, thickness = 0.1.dp)
                    YSpacer(height = 8)
                    BoldText(text = "Hình Ảnh Xem Trước")
                    YSpacer(height = 8)
                    LazyRow(modifier = Modifier.fillMaxWidth()) {
                        item {
                            AsyncImage(
                                model = imgURL,
                                contentDescription = null,
                                modifier = Modifier
                                    .width(98.dp)
                                    .height(82.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop,
                            )
                            XSpacer(width = 16)
                            AsyncImage(
                                model = imgURL,
                                contentDescription = null,
                                modifier = Modifier
                                    .width(98.dp)
                                    .height(82.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop,
                            )
                            XSpacer(width = 16)
                            AsyncImage(
                                model = imgURL,
                                contentDescription = null,
                                modifier = Modifier
                                    .width(98.dp)
                                    .height(82.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop,
                            )
                            XSpacer(width = 16)
                            AsyncImage(
                                model = imgURL,
                                contentDescription = null,
                                modifier = Modifier
                                    .width(98.dp)
                                    .height(82.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop,
                            )
                            XSpacer(width = 16)
                            AsyncImage(
                                model = imgURL,
                                contentDescription = null,
                                modifier = Modifier
                                    .width(98.dp)
                                    .height(82.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop,
                            )

                        }

                    }
                    YSpacer(height = 16)
                    HorizontalDivider(color = Color.Black, thickness = 0.1.dp)
                    YSpacer(height = 8)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        BoldText(text = "Chính Sách Lưu Trú")
                        PrimaryText(text = "Xem tất cả", modifier = Modifier.clickable { })
                    }
                    YSpacer(height = 8)
                    HotelDetailPolicyCard(
                        icon = Icons.Default.Money,
                        text = "Tiền cọc",
                        description = "Bạn phải đóng tiền cọc 0 khi nhận phòng. Cơ sở lưu trú chấp nhận tiền mặt, thẻ ghi nợ hoặc thẻ tín dụng"
                    )
                    YSpacer(height = 10)

                }
            }
        }
    }
}
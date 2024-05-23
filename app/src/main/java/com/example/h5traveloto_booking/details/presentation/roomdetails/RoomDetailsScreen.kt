package com.example.h5traveloto_booking.details.presentation.roomdetails

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.details.presentation.roomdetails.components.RoomDetailTag
import com.example.h5traveloto_booking.details.presentation.roomdetails.components.RoomFacilitiesList
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.CreateBookingDTO
import com.example.h5traveloto_booking.main.presentation.favorite.AllFavorite.formatPrice
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.share.shareDataHotelDetail
import com.example.h5traveloto_booking.ui_shared_components.*
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton
import com.example.h5traveloto_booking.util.Result
import com.google.gson.Gson

@Composable
fun RoomDetailsScreen(
    navController: NavController,
    viewModel: RoomDetailsScreenViewModel = hiltViewModel(),
    Object: com.example.h5traveloto_booking.main.presentation.data.dto.SearchRoomType.Data,
) {

    val bookingData = CreateBookingDTO(
        hotelId = shareDataHotelDetail.getHotelId(),
        roomTypeId = shareDataHotelDetail.getRoomTypeId(),
        roomQuantity = shareDataHotelDetail.getRoomQuantity(),
        adults = shareDataHotelDetail.getAdults(),
        children = shareDataHotelDetail.getChildren(),
        startDate = shareDataHotelDetail.getStartDateString(),
        endDate = shareDataHotelDetail.getEndDateString()
    )
    Log.d("booking", bookingData.hotelId)
    Log.d("booking", bookingData.roomTypeId)
    Log.d("booking", bookingData.roomQuantity.toString())
    Log.d("booking", bookingData.adults.toString())
    Log.d("booking", bookingData.children.toString())
    Log.d("booking", bookingData.startDate)
    Log.d("booking", bookingData.endDate)


    LaunchedEffect(Unit) {
        viewModel.getRoomFacilitiesDetails(Object.id)
    }
    val roomFacilitiesDetailsResponse = viewModel.RoomFacilitiesDetailsResponse.collectAsState().value
//    val Object = shareDataHotelDetail.getRoomDTO()
    var imageOpen by remember {
        mutableStateOf(false)
    }
    var imageURL by remember {
        mutableStateOf("")
    }
    when(imageOpen){
        true -> {
            DialogWithImage(onDismissRequest = {imageOpen = false}, imageURL = imageURL)
        }
        false -> {}
    }
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    PrimaryIconButton(
                        DrawableId = R.drawable.backbutton, onClick = { navController.popBackStack() }, alt = ""
                    )
                    BoldText2(text = "Chi tiết Phòng", modifier = Modifier.align(Alignment.Center))

                }

            }
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    GreyText(text = "Giá/phòng/đêm từ")
                    PrimaryText20(
                        text = "${Object.price.formatPrice()} VND",
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    )
                    BoldText(text = "Đã bao gồm thuế")
                }

                PrimaryButton(
                    onClick = {

                        navController.navigate("${Screens.BookingScreen.name}/${Gson().toJson(bookingData)}")
                    },
                    text = "Đặt Phòng",
                    modifier = Modifier
                        .width(150.dp)
                        .height(65.dp)
                )
            }

        },
    ) { innerpadding ->
        Column(
            modifier = Modifier.padding(innerpadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp),
            ) {
                item {
                    if (Object.images?.isNotEmpty() == true) {
                        LazyRow() {
                            items(Object.images?.map { it.url } ?: emptyList()) { url ->
                                AsyncImage(
                                    model = url,
                                    contentDescription = "Room Image",
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier
                                        .width(400.dp)
                                        .height(200.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .clickable { imageOpen = true; imageURL = url!!}

                                )
                                XSpacer(width = 10)
                            }
                        }
                    }
                    else {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.LightGray),
                            contentAlignment = Alignment.Center

                        ){
                            Text(text = "Không có hình ảnh xem trước")
                        }
                    }
                    YSpacer(height = 10)
                    HorizontalDivider(thickness = 0.8.dp, color = Color.LightGray)
                    YSpacer(height = 10)


                    BoldText24(text = Object.name)
                    YSpacer(height = 10)
                    HorizontalDivider(thickness = 0.8.dp, color = Color.LightGray)
                    YSpacer(height = 10)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        RoomDetailTag(
                            icon = R.drawable.baseline_people_24,
                            text = "Khách",
                            description = "${Object.maxCustomer} Người lớn"
                        )
                        RoomDetailTag(
                            icon = R.drawable.baseline_business_24,
                            text = "Kích thước Phòng",
                            description = "${Object.area} m2"
                        )
                    }


                    when (roomFacilitiesDetailsResponse) {
                        is Result.Loading -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }

                        is Result.Idle -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "Idle")
                            }
                        }

                        is Result.Error -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = roomFacilitiesDetailsResponse.error)
                            }
                        }

                        is Result.Success -> {
                            Log.d("RoomFacilitiesDetails View", roomFacilitiesDetailsResponse.data.toString())
                            YSpacer(height = 10)
                            HorizontalDivider(thickness = 0.8.dp, color = Color.LightGray)
                            YSpacer(height = 10)
                            BoldText20(text = "Tiện ích phòng")
                            RoomFacilitiesList(
                                items = roomFacilitiesDetailsResponse.data.data.map { data ->
                                    data.nameVn
                                },
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal,
                                ),
                                lineSpacing = 8.dp,
                            )
                        }

                    }

                    if (Object.freeCancel == 0) {
                        YSpacer(height = 10)
                        HorizontalDivider(thickness = 0.8.dp, color = Color.LightGray)
                        YSpacer(height = 10)


                        BoldText20(text = "Đổi lịch & Hủy phòng")
                        GreyText16(text = "Đặt phòng này không thể hoàn tiền và không thể đổi lịch ")

                    }
                    YSpacer(height = 10)
                    HorizontalDivider(thickness = 0.8.dp, color = Color.LightGray)
                    YSpacer(height = 10)

                    BoldText20(text = "Về Phòng này")
                    GreyText16(text = Object.description)

                }
            }
        }
    }
}
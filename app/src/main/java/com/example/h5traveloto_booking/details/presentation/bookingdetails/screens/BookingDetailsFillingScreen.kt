package com.example.h5traveloto_booking.details.presentation.bookingdetails.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
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
import com.example.h5traveloto_booking.util.Result
import com.google.gson.Gson

@Composable
fun BookingDetailsFillingScreen(
    navController: NavController,
    bookingData: CreateBookingDTO,
    viewModel: BookingDetailsScreenViewModel = hiltViewModel(),
) {


    LaunchedEffect(Unit) {
        viewModel.getRoomTypeById(bookingData.roomTypeId)
        viewModel.getRoomTypeFacility(bookingData.roomTypeId)
    }

    val isLoadingRoomType = remember {
        mutableStateOf(true)
    }
    val isLoadingRoomTypeFacility = remember {
        mutableStateOf(true)
    }

    val roomTypeResponse = viewModel.RoomType.collectAsState().value
    val roomTypeFacilityResponse = viewModel.RoomTypeFacility.collectAsState().value

    var roomName by remember {
        mutableStateOf("")
    }
    var roomBedDouble by remember {
        mutableStateOf(0)
    }
    var roomBedKing by remember {
        mutableStateOf(0)
    }
    var roomBedQueen by remember {
        mutableStateOf(0)
    }
    var roomBedSingle by remember {
        mutableStateOf(0)
    }
    var roomMaxCustomer by remember {
        mutableStateOf(0)
    }
    var roomImages by remember {
        mutableStateOf(listOf<com.example.h5traveloto_booking.details.presentation.data.dto.roomtypebyid.Image>())
    }
    var roomPrice by remember {
        mutableStateOf(0)
    }
    var roomFreeCancel by remember {
        mutableStateOf(false)
    }
    var roomBreakFast by remember {
        mutableStateOf(false)
    }
    var roomPayInHotel by remember {
        mutableStateOf(false)
    }


    when (roomTypeResponse) {
        is Result.Loading -> {
            Log.d("BookingDetails Screen", "RoomType is loading")
        }
        is Result.Error -> {
            Log.d("BookingDetails Screen", "RoomType error")
        }
        is Result.Success -> {
            Log.d("BookingDetails Screen", "RoomType success")
            roomName = roomTypeResponse.data.data.name
            roomBedDouble = roomTypeResponse.data.data.bed.double
            roomBedKing = roomTypeResponse.data.data.bed.king
            roomBedQueen = roomTypeResponse.data.data.bed.queen
            roomBedSingle = roomTypeResponse.data.data.bed.single
            roomMaxCustomer = roomTypeResponse.data.data.maxCustomer
            roomImages = if (roomTypeResponse.data.data.images != null) roomTypeResponse.data.data.images
            else listOf<com.example.h5traveloto_booking.details.presentation.data.dto.roomtypebyid.Image>()
            roomFreeCancel = roomTypeResponse.data.data.freeCancel
            roomBreakFast = roomTypeResponse.data.data.breakFast
            roomPayInHotel = roomTypeResponse.data.data.payInHotel
            isLoadingRoomType.value = false
        }
        else -> Unit
    }
    when (roomTypeFacilityResponse) {
        is Result.Loading -> {
            Log.d("BookingDetails Screen", "RoomTypeFacility is loading")
        }
        is Result.Error -> {
            Log.d("BookingDetails Screen", "RoomTypeFacility error")
        }
        is Result.Success -> {
            Log.d("BookingDetails Screen", "RoomTypeFacility success")
            isLoadingRoomTypeFacility.value = false
        }
        else -> Unit
    }
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
                onClick = { navController.navigate("${ Screens.BookingDetailsReviewScreen.name }/${Gson().toJson(bookingData)}" )},
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
               // BookingSummaryCard(bookingData)
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
                        text = "(${bookingData.roomQuantity}x) ${roomName}",
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
                            if (roomBedDouble > 0) {
                                Text(
                                    text = "${roomBedDouble} double bed",
                                    fontSize = 14.sp,
                                )
                            }
                            if (roomBedKing > 0) {
                                Text(
                                    text = "${roomBedKing} king bed",
                                    fontSize = 14.sp,
                                )
                            }
                            if (roomBedQueen > 0) {
                                Text(
                                    text = "${roomBedQueen} queen bed",
                                    fontSize = 14.sp,
                                )
                            }
                            if (roomBedSingle > 0) {
                                Text(
                                    text = "${roomBedSingle} single bed",
                                    fontSize = 14.sp,
                                )
                            }
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
                            Text(
                                text = "${roomMaxCustomer} người/phòng",
                                fontSize = 14.sp,
                            )
                            Text(
                                text = "(${bookingData.adults} người lớn, ${bookingData.children} trẻ em trong ${bookingData.roomQuantity} phòng)",
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
                            model = if (roomImages.isNotEmpty()) roomImages[0].url else R.drawable.default_img,
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
                                    text = if (roomBreakFast) "Bữa sáng miễn phí"
                                    else "Không có bữa sáng",
                                    color = if (roomBreakFast) DarkGreenColor
                                    else Color.Red,
                                    fontSize = 14.sp
                                )
                            }
                            if (roomPayInHotel) {
                                Row {
                                    Icon(
                                        painter = painterResource(R.drawable.baseline_access_time_24),
                                        contentDescription = "",
                                        tint = PrimaryColor,
                                        modifier = Modifier
                                            .size(20.dp)
                                    )
                                    XSpacer(10)
                                    Text(
                                        text = "Thanh toán tại khách sạn",
                                        color = DarkGreenColor,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                            Row {
                                Icon(
                                    painter = painterResource(R.drawable.check),
                                    contentDescription = "",
                                    tint = DarkGreenColor,
                                    modifier = Modifier
                                        .size(20.dp)
                                )
                                XSpacer(10)
                                Text(
                                    text = if (roomFreeCancel) "Miễn phí hủy phòng"
                                    else "Có phí trả phòng",
                                    color = if (roomFreeCancel) DarkGreenColor
                                    else Color.Red,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
            }
            item { YSpacer(20) }
            item {
                ContactDetailsCard()
            }
            item { YSpacer(20) }
            item {
                PriceDetailsCard(
                    bookingData = bookingData,
                    roomName = roomName,
                    roomPrice = roomPrice
                )
            }
        }
    }
}
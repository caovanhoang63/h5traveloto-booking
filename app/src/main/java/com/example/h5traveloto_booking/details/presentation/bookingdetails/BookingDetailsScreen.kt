package com.example.h5traveloto_booking.details.presentation.bookingdetails

import android.util.Log
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.details.presentation.bookingdetails.components.ObjectAndPrice
import com.example.h5traveloto_booking.details.presentation.bookingdetails.screens.BookingDetailsScreenViewModel
import com.example.h5traveloto_booking.details.presentation.data.dto.reviews.CreateReviewDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.BookingDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.Image
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.UserBookingDTO
import com.example.h5traveloto_booking.share.UserShare
import com.example.h5traveloto_booking.share.shareDataHotelDetail
import com.example.h5traveloto_booking.share.user
import com.example.h5traveloto_booking.theme.*
import com.example.h5traveloto_booking.ui_shared_components.PrimaryIconButton
import com.example.h5traveloto_booking.ui_shared_components.XSpacer
import com.example.h5traveloto_booking.ui_shared_components.YSpacer
import com.example.h5traveloto_booking.util.Result
import kotlinx.datetime.minus
import kotlinx.datetime.plus

@Composable
fun BookingDetailsScreen (
    userBookingData: UserBookingDTO,
    navController: NavController,
    viewModel: BookingDetailsScreenViewModel = hiltViewModel()
) {
    shareDataHotelDetail.setHotelId(userBookingData.hotel.id)

    val state by remember {
        mutableStateOf(userBookingData.state)
    }
    val showRateDialog = remember {
        mutableStateOf(false)
    }
    var rateText by remember {
        mutableStateOf(TextFieldValue(""))
    }
    val score = remember {
        mutableStateOf(0)
    }
    val showSuccessDialog = remember {
        mutableStateOf(false)
    }

    if (showSuccessDialog.value) {
        Dialog(
            onDismissRequest = {
                showSuccessDialog.value = false
            }
        ) {
            Card (
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Column (
                    modifier = Modifier
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(R.drawable.onlylogo),
                        contentDescription = "",
                        tint = PrimaryColor,
                        modifier = Modifier
                            .size(30.dp)
                    )
                    YSpacer(10)
                    Text(
                        text = "Đánh giá của bạn đã được ghi nhận!",
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }

    if (showRateDialog.value) {
        Dialog(
            onDismissRequest = {
                showRateDialog.value = false
            }
        ) {
            Card (
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Column (
                    modifier = Modifier
                        .padding(12.dp)
                ) {
                    Text(
                        text = "Đánh giá",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    YSpacer(10)
                    OutlinedTextField(
                        value = rateText,
                        onValueChange = {
                            rateText = it
                        },
                        label = {
                            Text(
                                text = "Bình luận",
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        },
                        placeholder = {
                            Text(
                                text = "Trải nghiệm của bạn....",
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        },
                        minLines = 4,
                        maxLines = 6,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    YSpacer(10)
                    Row {
                        Text(
                            text = "Trên thang điểm 10, độ hài lòng của bạn là: ${score.value}",
                            fontSize = 14.sp,
                            modifier = Modifier
                                .fillMaxWidth(0.4f)
                        )
                        Column (
                            horizontalAlignment = Alignment.End,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Row (
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Icon(
                                    painter = painterResource(
                                        if (score.value >= 1) R.drawable.star
                                        else R.drawable.baseline_star_outline_16
                                    ),
                                    tint = if (score.value >= 1) StarColor else Color.Black,
                                    contentDescription = "" ,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clickable(
                                            onClick = {
                                                score.value = 1
                                            }
                                        )
                                )
                                Icon(
                                    painter = painterResource(
                                        if (score.value >= 2) R.drawable.star
                                        else R.drawable.baseline_star_outline_16
                                    ),
                                    tint = if (score.value >= 2) StarColor else Color.Black,
                                    contentDescription = "" ,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clickable(
                                            onClick = {
                                                score.value = 2
                                            }
                                        )
                                )
                                Icon(
                                    painter = painterResource(
                                        if (score.value >= 3) R.drawable.star
                                        else R.drawable.baseline_star_outline_16
                                    ),
                                    tint = if (score.value >= 3) StarColor else Color.Black,
                                    contentDescription = "" ,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clickable(
                                            onClick = {
                                                score.value = 3
                                            }
                                        )
                                )
                                Icon(
                                    painter = painterResource(
                                        if (score.value >= 4) R.drawable.star
                                        else R.drawable.baseline_star_outline_16
                                    ),
                                    tint = if (score.value >= 4) StarColor else Color.Black,
                                    contentDescription = "" ,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clickable(
                                            onClick = {
                                                score.value = 4
                                            }
                                        )
                                )
                                Icon(
                                    painter = painterResource(
                                        if (score.value >= 5) R.drawable.star
                                        else R.drawable.baseline_star_outline_16
                                    ),
                                    tint = if (score.value >= 5) StarColor else Color.Black,
                                    contentDescription = "" ,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clickable(
                                            onClick = {
                                                score.value = 5
                                            }
                                        )
                                )
                            }
                            YSpacer(5)
                            Row (
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Icon(
                                    painter = painterResource(
                                        if (score.value >= 6) R.drawable.star
                                        else R.drawable.baseline_star_outline_16
                                    ),
                                    tint = if (score.value >= 6) StarColor else Color.Black,
                                    contentDescription = "" ,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clickable(
                                            onClick = {
                                                score.value = 6
                                            }
                                        )
                                )
                                Icon(
                                    painter = painterResource(
                                        if (score.value >= 7) R.drawable.star
                                        else R.drawable.baseline_star_outline_16
                                    ),
                                    tint = if (score.value >= 7) StarColor else Color.Black,
                                    contentDescription = "" ,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clickable(
                                            onClick = {
                                                score.value = 7
                                            }
                                        )
                                )
                                Icon(
                                    painter = painterResource(
                                        if (score.value >= 8) R.drawable.star
                                        else R.drawable.baseline_star_outline_16
                                    ),
                                    tint = if (score.value >= 8) StarColor else Color.Black,
                                    contentDescription = "" ,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clickable(
                                            onClick = {
                                                score.value = 8
                                            }
                                        )
                                )
                                Icon(
                                    painter = painterResource(
                                        if (score.value >= 9) R.drawable.star
                                        else R.drawable.baseline_star_outline_16
                                    ),
                                    tint = if (score.value >= 9) StarColor else Color.Black,
                                    contentDescription = "" ,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clickable(
                                            onClick = {
                                                score.value = 9
                                            }
                                        )
                                )
                                Icon(
                                    painter = painterResource(
                                        if (score.value >= 10) R.drawable.star
                                        else R.drawable.baseline_star_outline_16
                                    ),
                                    tint = if (score.value >= 10) StarColor else Color.Black,
                                    contentDescription = "" ,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clickable(
                                            onClick = {
                                                score.value = 10
                                            }
                                        )
                                )
                            }
                        }
                    }
                    YSpacer(10)
                    Row (
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = { showRateDialog.value = false },
                            modifier = Modifier
                                .height(65.dp)
                                .width(150.dp)
                                .padding(12.dp)
                                .border(2.dp, BorderStroke, RoundedCornerShape(12.dp)),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(Color.Transparent)
                        ) {
                            Text(
                                text = "Hủy",
                                fontSize = 14.sp,
                                color = PrimaryColor
                            )
                        }

                        Button(
                            onClick = {
                                showRateDialog.value = false
                                viewModel.createReview(
                                    CreateReviewDTO(
                                        hotelId = userBookingData.hotel.id,
                                        roomTypeId = userBookingData.roomTypeId,
                                        comment = rateText.text,
                                        rate = score.value
                                    )
                                )
                                showSuccessDialog.value = true
                                      },
                            modifier = Modifier
                                .height(65.dp)
                                .width(150.dp)
                                .padding(12.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(OrangeColor)
                        ) {
                            Text(
                                text = "Gửi",
                                fontSize = 14.sp,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }

    }

    LaunchedEffect(Unit) {
        viewModel.getRoomTypeById(userBookingData.roomTypeId)
        viewModel.getRoomTypeFacility(userBookingData.roomTypeId)
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
        mutableLongStateOf(0)
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
            roomPrice =  roomTypeResponse.data.data.price.toLong()
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

    Scaffold (
        topBar = {
            Column (
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
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
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, top = 0.dp, bottom = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    when (state) {
                        "pending" -> {
                            Text(
                                text = "đang đặt phòng",
                                fontSize = 14.sp,
                                color = PrimaryColor,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                        "paid" -> {
                            Text(
                                text = "đã thanh toán",
                                fontSize = 14.sp,
                                color = DarkGreenColor,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                        "canceled" -> {
                            Text(
                                text = "đã hủy",
                                fontSize = 14.sp,
                                color = RedColor,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                        "check-out" -> {
                            Text(
                                text = "đã trả phòng",
                                fontSize = 14.sp,
                                color = GreenColor,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                        "expired" -> {
                            Text(
                                text = "đã hết thời gian chờ",
                                fontSize = 14.sp,
                                color = OrangeColor,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                        else -> Unit
                    }
                }
            }
        },
        bottomBar = {
            when (state) {
                "check-out" -> {
                    Row {
                        Text(
                            text = "Chúc mừng bạn đã hoàn thành chuyến đi của mình, để lại đánh giá để ghi lại trải nghệm của bạn",
                            fontSize = 14.sp,
                            color = SecondaryColor,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(horizontal = 24.dp, vertical = 10.dp)
                                .fillMaxWidth(0.6f),
                        )
                        Button(
                            onClick = {
                                showRateDialog.value = true
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp, vertical = 10.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(SecondaryColor)
                        ) {
                            Text(
                                text = "Đánh giá ngay",
                                fontSize = 14.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                "pending" -> {
                    Button(
                        onClick = {
                            /*tiep tuc thanh toan*/
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(65.dp)
                            .padding(horizontal = 24.dp, vertical = 10.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(OrangeColor)
                    ) {
                        Text(
                            text = "Tiếp tục thanh toán",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                }
                "expire" -> {
                    Row {
                        Text(
                            text = "Thời gian đặt phòng đã hết",
                            fontSize = 14.sp,
                            color = SecondaryColor,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(horizontal = 24.dp, vertical = 10.dp)
                                .fillMaxWidth(0.6f),
                        )
                        Button(
                            onClick = {
                                showRateDialog.value = true
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp, vertical = 10.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(OrangeColor)
                        ) {
                            Text(
                                text = "Đặt lại",
                                fontSize = 14.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                "canceled" -> {
                    Row {
                        Text(
                            text = "Phòng bạn đặt đã bị hủy",
                            fontSize = 14.sp,
                            color = SecondaryColor,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(horizontal = 24.dp, vertical = 10.dp)
                                .fillMaxWidth(0.6f),
                        )
                        Button(
                            onClick = {
                                showRateDialog.value = true
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp, vertical = 10.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(OrangeColor)
                        ) {
                            Text(
                                text = "Đặt lại",
                                fontSize = 14.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                "paid" -> {

                }
                else -> Unit
            }
        }
    ) { innerPadding ->
        if (isLoadingRoomType.value || isLoadingRoomTypeFacility.value) {
//            Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(innerPadding).fillMaxSize()) {
//                CircularProgressIndicator()
//            }
        } else {
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
                                    text = "${shareDataHotelDetail.getEndDate().minus(shareDataHotelDetail.getStartDate()).days} đêm",
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
                        text = "(${userBookingData.roomQuantity}x) ${roomName}",
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
                                        tint = if (roomBreakFast) DarkGreenColor
                                        else RedColor,
                                        modifier = Modifier
                                            .size(20.dp)
                                    )
                                    XSpacer(10)
                                    Text(
                                        text = if (roomBreakFast) "Bữa sáng miễn phí"
                                        else "Không có bữa sáng",
                                        color = if (roomBreakFast) DarkGreenColor
                                        else RedColor,
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
                                            color = PrimaryColor,
                                            fontSize = 14.sp
                                        )
                                    }
                                }
                                Row {
                                    Icon(
                                        painter = painterResource(R.drawable.check),
                                        contentDescription = "",
                                        tint = if (roomFreeCancel) DarkGreenColor
                                        else RedColor,
                                        modifier = Modifier
                                            .size(20.dp)
                                    )
                                    XSpacer(10)
                                    Text(
                                        text = if (roomFreeCancel) "Miễn phí hủy phòng"
                                            else "Có phí trả phòng",
                                        color = if (roomFreeCancel) DarkGreenColor
                                                else RedColor,
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
                                    text = if (UserShare.User.firstName != null) "${UserShare.User.lastName.toString()} ${UserShare.User.firstName.toString()}"
                                    else "",
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
                                    text = if(UserShare.User.email != null) UserShare.User.email.toString()
                                    else "",
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
                                    text = if (UserShare.User.phone != null) UserShare.User.phone.toString()
                                    else "",
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
                            text = "(${userBookingData.roomQuantity}x) ${roomName}",
                            price = roomPrice.toLong() * userBookingData.roomQuantity
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
}
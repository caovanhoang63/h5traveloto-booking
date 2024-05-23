package com.example.h5traveloto_booking.details.presentation.bookingdetails.screens

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
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
import com.example.h5traveloto_booking.share.UserShare
import com.example.h5traveloto_booking.share.shareDataHotelDetail
import com.example.h5traveloto_booking.theme.*
import com.example.h5traveloto_booking.ui_shared_components.PrimaryIconButton
import com.example.h5traveloto_booking.ui_shared_components.XSpacer
import com.example.h5traveloto_booking.ui_shared_components.YSpacer
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.modifiers.RangeMidDay
import com.example.h5traveloto_booking.util.Result
import createPayment
import kotlinx.datetime.minus

@Composable
fun BookingReviewScreen (
    navController: NavController,
    bookingData: CreateBookingDTO,
    viewModel: BookingScreenViewModel = hiltViewModel()
) {
    var couponCode by remember { mutableStateOf("") }
    var discountAmount by remember { mutableStateOf(0L) }
    var isCouponApplied by remember { mutableStateOf(false) }
    val getBookingResponse = viewModel.BookingResponse.collectAsState().value
    val getLinkResponse = viewModel.GetLinkResponse.collectAsState().value
    val executePaymentResponse = viewModel.ExecutePaymentResponse.collectAsState().value
    val context = LocalContext.current
    /*when(GetBookingResponse){
        is Result.Error -> {}
        is Result.Idle -> {}
        is Result.Loading -> {}
        is Result.Success -> {
            LaunchedEffect(Unit){
                viewModel.getLinkPayment(bookingId= GetBookingResponse.data.data.id,dealId = null, currency = GetBookingResponse.data.data.currency)
            }
        }
    }*/
    LaunchedEffect(getBookingResponse){
        when(getBookingResponse){
            is Result.Success -> {
                val bookingId = getBookingResponse.data.data.id
                val currency = getBookingResponse.data.data.currency
                viewModel.getLinkPayment(bookingId = bookingId, dealId = getBookingResponse.data.data.dealId,currency= currency)
            }
            else -> Unit
        }
    }
    LaunchedEffect(getLinkResponse) {
        when (getLinkResponse) {
            is Result.Success -> {
                val paymentUrl = (getLinkResponse as Result.Success).data.data.paymentUrl
                val txnId = getLinkResponse.data.data.txnId
                viewModel.executePayment(txnId.toString())
                navController.navigate("webview/${Uri.encode(paymentUrl)}")
            }
            else -> Unit
        }
    }

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
                        text = shareDataHotelDetail.getHotelName(),
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
                                text = shareDataHotelDetail.getStartDateString(),
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
                                text = "${
                                    shareDataHotelDetail.getEndDate()
                                        .minus(shareDataHotelDetail.getStartDate()).days.toString()
                                }/night(s)",
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
                                text = shareDataHotelDetail.getEndDateString(),
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
                        text = shareDataHotelDetail.getRoomTypeId(),
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
                                text = "${shareDataHotelDetail.getRoomQuantity()}/phòng",
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
                            model = shareDataHotelDetail.getHotelDetails()?.logo?.url,
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
                                text = "${UserShare.User.lastName} ${UserShare.User.firstName}",
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
            item{
                Column (
                    modifier = Modifier
                        .background(color = Grey50Color)
                        .fillMaxWidth()
                        .padding(24.dp, 24.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Row(modifier = Modifier.align(Alignment.CenterStart)){
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_discount_24),
                                    contentDescription = "discount",
                                    modifier = Modifier
                                        .size(24.dp)
                                )
                                XSpacer(width = 5)
                                Text(
                                    text = "Mã giảm giá",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                )
                            }
                            
                            Button(
                                onClick = {
                                    // Thêm hành động điều hướng đến màn hình danh sách mã giảm giá
                                    //navController.navigate("coupon_list")
                                },
                                modifier = Modifier.align(Alignment.CenterEnd),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(OrangeColor)
                            ) {
                                Text(text = "Áp dụng", color = Color.White)
                            }
                        }
                        // Uncomment và điều chỉnh đoạn mã này nếu bạn muốn hiển thị thông tin giảm giá
                        /*
                        if (isCouponApplied) {
                            Text(
                                text = "Giảm giá: -${discountAmount} VND",
                                fontSize = 14.sp,
                                color = Color.Green,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                        */
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
                        price = shareDataHotelDetail.getHotelDetails()?.displayPrice?.toLong() ?: 0
                    )
                    //Thêm mã giảm giá


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
                            text = shareDataHotelDetail.getHotelDetails()?.displayPrice.toString() ?: "",
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
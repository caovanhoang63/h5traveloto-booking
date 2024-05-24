package com.example.h5traveloto_booking.main.presentation.schedule

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.wear.compose.material.Text
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.BookingDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.UserBookingDTO
import com.example.h5traveloto_booking.main.presentation.schedule.components.BookingCard
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.theme.Grey100Color
import com.example.h5traveloto_booking.theme.Grey50Color
import com.example.h5traveloto_booking.theme.PrimaryColor
import com.example.h5traveloto_booking.ui_shared_components.BoldText
import com.example.h5traveloto_booking.ui_shared_components.PrimaryIconButton
import com.example.h5traveloto_booking.ui_shared_components.YSpacer
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.utils.today
import com.example.h5traveloto_booking.util.FromDatetoString
import com.example.h5traveloto_booking.util.Result
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Local

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun BookingScreen (
    navController: NavController,
    parentNavController: NavController,
    viewModel: ScheduleViewModel = hiltViewModel()
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val showMenuState = remember {
        mutableStateOf(false)
    }
    val stateNames = listOf(
        "Đang đặt",
        "Đã thanh toán",
        "Đã hủy",
        "Đã hoàn thành",
        "Hết hạn"
    )
    val states = listOf(
        "pending",
        "paid",
        "canceled",
        "checked-out",
        "expired"
    )
    val bookingState = remember {
        mutableStateOf(states[1])
    }
    val isPayInHotel = remember {
        mutableStateOf(false)
    }
    val isShowDateRangePicker = remember {
        mutableStateOf(false)
    }
    val start = remember {
        mutableStateOf(LocalDate.today())
    }
    val end = remember {
        mutableStateOf(LocalDate.today())
    }
    val isAllDate = remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit) {
        viewModel.getUserBookings(state = bookingState.value)
    }
    val listBookingResponse = viewModel.UserBookingsResponse.collectAsState().value

    if (isShowDateRangePicker.value) {
        com.example.h5traveloto_booking.ui_shared_components.DateRangePicker(
            start = LocalDate.today(),
            end = LocalDate.today().plus(1, DateTimeUnit.DAY),
            onDismiss = {
                isShowDateRangePicker.value = false
                isAllDate.value = true
                viewModel.getUserBookings(
                    state = bookingState.value,
                    payInHotel = isPayInHotel.value,
                )
            },
            onCompleted = { startDate, endDate ->
                isShowDateRangePicker.value = false
                isAllDate.value = false
                start.value = startDate
                end.value = endDate
                viewModel.getUserBookings(
                    state = bookingState.value,
                    payInHotel = isPayInHotel.value,
                    startDate = FromDatetoString(start.value),
                    endDate = FromDatetoString(end.value)
                )
            }
        )
    }

    Scaffold (
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Column {
                Row (
                    Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box (
                        modifier = Modifier
                            .fillMaxWidth(0.2f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        PrimaryIconButton(
                            DrawableId = R.drawable.backarrow48,
                            onClick = {
                                navController.popBackStack()
                            },
                            alt = ""
                        )
                    }
                    Box (
                        modifier = Modifier
                            .fillMaxWidth(0.75f),
                        contentAlignment = Alignment.Center
                    ) {
                        BoldText(text = "Danh sách đặt phòng")
                    }

                }
                Row (
                    modifier = Modifier
                        .padding(16.dp, 0.dp, 16.dp, 16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .clickable(
                                    onClick = {
                                        showMenuState.value = true
                                    },
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                )
                                .padding(5.dp)
//                                .width(100.dp)
                                .height(40.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Grey100Color)
                        ) {
                            Column {
                                Text(
                                    text = "${stateNames.getOrNull(states.indexOf(bookingState.value))}",
                                    fontSize = 14.sp,
                                    color = Color.Black,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .padding(5.dp)
                                )
                                DropdownMenu(
                                    expanded = showMenuState.value,
                                    onDismissRequest = {showMenuState.value = false},
                                    modifier = Modifier
                                        .background(Color.White)
                                ) {
                                    states.forEachIndexed { index, s ->
                                        DropdownMenuItem(
                                            text = {
                                                Text(
                                                    text = stateNames[index],
                                                    fontSize = 14.sp,
                                                    color = Color.Black
                                                )
                                            },
                                            onClick = {
                                                bookingState.value = states[index]
                                                showMenuState.value = false
                                                if (isAllDate.value) {
                                                    viewModel.getUserBookings(
                                                        state = bookingState.value,
                                                        payInHotel = isPayInHotel.value
                                                    )
                                                } else {
                                                    viewModel.getUserBookings(
                                                        state = bookingState.value,
                                                        payInHotel = isPayInHotel.value,
                                                        startDate = FromDatetoString(start.value),
                                                        endDate = FromDatetoString(end.value)
                                                    )
                                                }
                                            },
                                            modifier = Modifier
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .clickable(
                                onClick = {
                                    isShowDateRangePicker.value = true
                                },
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            )
                            .padding(5.dp)
                            .height(40.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                if (isAllDate.value) PrimaryColor
                            else Grey50Color)
                    ) {
                        Text(
                            text = "Mọi ngày",
                            fontSize = 14.sp,
                            color = if (isAllDate.value) Color.White
                            else Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(5.dp)
                        )
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .clickable(
                                onClick = {
                                    isPayInHotel.value = !isPayInHotel.value
                                    if (isAllDate.value) {
                                        viewModel.getUserBookings(
                                            state = bookingState.value,
                                            payInHotel = isPayInHotel.value
                                        )
                                    } else {
                                        viewModel.getUserBookings(
                                            state = bookingState.value,
                                            payInHotel = isPayInHotel.value,
                                            startDate = FromDatetoString(start.value),
                                            endDate = FromDatetoString(end.value)
                                        )
                                    }
                                },
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            )
                            .padding(5.dp)
                            .height(40.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                if (isPayInHotel.value) PrimaryColor
                                else Grey50Color
                            )
                    ) {
                        Text(
                            text = "Trả tại khách sạn",
                            fontSize = 14.sp,
                            color = if (isPayInHotel.value) Color.White
                            else Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(5.dp)
                        )
                    }
                }
            }

        }
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 10.dp)
        ) {
            when (listBookingResponse) {
                is Result.Loading -> {
                    Log.d("Schedule Screen", "Booking is loading")
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator()
                    }
                }
                is Result.Error -> {
                    Log.d("Schedule Screen", "Booking error")
                }
                is Result.Success -> {
                    Log.d("Schedule Screen", "Booking Success")
                    var listBooking = listBookingResponse.data.data
                    LazyColumn {
                        items(listBooking) { bookingData ->
                            BookingCard(
                                true,
                                bookingData = bookingData,
                                navController = parentNavController
                            )
                            YSpacer(height = 10)
                        }
                    }
                }
                else -> Unit
            }

        }
    }
}
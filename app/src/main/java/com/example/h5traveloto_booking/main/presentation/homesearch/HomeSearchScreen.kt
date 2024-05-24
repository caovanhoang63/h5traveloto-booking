package com.example.h5traveloto_booking.main.presentation.homesearch

import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backup
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.h5traveloto_booking.MainActivity
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.main.presentation.home.components.HotelTagSmall
import com.example.h5traveloto_booking.main.presentation.homesearch.screens.ChoosePersonScreen
import com.example.h5traveloto_booking.main.presentation.homesearch.screens.SearchLocationScreen
import com.example.h5traveloto_booking.main.presentation.map.LocationProvider
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.share.ShareHotelDataViewModel
import com.example.h5traveloto_booking.share.shareDataHotelDetail
import com.example.h5traveloto_booking.share.shareHotelDataViewModel
import com.example.h5traveloto_booking.theme.*
import com.example.h5traveloto_booking.ui_shared_components.*
import com.example.h5traveloto_booking.util.Result
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun HomeSearchScreen(
    navController: NavController,
    navAppNavController: NavController,
    viewModel: HomeSearchViewModel = hiltViewModel(),
) {
    val scrollState = rememberScrollState()
    var showChosePerson by remember { mutableStateOf(false) }
    var showChoseLocation by remember { mutableStateOf(false) }
    var adult by rememberSaveable { mutableIntStateOf(1) }
    var child by rememberSaveable { mutableIntStateOf(0) }
    var room by rememberSaveable { mutableIntStateOf(1) }
    var location by rememberSaveable { mutableStateOf("Khách sạn gần tôi") }
    var isShowDateRangePicker by remember { mutableStateOf(false) }

    val listHotelViewed = viewModel.ListHotelViewed.collectAsState().value

    LaunchedEffect(key1 = true) {
        viewModel.getHotelViewed()
    }

    Scaffold(
        topBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(color = Grey50Color)
                    .fillMaxWidth()
                    .padding(21.dp, 0.dp, 21.dp, 0.dp)
                    .height(64.dp)
            ) {


                Box(modifier = Modifier.fillMaxWidth()) {
                    PrimaryIconButton(
                        DrawableId = R.drawable.backbutton,
                        onClick = { navController.popBackStack() },
                        alt = "",
                    )
                    Text(
                        text = "Tìm kiếm",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.Center)
                    )

                }
            }
        }

    ) { innerPaddings ->
        Column(
            modifier = Modifier
                .padding(innerPaddings)
                .fillMaxSize()

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = scrollState)
            ) {
                Box(
                    modifier = Modifier
                        .height(27.dp)
                        .background(color = Grey50Color)
                        .fillMaxWidth()
                )
                Box(
                    modifier = Modifier
                        .height(277.dp)
                        .background(color = Color(0xFFFBFBFB))
                        .fillMaxWidth()
                        .offset(y = (-20).dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Box(
                        modifier = Modifier
                            .height(260.dp)
                            .fillMaxWidth()
                            .padding(30.dp, 0.dp)
                            .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(color = Color(0xFFFFFFFF)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp, 8.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp),
                            ) {
/* Choose location */
                                TextButtonDialog(
                                    modifier = Modifier
                                        .height(52.dp)
                                        .weight(1f)
                                        .clickable { showChoseLocation = true },
                                    content = location,
                                    icon = {
                                        Image(
                                            painterResource(id = R.drawable.location1),
                                            contentDescription = "",
                                            modifier = Modifier.size(20.dp)
                                        )
                                    },
                                )
                                if (showChoseLocation) {
                                    SearchLocationScreen(
                                        onDismiss = { showChoseLocation = false },
                                        onComplete = { locations, suggestion ->
                                            if (locations.isNotEmpty()) {
                                                location = locations
                                            }
                                            showChoseLocation = false
                                            viewModel.setLocation(suggestion)
                                        },
                                        location,
                                        viewModel.getIsCurrentLocation(),
                                        setIsCurrentLocation = { isCurrentLocation ->
                                            viewModel.setIsCurrentLocation(isCurrentLocation)
                                        }
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = {
                                        location = "Khách sạn gần tôi"
                                        viewModel.setIsCurrentLocation(true)
                                    },
                                    modifier = Modifier
                                        .size(44.dp)
                                        .padding(4.dp)
                                        .shadow(4.dp, shape = RoundedCornerShape(22.dp))
                                        .clip(shape = RoundedCornerShape(22.dp)),
                                    contentPadding = PaddingValues(0.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.White,
                                    ),
                                )
                                {
                                    Icon(
                                        Icons.Default.MyLocation,
                                        contentDescription = "",
                                        modifier = Modifier.size(24.dp),
                                        tint = PrimaryColor
                                    )
                                }
                            }
                            /* data picker range */
                            TextButtonDialog(
                                modifier = Modifier
                                    .height(52.dp)
                                    .fillMaxWidth()
                                    .clickable {
                                        isShowDateRangePicker = true
                                    },
                                content = "${viewModel.getStartDate().dayOfMonth} Thg ${viewModel.getStartDate().monthNumber} ${viewModel.getStartDate().year} - ${viewModel.getEndDate().dayOfMonth} Thg ${viewModel.getEndDate().monthNumber} ${viewModel.getEndDate().year}",
                                icon = {
                                    Image(
                                        painterResource(id = R.drawable.calendar),
                                        contentDescription = "",
                                        modifier = Modifier.size(20.dp)
                                    )
                                },
                            )
                            if (isShowDateRangePicker) {
                                DateRangePicker(
                                    start = viewModel.getStartDate(),
                                    end = viewModel.getEndDate(),
                                    onCompleted = { startDate, endDate ->
                                        viewModel.setStartDateEndDate(startDate, endDate)
                                        isShowDateRangePicker = false
                                    },
                                    onDismiss = {
                                        isShowDateRangePicker = false
                                    }
                                )
                            }
                            /* Choose person */
                            TextButtonDialog(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp)
                                    .clickable { showChosePerson = true },
                                content = "$adult người lớn, $child trẻ em, $room phòng",
                                icon = {
                                    Image(
                                        painterResource(id = R.drawable.useralt),
                                        contentDescription = "",
                                        modifier = Modifier.size(22.dp)
                                    )
                                },
                            )
                            if (showChosePerson) {
                                ChoosePersonScreen(
                                    onDismiss = { showChosePerson = false },
                                    onConfirm = { adults, childs, rooms ->
                                        adult = adults
                                        child = childs
                                        room = rooms
                                        viewModel.setPersonEndRoom(adults, childs, rooms)
                                        showChosePerson = false
                                    },
                                    adult, child, room
                                )
                            }
                            Spacer(modifier = Modifier.height(24.dp))
                            /* Button booking now */
                            Button(
                                onClick = {
                                    //launchMultiplePermissions.launch(permissions)
                                    viewModel.bookingNow()
                                    navAppNavController.navigate(Screens.ListHotels.name)
                                    shareDataHotelDetail.setSearchText(location)
                                    shareDataHotelDetail.setSelectedStartDate(viewModel.getStartDate().toString())
                                    shareDataHotelDetail.setPersonOption(adult, child, room)
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp),
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = OrangeColor
                                )
                            ) {
                                Text(
                                    text = "Booking Now",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                Column(modifier = Modifier.padding(16.dp, 0.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        BoldText("Đã xem gần đây")
                        Text(
                            text = "Xem tất cả",
                            fontSize = 16.sp,
                            color = if (viewModel.checkDataViewed()) PrimaryColor else Grey500Color,
                            modifier = Modifier.clickable {
                                if (viewModel.checkDataViewed()) {
                                    shareHotelDataViewModel.setIsCurrentLocation(false)
                                    shareHotelDataViewModel.setListHotel((listHotelViewed as Result.Success).data)
                                    shareHotelDataViewModel.setOnClickBooking(false)
                                    navAppNavController.navigate(Screens.ListHotels.name)
                                }
                            }
                        )
                    }
                    YSpacer(16)
                    when (listHotelViewed) {
                        is Result.Error -> {
                            NotFoundHotel()
                        }

                        is Result.Idle -> {
                        }

                        is Result.Loading -> {
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)) {
                                CircleLoading()
                            }
                        }

                        is Result.Success -> {
                            val listHotel = listHotelViewed.data.data
                            Column(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp)) {
                                if (listHotel != null) {
                                    listHotel.forEachIndexed { index, hotel ->
                                        HotelTagSmall(
                                            hotelDTO = hotel,
                                            onClick = {
                                                shareDataHotelDetail.setHotelDetails(hotel)
                                                shareDataHotelDetail.setHotelId(hotel.id)
                                                shareDataHotelDetail.LogData()
                                                navAppNavController.navigate(Screens.HotelDetailsScreen.name)
                                            }
                                        )
                                        YSpacer(16)
                                        if (index >= 3) {
                                            return@Column
                                        }
                                    }
                                } else {
                                    NotFoundHotel()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



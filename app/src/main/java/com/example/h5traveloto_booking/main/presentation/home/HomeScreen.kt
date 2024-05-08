package com.example.h5traveloto_booking.main.presentation.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.main.presentation.home.components.HotelTagLarge
import com.example.h5traveloto_booking.main.presentation.home.components.HotelTagSmall
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.share.ShareHotelDataViewModel
import com.example.h5traveloto_booking.share.shareHotelDataViewModel
import com.example.h5traveloto_booking.theme.*
import com.example.h5traveloto_booking.ui_shared_components.*
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton
import com.example.h5traveloto_booking.util.Result


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {


    //
    LaunchedEffect(Unit) {
        viewModel.getListHotel()
    }

    var selectedItemIndex by remember { mutableStateOf(0) }
    val items =
        listOf("Ha Noi", "TP HCM", "Da Nang", "Hue", "Hoi An", "Nha Trang", "Da Lat", "Vung Tau", "Phu Quoc", "Can Tho")


    val listHotelDataResponse = viewModel.listHotelDataResponse.collectAsState().value

    Spacer(modifier = Modifier.height(10.dp))

    Scaffold(
        modifier = Modifier.background(ScreenBackGround),
        topBar = {
            Row(
                Modifier
                    .padding(24.dp, 5.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = "Current location")
                    Spacer(modifier = Modifier.height(4.dp))
                    MenuDown(items = items,
                        selectedItemIndex = selectedItemIndex,
                        onItemSelected = { index -> selectedItemIndex = index })

                }
                Column {
                    PrimaryIconButton(DrawableId = R.drawable.notifyicon, onClick = {}, alt = "")
                }
            }
        }


    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                Spacer(modifier = Modifier.height(10.dp))
                Column {
                    Button(
                        onClick = { navController.navigate(Screens.HomeSearchScreen.name) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp, 0.dp)
                            .border(2.dp, BorderStroke, RoundedCornerShape(8.dp))
                            .height(52.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                        ),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painterResource(id = R.drawable.search),
                                contentDescription = "search",
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = "Search Hotels",
                                fontSize = 14.sp,
                                color = Grey500Color,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp, 0.dp)
                            )
                            Image(
                                painterResource(id = R.drawable.searchfilter),
                                contentDescription = "search",
                                modifier = Modifier.size(28.dp)
                            )
                        }

                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp, 0.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            text = "Vị trí gần bạn"
                        )
                        Text(
                            text = "See all",
                            fontSize = 16.sp,
                            color = PrimaryColor,
                            modifier = Modifier.clickable { navController.navigate(Screens.LoginScreen.name) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))




                when (listHotelDataResponse) {
                    is Result.Loading -> {
                        Log.d("Home ", "dang load")

                        // Hieu ung load
                        Box( contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator()

                        }
                    }

                    is Result.Error -> {
                        Log.d("Home ", "loi roi")
                    }

                    is Result.Success -> {
//                    Log.d("Home Screen", result.data.data.size.toString())
                        val hotels = listHotelDataResponse.data.data
                        LazyRow(modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp)) {
                            for (i in 0..hotels.size - 1) {
                                item {
                                    HotelTagLarge(hotels[i])
                                }
                            }
                        }
                    }

                    else -> Unit
                }


                Spacer(modifier = Modifier.height(22.dp))

                Column(modifier = Modifier.padding(16.dp, 0.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        BoldText("Địa điểm nổi bật")
                        ClickableText("See all", {})
                    }
                    YSpacer(10)
                    HotelTagSmall()
                }
            }
        }

    }
}
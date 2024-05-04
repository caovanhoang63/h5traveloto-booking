package com.example.h5traveloto_booking.details.presentation.hoteldetails

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.account.AccountViewModel
import com.example.h5traveloto_booking.account.ListHotelsViewModel
import com.example.h5traveloto_booking.details.presentation.hoteldetails.components.HotelDetailCard
import com.example.h5traveloto_booking.main.presentation.home.components.HotelTagLarge
import com.example.h5traveloto_booking.theme.Grey50Color
import com.example.h5traveloto_booking.ui_shared_components.PrimaryIconButton
import com.example.h5traveloto_booking.ui_shared_components.XSpacer
import com.example.h5traveloto_booking.ui_shared_components.YSpacer
import com.example.h5traveloto_booking.util.Result

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListHotels(navController: NavController, viewModel: ListHotelsViewModel = hiltViewModel()) {
    LaunchedEffect(Unit){
        viewModel.getListHotels()
    }
    val listHotelResponse = viewModel.ListHotelResponse.collectAsState().value

    Scaffold(
        topBar = {
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(121.dp)
                .background(Grey50Color),) {
                Column( modifier = Modifier.padding(top = 21.dp, start = 27.dp, end = 27.dp )) {
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,) {
                        PrimaryIconButton(DrawableId = R.drawable.backbutton, onClick = {navController.popBackStack()},alt = "",)

                        Column { //Current location
                            Text(text = "Khách sạn gần tôi", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp))
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "Th 6, 15 / 3 / 2024, 1 đêm, 1 phòng", style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 12.sp))
                        }
                        PrimaryIconButton(DrawableId = R.drawable.search, onClick = {},alt = "",)
                    }
                    YSpacer(15)
                    Row(modifier = Modifier.fillMaxWidth(),) {
                        PrimaryIconButton(DrawableId = R.drawable.filter, onClick = {},alt = "",)
                        XSpacer(25)
                        PrimaryIconButton(DrawableId = R.drawable.sort, onClick = {},alt = "",)
                    }
                }



            }

        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 54.dp, horizontal = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                item {
                    when (listHotelResponse) {
                        is Result.Loading -> {
                            Log.d("List Hotel ", "dang load")

                            // Hieu ung load
                            Box( contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                CircularProgressIndicator()

                            }
                        }

                        is Result.Error -> {
                            Log.d("List Hotel ", "loi roi")
                        }

                        is Result.Success -> {
                            Log.d("List Hotel ", "thanh cong")
                            val hotels = listHotelResponse.data.data
                                for (i in 0..hotels.size - 1) {
                                        HotelDetailCard(hotelDTO = hotels[i], navController = navController)
                                }

                        }

                        else -> Unit
                    }
                }
            }
        }

    }

}

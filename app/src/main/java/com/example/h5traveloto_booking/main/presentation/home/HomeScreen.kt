package com.example.h5traveloto_booking.main.presentation.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.main.presentation.data.dto.Hotel.HotelDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Hotel.ListHotelDTO
import com.example.h5traveloto_booking.main.presentation.home.components.HotelTagLarge
import com.example.h5traveloto_booking.main.presentation.home.components.HotelTagSmall
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.theme.PrimaryColor
import com.example.h5traveloto_booking.theme.ScreenBackGround
import com.example.h5traveloto_booking.ui_shared_components.BoldText
import com.example.h5traveloto_booking.ui_shared_components.ClickableText
import com.example.h5traveloto_booking.ui_shared_components.PrimaryIconButton
import com.example.h5traveloto_booking.ui_shared_components.YSpacer
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton
import com.example.h5traveloto_booking.util.Result


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen (navController: NavController,
                viewModel: HomeViewModel = hiltViewModel()) {



    //
    LaunchedEffect(Unit){
        viewModel.getListHotel()
    }





    val listHotelDataResponse = viewModel.listHotelDataResponse.collectAsState().value

    Spacer(modifier = Modifier.height(10.dp))

    Scaffold(
        modifier = Modifier.background(ScreenBackGround),
        topBar = {
            Row (Modifier.padding(24.dp,5.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text(text = "Current location")
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Wallace, Australiaaaa")
                }
                Column {
                    PrimaryIconButton(DrawableId = R.drawable.notifyicon, onClick = {},alt = "",)
                }
            }
        }


    ) {
            innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            Spacer(modifier = Modifier.height(10.dp))
            Column {
                PrimaryButton(onClick = {},text = "Search", modifier = Modifier.fillMaxWidth().padding(24.dp,0.dp))
                Spacer(modifier = Modifier.height(24.dp))
                Row (modifier = Modifier.fillMaxWidth().padding(24.dp,0.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        fontSize = 16.sp,
                        fontWeight =  FontWeight.Bold,
                        text ="Vị trí gần bạn"
                    )
                    Text(
                        text = "See all",
                        fontSize = 16.sp,
                         color = PrimaryColor,
                        modifier = Modifier.clickable { navController.navigate(Screens.LoginScreen.name)}
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))




            when (listHotelDataResponse) {
                is Result.Loading -> {
                    Log.d("Home ", "dang load")

                    // Hieu ung load
                }

                is Result.Error -> {
                    Log.d("Home ", "loi roi")
                }

                is Result.Success -> {
//                    Log.d("Home Screen", result.data.data.size.toString())
                    val hotels = listHotelDataResponse.data.data
                    LazyRow(modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp)) {
                        for (i in 0..hotels.size - 1) {
                            item{
                                HotelTagLarge(hotels[i])
                            }
                        }
                    }
                }

                else -> Unit
            }


            Spacer(modifier = Modifier.height(22.dp))

            Column (modifier = Modifier.padding(16.dp,0.dp)) {
                Row (modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    BoldText("Địa điểm nổi bật")
                    ClickableText("See all", {})
                }
                YSpacer(10)
                HotelTagSmall()
            }



        }
    }
}
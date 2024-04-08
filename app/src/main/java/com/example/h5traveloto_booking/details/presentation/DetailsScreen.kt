package com.example.h5traveloto_booking.details.presentation

import android.widget.ImageButton
import android.widget.RatingBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.details.presentation.components.HotelDetailCard
import com.example.h5traveloto_booking.details.presentation.components.MultiColorText
import com.example.h5traveloto_booking.theme.Grey50Color
import com.example.h5traveloto_booking.ui_shared_components.PrimaryIconButton
import com.example.h5traveloto_booking.ui_shared_components.XSpacer
import com.example.h5traveloto_booking.ui_shared_components.YSpacer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController) {
    val hotels = listOf( //dummy data
        HotelDetailCard(),
        HotelDetailCard(),
        HotelDetailCard()
    )
    Scaffold(
        topBar = {

            Column(modifier = Modifier.fillMaxWidth().padding(top = 21.dp, start = 27.dp, end = 27.dp ).height(121.dp).background(Grey50Color),) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,) {
                    PrimaryIconButton(DrawableId = R.drawable.backbutton, onClick = {},alt = "",)

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
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(vertical = 54.dp, horizontal = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                hotels.forEachIndexed() { index, hotel ->
                    item {
                        HotelDetailCard()

                    }
                }
            }
        }

    }

}

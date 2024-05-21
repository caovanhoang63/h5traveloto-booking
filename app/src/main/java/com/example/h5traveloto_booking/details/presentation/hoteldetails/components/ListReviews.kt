package com.example.h5traveloto_booking.details.presentation.hoteldetails.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.details.presentation.hoteldetails.HotelDetailsScreenViewModel
import com.example.h5traveloto_booking.details.presentation.roomdetails.components.RoomDetailCard
import com.example.h5traveloto_booking.ui_shared_components.BoldText
import com.example.h5traveloto_booking.ui_shared_components.BoldText2
import com.example.h5traveloto_booking.ui_shared_components.PrimaryIconButton
import com.example.h5traveloto_booking.util.Result

@Composable
fun ListReviews(
    navController: NavController,
    viewModel: HotelDetailsScreenViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getListReviews()
    }
    val listReviewsResponse = viewModel.ListReviewsResponse.collectAsState().value
    Log.d("List Reviews", listReviewsResponse.toString())

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    PrimaryIconButton(
                        DrawableId = R.drawable.backbutton, onClick = { navController.popBackStack() }, alt = ""
                    )
                    BoldText2(text = "Đánh giá", modifier = Modifier.align(Alignment.Center))

                }


            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(24.dp)
        ) {
            when (listReviewsResponse) {
                is Result.Loading -> {
                    Log.d("List Reviews", "dang load")

                    // Hieu ung load
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator()

                    }
                }

                is Result.Error -> {
                    Log.d("List Reviews ", "loi roi")
                }

                is Result.Success -> {
                    Log.d("List Reviews", "thanh cong")
                    val reviews = listReviewsResponse.data.data
                    reviews.forEach() {
                        Spacer(modifier = Modifier.height(16.dp))
                        HotelDetailFeedback(text = it.comment, author = it.user.lastName)
                        Spacer(modifier = Modifier.height(16.dp))
                        HorizontalDivider(thickness = 0.8.dp, color = Color.LightGray)

                    }
                }

                else -> Unit
            }
            /*HotelDetailFeedback(text = "phong nhu cai lon  tao vay do", author = "Hoang Cao")
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(thickness = 0.8.dp, color = Color.LightGray)
            //
            Spacer(modifier = Modifier.height(16.dp))
            HotelDetailFeedback(
                text = "The Aston Vill Hotel is a 5-star hotel located in the heart of the city. The hotel is a 5-minute walk from the city center and a 10-minute walk from the beach. The hotel offers a variety of amenities, including a spa, fitness center, and swimming pool. The hotel also has a restaurant and bar, where guests can enjoy a variety of dishes and drinks.",
                author = "Hoang Cao"
            )*/

        }

    }
}
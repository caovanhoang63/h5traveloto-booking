package com.example.h5traveloto_booking.main.presentation.data.dto.Payment.CoupounScreen

import androidx.compose.runtime.Composable
import ExpandingText
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.account.ListHotelsViewModel
import com.example.h5traveloto_booking.details.presentation.hoteldetails.components.*
import com.example.h5traveloto_booking.details.presentation.roomdetails.components.RoomDetailCard
import com.example.h5traveloto_booking.details.presentation.roomdetails.components.RoomFacilitiesList
import com.example.h5traveloto_booking.main.presentation.favorite.AllFavorite.formatPrice
import com.example.h5traveloto_booking.main.presentation.favorite.DetailCollection.DetailCollectionViewModel
import com.example.h5traveloto_booking.main.presentation.favorite.FavoriteViewModel

import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.share.shareDataHotelDetail
import com.example.h5traveloto_booking.theme.Grey500Color
import com.example.h5traveloto_booking.theme.PrimaryColor
import com.example.h5traveloto_booking.ui_shared_components.*
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.modifiers.RangeMidDay
import com.example.h5traveloto_booking.util.Result
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CouponListScreen(navController: NavController, onCouponSelected: (String) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Danh sách mã giảm giá") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painter = painterResource(id = R.drawable.backbutton), contentDescription = null)
                    }
                }
            )
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(5) { coupon ->

            }
        }
    }
}

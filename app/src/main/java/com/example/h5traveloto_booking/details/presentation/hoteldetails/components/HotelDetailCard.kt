package com.example.h5traveloto_booking.details.presentation.hoteldetails.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.h5traveloto_booking.main.presentation.data.dto.Hotel.HotelDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchHotel.Data
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchHotel.SearchHotelDTO
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.share.shareDataHotelDetail
import com.example.h5traveloto_booking.theme.Grey500Color
import com.example.h5traveloto_booking.theme.Grey50Color
import com.example.h5traveloto_booking.theme.PrimaryColor
import com.example.h5traveloto_booking.ui_shared_components.*
import kotlin.math.log

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotelDetailCard(navController: NavController, hotelDTO: HotelDTO) { //default rating will be 1
    val imgURL = hotelDTO.images?.map { it.url }



    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(232.dp),
        colors = CardDefaults.cardColors(
            containerColor = Grey50Color, //Card background color
        ),
        onClick = { navController.navigate(Screens.HotelDetailsScreen.name) },
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                imgURL?.forEachIndexed { index, image ->
                    item {
                        AsyncImage(
                            model = image,
                            contentDescription = null,
                            modifier = Modifier
                                .width(165.dp)
                                .height(110.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop,
                        )
                    }
                }
            }
            YSpacer(13)
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.Start

                ) {
                    Text(text = hotelDTO.name, style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold))
                    YSpacer(5)
                    GreyText(text = hotelDTO.address)
                    YSpacer(5)
                    Row {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "starRating",
                            tint = Color(0xffffe234),
                        )
                        XSpacer(width = 5)
                        BoldText(text = hotelDTO.star.toString() + ".0")


                    }
                }
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.End
                ) {
                    PrimaryText(text = "${hotelDTO.rating} (${hotelDTO.totalRating})")
                    MultiColorText(
                        Triple("${hotelDTO.avgPrice}VND", PrimaryColor, FontWeight.Bold),
                        Triple("/đêm", Grey500Color, FontWeight.Normal)
                    )


                }
            }
        }
    }
}

@Composable
fun HotelDetailCard2(navController: NavController, hotelDTO: Data) { //default rating will be 1
    val imgURL = hotelDTO.images?.map { it.url }



    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(232.dp),
        colors = CardDefaults.cardColors(
            containerColor = Grey50Color, //Card background color
        ),
        onClick = {
            navController.navigate(
                Screens.HotelDetailsScreen.name
            );
            shareDataHotelDetail.setHotelId(hotelDTO.id)
        },
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                imgURL?.forEachIndexed { index, image ->
                    item {
                        AsyncImage(
                            model = image,
                            contentDescription = null,
                            modifier = Modifier
                                .width(165.dp)
                                .height(110.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop,
                        )
                    }
                }
            }
            YSpacer(13)
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.Start

                ) {
                    Text(text = hotelDTO.name, style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold))
                    YSpacer(5)
                    GreyText(text = hotelDTO.district.name + ", " + hotelDTO.province.name)
                    YSpacer(5)
                    Row {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "starRating",
                            tint = Color(0xffffe234),
                        )
                        XSpacer(width = 5)
                        BoldText(text = hotelDTO.star.toString() + ".0")


                    }
                }
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.End
                ) {
                    PrimaryText(text = "${hotelDTO.totalRating} (${hotelDTO.totalRating})")
                    MultiColorText(
                        Triple("${hotelDTO.displayPrice}VND", PrimaryColor, FontWeight.Bold),
                        Triple("/đêm", Grey500Color, FontWeight.Normal)
                    )


                }
            }
        }
    }
}
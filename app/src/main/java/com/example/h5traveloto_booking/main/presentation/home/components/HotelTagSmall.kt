package com.example.h5traveloto_booking.main.presentation.home.components

import android.app.Dialog
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.UserBookingDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Hotel.HotelDTO
import com.example.h5traveloto_booking.main.presentation.favorite.AllFavorite.formatPrice
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.theme.Grey100Color
import com.example.h5traveloto_booking.theme.Grey500Color
import com.example.h5traveloto_booking.ui_shared_components.*
import com.example.h5traveloto_booking.util.FromStringtoDate
import com.google.gson.Gson


@Composable
fun HotelTagSmall(
    hotelDTO: com.example.h5traveloto_booking.main.presentation.data.dto.SearchHotel.Data,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                8.dp,
                shape = RoundedCornerShape(8.dp),
                clip = true,
                ambientColor = Grey100Color,
                spotColor = Grey500Color
            )
            .clickable { onClick() },
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Row(modifier = Modifier.padding(12.dp)) {
                AsyncImage(
                    modifier = Modifier
                        .size(84.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)),
                    model = hotelDTO.images[0].url,
                    contentDescription = "Hotel image",
                    contentScale = ContentScale.FillBounds,
                )

                Column(
                    modifier = Modifier
                        .padding(12.dp, 4.dp)
                        .fillMaxWidth()
                ) {
                    ScrollingText(hotelDTO.name)


                    YSpacer(4)

                    ScrollingText2(hotelDTO.address)

                    YSpacer(4)

                    Row {
                        repeat(hotelDTO.star) {
                            Star()
                        }
                        XSpacer(4)
                        BoldText("${hotelDTO.star}")
                    }
                    Row {
                        PrimaryText("Chỉ từ ")
                        PrimaryText("${hotelDTO.displayPrice?.formatPrice()} VND ")
                        GreyText("/đêm")
                    }
                }
            }
        }

    }

}


@Composable
fun HotelTagBooked(
    isStatusVisible: Boolean,
    bookingData: UserBookingDTO,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                8.dp,
                shape = RoundedCornerShape(8.dp),
                clip = true,
                ambientColor = Grey100Color,
                spotColor = Grey500Color
            )
            .clickable {
                navController.navigate("${Screens.BookingDetailsScreen.name}/${Uri.encode(Gson().toJson(bookingData))}")
            },
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Row(modifier = Modifier.padding(12.dp)) {
                AsyncImage(
                    modifier = Modifier
                        .size(84.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)),
                    model = if (bookingData.hotel.logo != null) bookingData.hotel.logo.url else com.example.h5traveloto_booking.R.drawable.default_img,
                    contentDescription = "Hotel image",
                    contentScale = ContentScale.FillBounds,
                )

                Column(
                    modifier = Modifier
                        .padding(12.dp, 4.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.
                        fillMaxWidth(),
                        text = bookingData.hotel.name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )


                    YSpacer(4)

                    Text(
                        text = "${bookingData.hotel.ward.fullName}, ${bookingData.hotel.province.fullName}",
                        fontSize = 12.sp,
                        color = Grey500Color,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )

                    YSpacer(4)

                    Row {
                        GreyText("${FromStringtoDate(bookingData.startDate).dayOfMonth} " +
                                "Thg ${FromStringtoDate(bookingData.startDate).monthNumber} " +
                                "${FromStringtoDate(bookingData.startDate).year} " +
                                "- " +
                                "${FromStringtoDate(bookingData.endDate).dayOfMonth} " +
                                "Thg ${FromStringtoDate(bookingData.endDate).monthNumber} " +
                                "${FromStringtoDate(bookingData.endDate).year}"
                        )
                    }
                }
            }
        }

    }

}
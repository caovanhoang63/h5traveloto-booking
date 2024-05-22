package com.example.h5traveloto_booking.main.presentation.home.components

import android.app.Dialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.h5traveloto_booking.main.presentation.data.dto.Hotel.HotelDTO
import com.example.h5traveloto_booking.main.presentation.favorite.AllFavorite.formatPrice
import com.example.h5traveloto_booking.theme.Grey100Color
import com.example.h5traveloto_booking.theme.Grey500Color
import com.example.h5traveloto_booking.ui_shared_components.*


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
            ),
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
                        Star()
                        Star()
                        Star()
                        Star()
                        Star()
                        XSpacer(4)
                        BoldText("5.0")
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
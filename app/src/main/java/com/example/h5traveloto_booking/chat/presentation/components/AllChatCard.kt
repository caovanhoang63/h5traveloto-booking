package com.example.h5traveloto_booking.chat.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.h5traveloto_booking.main.presentation.favorite.AllFavorite.formatPrice
import com.example.h5traveloto_booking.main.presentation.home.components.ScrollingText
import com.example.h5traveloto_booking.main.presentation.home.components.ScrollingText2
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.share.ShareDataHotelDetail
import com.example.h5traveloto_booking.share.shareDataHotelDetail
import com.example.h5traveloto_booking.theme.Grey100Color
import com.example.h5traveloto_booking.theme.Grey500Color
import com.example.h5traveloto_booking.ui_shared_components.*

@Composable
fun AllChatCard(
    navController: NavController,
    inboxData: com.example.h5traveloto_booking.chat.presentation.data.dto.AllChat.Data
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
                navController.navigate(Screens.ChatScreen.name);
                shareDataHotelDetail.setHotelName(inboxData.hotel.name)
                shareDataHotelDetail.setHotelId(inboxData.hotel.id)
            },
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)

        ) {

            AsyncImage(
                modifier = Modifier
                    .size(84.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                model = inboxData.hotel.logo.url,
                contentDescription = "Hotel image",
                contentScale = ContentScale.FillBounds,
            )
            XSpacer(width = 16)
            Column(

                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                ScrollingText(inboxData.hotel.name)
                GreyText(text = inboxData.lastMessage!!.message)

            }
        }
    }

}


package com.example.h5traveloto_booking.details.presentation.roomdetails.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.h5traveloto_booking.details.presentation.data.dto.hotelDetails.Data
import com.example.h5traveloto_booking.details.presentation.data.dto.listRooms.ListRoomDTO
import com.example.h5traveloto_booking.details.presentation.hoteldetails.components.MultiColorText
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.theme.Grey500Color
import com.example.h5traveloto_booking.theme.Grey50Color
import com.example.h5traveloto_booking.theme.PrimaryColor
import com.example.h5traveloto_booking.ui_shared_components.BoldText
import com.example.h5traveloto_booking.ui_shared_components.GreyText
import com.example.h5traveloto_booking.ui_shared_components.YSpacer
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton

@Composable
fun RoomDetailCard(navController: NavController,roomDTO: com.example.h5traveloto_booking.details.presentation.data.dto.listRooms.Data) {
    val imagelist = listOf(
        //dummy data
        "https://media.istockphoto.com/id/104731717/photo/luxury-resort.jpg?s=612x612&w=0&k=20&c=cODMSPbYyrn1FHake1xYz9M8r15iOfGz9Aosy9Db7mI=",
        "https://media.istockphoto.com/id/104731717/photo/luxury-resort.jpg?s=612x612&w=0&k=20&c=cODMSPbYyrn1FHake1xYz9M8r15iOfGz9Aosy9Db7mI=",
        "https://media.istockphoto.com/id/104731717/photo/luxury-resort.jpg?s=612x612&w=0&k=20&c=cODMSPbYyrn1FHake1xYz9M8r15iOfGz9Aosy9Db7mI=",
        "https://media.istockphoto.com/id/104731717/photo/luxury-resort.jpg?s=612x612&w=0&k=20&c=cODMSPbYyrn1FHake1xYz9M8r15iOfGz9Aosy9Db7mI=",
        "https://media.istockphoto.com/id/104731717/photo/luxury-resort.jpg?s=612x612&w=0&k=20&c=cODMSPbYyrn1FHake1xYz9M8r15iOfGz9Aosy9Db7mI=",
        "https://media.istockphoto.com/id/104731717/photo/luxury-resort.jpg?s=612x612&w=0&k=20&c=cODMSPbYyrn1FHake1xYz9M8r15iOfGz9Aosy9Db7mI=",
        "https://media.istockphoto.com/id/104731717/photo/luxury-resort.jpg?s=612x612&w=0&k=20&c=cODMSPbYyrn1FHake1xYz9M8r15iOfGz9Aosy9Db7mI=",
        "https://media.istockphoto.com/id/104731717/photo/luxury-resort.jpg?s=612x612&w=0&k=20&c=cODMSPbYyrn1FHake1xYz9M8r15iOfGz9Aosy9Db7mI=",
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            /*.height(320.dp)*/,
        colors = CardDefaults.cardColors(
            containerColor = Grey50Color, //Card background color
        ),

        ) {
        Column(modifier = Modifier.padding(15.dp)) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                imagelist.forEachIndexed { index, image ->
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
                modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(), horizontalAlignment = Alignment.Start

                ) {
                    BoldText(text = roomDTO.name)

                }
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    /*verticalArrangement = Arrangement.SpaceBetween,*/
                    horizontalAlignment = Alignment.End
                ) {
                    MultiColorText(
                        Triple(roomDTO.price.toString(), PrimaryColor, FontWeight.Bold),
                        Triple(" /đêm", Grey500Color, FontWeight.Normal)
                    )
                    YSpacer(5)
                    PrimaryButton(
                        onClick = { navController.navigate(Screens.HotelDetailsScreen.name) },
                        text = "Chọn Phòng",
                    )
                }
            }
        }
    }
}
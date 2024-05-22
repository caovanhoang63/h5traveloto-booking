package com.example.h5traveloto_booking.main.presentation.home.components

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.h5traveloto_booking.main.presentation.data.dto.Hotel.HotelDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchHotel.Data
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchHotel.SearchHotelDTO
import com.example.h5traveloto_booking.main.presentation.favorite.AllFavorite.formatPrice
import com.example.h5traveloto_booking.theme.Grey100Color
import com.example.h5traveloto_booking.theme.Grey500Color
import com.example.h5traveloto_booking.ui_shared_components.*
import kotlinx.coroutines.delay
import kotlin.math.log


@Composable
fun HotelTagLarge(
    hotelDTO: Data,
    onClick: () -> Unit
) {
    Log.d("Success","hehehehe")
    Card (
        modifier = Modifier
            .padding(0.dp,0.dp,16.dp,0.dp).width(257.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .shadow(8.dp, shape = RoundedCornerShape(8.dp), clip = true, ambientColor = Grey100Color, spotColor = Grey500Color )
            .clickable { onClick() },
    ) {
        Column (modifier = Modifier.background(Color.White)){
            AsyncImage(
                modifier = Modifier.height(209.dp).fillMaxWidth(),
                model = hotelDTO.images[0].url,
                contentDescription = "Hotel image",
                contentScale = ContentScale.FillBounds,
            )

            Column (modifier = Modifier.padding(16.dp).fillMaxWidth().background(Color.White)) {
                Row (horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()) {
                    ScrollingText(hotelDTO.name, Modifier.weight(1f))
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Star()
                        XSpacer(4)
                        BoldText(hotelDTO.star.toString())
                    }
                }
                YSpacer(8)

                GreyText("${hotelDTO.district.fullName}, ${hotelDTO.province.fullName}")

                YSpacer(8)
                Row {
                    PrimaryText("${hotelDTO.displayPrice?.formatPrice()} VND")
                    GreyText("/đêm")
                }
            }
        }
    }
}



@Composable
fun ScrollingText(hotelName: String, modifier: Modifier = Modifier) {
    val offsetX = remember { Animatable(0f) }

    LaunchedEffect(hotelName) {
        val textWidth = 500f // Chiều rộng giả định của văn bản
        while (true && hotelName.length > 21) {
            offsetX.animateTo(
                targetValue = -textWidth,
                animationSpec = tween(durationMillis = 3000, easing = LinearEasing)
            )
            offsetX.snapTo(0f)
            delay(500) // Khoảng dừng giữa các lần lặp lại
        }
    }

    Box(
        modifier = Modifier.then(modifier)
            .clipToBounds()
    ) {
        BasicText(
            text = hotelName,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            softWrap = false,
            maxLines = 1,
            overflow = TextOverflow.Visible,
            modifier = Modifier
                .offset { IntOffset(offsetX.value.toInt(), 0) }
        )
    }
}
@Composable
fun ScrollingText2(hotelName: String, modifier: Modifier = Modifier) {
    val offsetX = remember { Animatable(0f) }

    LaunchedEffect(hotelName) {
        val textWidth = 800f // Chiều rộng giả định của văn bản
        while (true && hotelName.length > 40) {
            offsetX.animateTo(
                targetValue = -textWidth,
                animationSpec = tween(durationMillis = 6000, easing = LinearEasing)
            )
            offsetX.snapTo(0f)
            delay(1000) // Khoảng dừng giữa các lần lặp lại
        }
    }

    Box(
        modifier = Modifier.then(modifier)
            .clipToBounds()
    ) {
        BasicText(
            text = hotelName,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium

            ),
            softWrap = false,
            maxLines = 1,
            overflow = TextOverflow.Visible,
            modifier = Modifier
                .offset { IntOffset(offsetX.value.toInt(), 0) }
        )
    }
}

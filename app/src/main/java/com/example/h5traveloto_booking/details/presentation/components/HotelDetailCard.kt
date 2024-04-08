package com.example.h5traveloto_booking.details.presentation.components

import android.widget.RatingBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.h5traveloto_booking.theme.Grey500Color
import com.example.h5traveloto_booking.theme.PrimaryColor
import com.example.h5traveloto_booking.ui_shared_components.GreyText
import com.example.h5traveloto_booking.ui_shared_components.PrimaryText
import com.example.h5traveloto_booking.ui_shared_components.XSpacer
import com.example.h5traveloto_booking.ui_shared_components.YSpacer

@Composable
fun HotelDetailCard() {
    var rating by remember { mutableStateOf(1f) } //default rating will be 1
    val imagelist = listOf( //dummy data
        "https://cdn.pixabay.com/photo/2023/11/23/20/40/ocean-8408693_640.jpg",
        "https://cdn.pixabay.com/photo/2014/11/03/11/06/hippo-515027_640.jpg",
        "https://cdn.pixabay.com/photo/2014/11/03/11/06/hippo-515027_640.jpg",
        "https://cdn.pixabay.com/photo/2014/11/03/11/06/hippo-515027_640.jpg",
        "https://cdn.pixabay.com/photo/2014/11/03/11/06/hippo-515027_640.jpg",
        "https://cdn.pixabay.com/photo/2014/11/03/11/06/hippo-515027_640.jpg",
        "https://cdn.pixabay.com/photo/2014/11/03/11/06/hippo-515027_640.jpg",
        "https://cdn.pixabay.com/photo/2014/11/03/11/06/hippo-515027_640.jpg",
        "https://cdn.pixabay.com/photo/2014/11/03/11/06/hippo-515027_640.jpg",
        "https://cdn.pixabay.com/photo/2014/11/03/11/06/hippo-515027_640.jpg",
        "https://cdn.pixabay.com/photo/2014/11/03/11/06/hippo-515027_640.jpg",
        "https://cdn.pixabay.com/photo/2014/11/03/11/06/hippo-515027_640.jpg",
        "https://cdn.pixabay.com/photo/2014/11/03/11/06/hippo-515027_640.jpg",
        "https://cdn.pixabay.com/photo/2014/11/03/11/06/hippo-515027_640.jpg",
        "https://cdn.pixabay.com/photo/2014/11/03/11/06/hippo-515027_640.jpg",
        "https://cdn.pixabay.com/photo/2014/11/03/11/06/hippo-515027_640.jpg",
        "https://cdn.pixabay.com/photo/2014/11/03/11/06/hippo-515027_640.jpg",
        "https://cdn.pixabay.com/photo/2014/11/03/11/06/hippo-515027_640.jpg",
        "https://cdn.pixabay.com/photo/2024/02/12/14/56/woman-8568693_640.jpg"
    )
    Card(
        modifier = Modifier.fillMaxWidth()
            .height(232.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFFFF), //Card background color
        )
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            LazyRow(
                modifier = Modifier.fillMaxWidth().height(110.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                imagelist.forEachIndexed { index, image ->
                    item {
                        AsyncImage(
                            model = image,
                            contentDescription = "Hotel Image",
                            modifier = Modifier.size(110.dp)
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
                    Text(text = "Asteria hotel", style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold))
                    YSpacer(5)
                    GreyText(text = "Wilora NT 0872, Australia")
                    YSpacer(5)
                    StarRatingBar(
                        maxStars = 5,
                        rating = rating,
                        onRatingChanged = {
                            rating = it
                        }
                    )
                }
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.End
                ) {
                    PrimaryText(text = "9.1 (1000)")
                    MultiColorText(
                        Triple("$165.3", PrimaryColor, FontWeight.Bold),
                        Triple(" /night", Grey500Color, FontWeight.Normal)
                    )


                }
            }
        }
    }
}
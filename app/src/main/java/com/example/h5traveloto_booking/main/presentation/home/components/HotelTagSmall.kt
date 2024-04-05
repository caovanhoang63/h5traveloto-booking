package com.example.h5traveloto_booking.main.presentation.home.components

import android.app.Dialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.h5traveloto_booking.ui_shared_components.*


@Composable
fun HotelTagSmall( ){
    Card (
        modifier = Modifier.height(108.dp).fillMaxWidth(),
    ) {
        Column (Modifier.fillMaxSize().background(Color.White)) {
            Row (modifier = Modifier.padding(12.dp)) {
                AsyncImage(
                    modifier = Modifier.size(84.dp).fillMaxWidth().clip(RoundedCornerShape(8.dp)),
                    model = "https://cdn.vietnambiz.vn/2019/11/4/dd32d9b188d86d6d8dc40d1ff9a0ebf6-15728512315071030248829.jpg",
                    contentDescription = "Hotel image",
                    contentScale = ContentScale.FillBounds,
                )

                Column (modifier = Modifier.padding(12.dp,16.5.dp).fillMaxWidth()) {
                    Row (horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()) {
                        BoldText("Aston Hotel")
                        Row {
                            PrimaryText("$")
                            PrimaryText("200,7")
                            GreyText("/night")
                        }
                    }
                    YSpacer(8)

                    GreyText("Alice Springs NT 0870, Australia")

                    YSpacer(8)

                    Row {
                        AppLogo()
                        BoldText("5.0")
                    }
                }
            }
        }

    }

}
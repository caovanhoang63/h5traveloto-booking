package com.example.h5traveloto_booking.details.presentation.roomdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.details.presentation.roomdetails.components.RoomDetailTag
import com.example.h5traveloto_booking.details.presentation.roomdetails.components.RoomFacilitiesList
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.ui_shared_components.*

@Composable
fun RoomDetailsScreen() {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PrimaryIconButton(
                    DrawableId = R.drawable.backbutton, onClick = { /*navController.popBackStack()*/ }, alt = ""
                )
                BoldText(text = "Chi tiết Phòng")

            }
        },
    ) { innerpadding ->
        Column(
            modifier = Modifier.padding(innerpadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp),
            ) {
                item {
                    BoldText20(text = "Junior Suite")
                    YSpacer(height = 10)
                    HorizontalDivider(thickness = 0.8.dp, color = Color.LightGray)
                    YSpacer(height = 10)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        RoomDetailTag(
                            icon = R.drawable.baseline_people_24,
                            text = "Khách",
                            description = "2 Người lớn")
                        RoomDetailTag(
                            icon = R.drawable.baseline_business_24,
                            text = "Kích thước Phòng",
                            description = "83.0 m2"
                        )
                    }
                    YSpacer(height = 10)
                    HorizontalDivider(thickness = 0.8.dp, color = Color.LightGray)
                    YSpacer(height = 10)

                    BoldText2(text = "Tiện ích phòng")
                    RoomFacilitiesList(
                        items = listOf(
                            "First bullet",
                            "Second bullet ... which is awfully long but that's not a problem",
                            "Third bullet ",
                        ),
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                        ),
                        lineSpacing = 8.dp,
                    )


                }
            }
        }
    }
}
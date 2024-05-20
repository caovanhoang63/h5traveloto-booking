package com.example.h5traveloto_booking.details.presentation.hoteldetails.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.ui_shared_components.BoldText
import com.example.h5traveloto_booking.ui_shared_components.PrimaryIconButton

@Composable
fun ListPolicies(navController: NavController) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                PrimaryIconButton(
                    DrawableId = R.drawable.backbutton, onClick = { navController.popBackStack() }, alt = ""
                )
                BoldText(text = "Chính Sách Lưu Trú")
                Spacer(modifier = Modifier.weight(1f))

            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(24.dp)
        ) {
            HotelDetailPolicyCard(
                icon = R.drawable.baseline_access_time_24,
                text = "Giờ nhận phòng/trả phòng",
                description = "Nhận phòng: 14:00 - Trả phòng: 12:00"
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(thickness = 0.8.dp, color = Color.LightGray)
            //
            Spacer(modifier = Modifier.height(16.dp))
            HotelDetailPolicyCard(
                icon = R.drawable.baseline_child_care_24,
                text = "Chính sách về độ tuổi tối thiểu",
                description = "Trẻ em từ 0-5 tuổi"
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(thickness = 0.8.dp, color = Color.LightGray)
            //
            Spacer(modifier = Modifier.height(16.dp))
            HotelDetailPolicyCard(
                icon = R.drawable.baseline_smoking_rooms_24,
                text = "Hút thuốc",
                description = "Cơ sở lưu trú cấm hút thuốc"
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(thickness = 0.8.dp, color = Color.LightGray)
            //
            Spacer(modifier = Modifier.height(16.dp))
            HotelDetailPolicyCard(
                icon = R.drawable.baseline_pets_24,
                text = "Thú cưng",
                description = "Không được mang thú cưng"
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(thickness = 0.8.dp, color = Color.LightGray)
            //
            Spacer(modifier = Modifier.height(16.dp))
            HotelDetailPolicyCard(
                icon = R.drawable.baseline_edit_note_24,
                text = "Chính sách bổ sung",
                description = ""
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(thickness = 0.8.dp, color = Color.LightGray)


        }

    }
}
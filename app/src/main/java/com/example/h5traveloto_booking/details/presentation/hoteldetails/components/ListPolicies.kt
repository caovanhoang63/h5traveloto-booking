package com.example.h5traveloto_booking.details.presentation.hoteldetails.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.details.presentation.data.dto.hotelDetails.Data
import com.example.h5traveloto_booking.details.presentation.data.dto.hotelDetails.HotelDetailsDTO
import com.example.h5traveloto_booking.details.presentation.hoteldetails.HotelDetailsScreenViewModel
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.ui_shared_components.BoldText
import com.example.h5traveloto_booking.ui_shared_components.BoldText2
import com.example.h5traveloto_booking.ui_shared_components.PrimaryIconButton
import com.example.h5traveloto_booking.util.Result

@Composable
fun ListPolicies(
    navController: NavController,
    Object: Data,
) {

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    PrimaryIconButton(
                        DrawableId = R.drawable.backbutton, onClick = { navController.popBackStack() }, alt = ""
                    )
                    BoldText2(text = "Chính Sách Lưu Trú", modifier = Modifier.align(Alignment.Center))

                }

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
                description = "Nhận phòng: ${Object.checkInTime} - Trả phòng: ${Object.checkOutTime}"
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(thickness = 0.8.dp, color = Color.LightGray)
            //
            Spacer(modifier = Modifier.height(16.dp))
            HotelDetailPolicyCard(
                icon = R.drawable.baseline_child_care_24,
                text = "Chính sách về độ tuổi tối thiểu",
                description = "Trẻ em từ trên ${Object.minimumAge} tuổi"
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(thickness = 0.8.dp, color = Color.LightGray)
            //
            Spacer(modifier = Modifier.height(16.dp))
            if (Object.smokingPolicy != "free") {
                HotelDetailPolicyCard(
                    icon = R.drawable.baseline_smoking_rooms_24,
                    text = "Hút thuốc",
                    description = "Cơ sở lưu trú cấm hút thuốc"
                )
            } else HotelDetailPolicyCard(
                icon = R.drawable.baseline_smoking_rooms_24,
                text = "Hút thuốc",
                description = "Cơ sở lưu trú được phép hút thuốc"
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(thickness = 0.8.dp, color = Color.LightGray)
            //
            Spacer(modifier = Modifier.height(16.dp))
            if (Object.petPolicy != "free") {
                HotelDetailPolicyCard(
                    icon = R.drawable.baseline_pets_24,
                    text = "Thú cưng",
                    description = "Không được mang thú cưng"
                )
            } else HotelDetailPolicyCard(
                icon = R.drawable.baseline_pets_24,
                text = "Thú cưng",
                description = "Được mang thú cưng"
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(thickness = 0.8.dp, color = Color.LightGray)
            //
            Spacer(modifier = Modifier.height(16.dp))
            HotelDetailPolicyCard(
                icon = R.drawable.baseline_edit_note_24,
                text = "Chính sách bổ sung",
                description = Object.additionalPolicies
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(thickness = 0.8.dp, color = Color.LightGray)
        }
    }


}

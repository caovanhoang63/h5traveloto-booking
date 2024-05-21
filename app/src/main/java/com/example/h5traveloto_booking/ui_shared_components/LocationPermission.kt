package com.example.h5traveloto_booking.ui_shared_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.theme.PrimaryColor
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton

@Composable
fun ButtonRequestLocationPermission(
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().height(300.dp)
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ){
            Icon(
                Icons.Default.MyLocation,
                contentDescription = "Request Location Permission",
                modifier = Modifier.size(30.dp),
                tint = PrimaryColor
            )
            YSpacer(16)
            Text(
                text = "Không thể tìm thấy vị trí hiện tại",
                style = TextStyle(fontWeight = FontWeight.Bold,fontSize = 16.sp),
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            YSpacer(8)
            Text(
                text = "Bạn chưa kích hoạt GPS/Dịch vụ định vị",
                style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp),
                modifier = Modifier.padding(horizontal = 20.dp),
            )
            YSpacer(10)
            PrimaryButton(
                onClick = onClick,
                text = "Kích hoạt ngay",
                modifier = Modifier.size(200.dp, 40.dp)
            )
        }
    }
}

@Composable
fun NotFoundHotel(){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Không tìm thấy khách sạn",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp),
        )
    }
}

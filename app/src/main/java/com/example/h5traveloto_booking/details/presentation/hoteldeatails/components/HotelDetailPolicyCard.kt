package com.example.h5traveloto_booking.details.presentation.hoteldeatails.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.wear.compose.material.Icon
import com.example.h5traveloto_booking.ui_shared_components.BoldText
import com.example.h5traveloto_booking.ui_shared_components.GreyText
import com.example.h5traveloto_booking.ui_shared_components.XSpacer
import com.example.h5traveloto_booking.ui_shared_components.YSpacer

@Composable
fun HotelDetailPolicyCard(icon : ImageVector, text: String, description:String) {
    Row(){
        Icon(
            imageVector = icon,
            contentDescription = "Money",
            tint = Color.Black,
        )
        XSpacer(width = 10)
        Column {
            BoldText(text = text)
            YSpacer(height = 3)
            GreyText(text = description)
        }
    }
}
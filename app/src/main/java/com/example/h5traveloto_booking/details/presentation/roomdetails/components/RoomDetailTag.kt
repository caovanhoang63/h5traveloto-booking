package com.example.h5traveloto_booking.details.presentation.roomdetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.h5traveloto_booking.theme.Grey100Color
import com.example.h5traveloto_booking.theme.Grey500Color
import com.example.h5traveloto_booking.theme.Grey50Color
import com.example.h5traveloto_booking.theme.PrimaryColor
import com.example.h5traveloto_booking.ui_shared_components.*

@Composable
fun RoomDetailTag(icon : Int, text: String, description:String) {
    Row(){
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "Money",
            tint = PrimaryColor,// Icon chiếm 10% Row width

        )
        XSpacer(width = 10)
        Column(
        ) {
            BoldText(text = text)
            YSpacer(height = 3)
            GreyText16(text = description)

        }
    }
}
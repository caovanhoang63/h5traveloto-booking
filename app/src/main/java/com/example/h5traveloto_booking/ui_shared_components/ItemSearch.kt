package com.example.h5traveloto_booking.ui_shared_components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.h5traveloto_booking.theme.Grey100Color
import com.example.h5traveloto_booking.theme.PrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemSearch(
    title: String,
    detail: String,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    type: String,
    isFirst: Boolean = false
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .padding(20.dp,8.dp)
            .clickable { onClick() }
    ) {
        icon()
        Column(
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
            if(detail.isNotEmpty()) {
                Text(
                    text = detail,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
        if(type.isNotEmpty())
        {
            Box(
                modifier = Modifier
                    .height(36.dp)
                    .padding(8.dp,4.dp)
                    .background(Color.Transparent)
                    .background(
                        color = Color(0x204C4DDC),
                        shape = RoundedCornerShape(18.dp)),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = type,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(12.dp,0.dp)
                        .align(Alignment.Center),
                    style = TextStyle(
                        color = PrimaryColor,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
    if(!isFirst) {
        Divider(
            color = Grey100Color,
            thickness = 1.dp,
            modifier = Modifier.padding(20.dp, 0.dp)
        )
    } else{
        Divider(
            color = Grey100Color,
            thickness = 1.dp,
        )
    }
}
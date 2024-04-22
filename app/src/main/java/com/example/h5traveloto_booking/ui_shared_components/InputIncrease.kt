package com.example.h5traveloto_booking.ui_shared_components

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.theme.Grey50Color
import com.example.h5traveloto_booking.theme.PrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputIncrease(icon: @Composable () -> Unit,title: String, value: Int, onIncrease: () -> Unit, onDecrease: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(16.dp, 0.dp)
            .height(58.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        icon()
        Text(
            text = title,
            fontWeight = FontWeight(500),
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp,0.dp)
                .weight(1f)
        )
        Row(
            modifier = Modifier.width(130.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { onDecrease() },
                modifier = Modifier
                    .size(40.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Grey50Color
                ),
                contentPadding = PaddingValues(0.dp)
            ){
                Text("-", fontSize = 20.sp, color = PrimaryColor)
            }

            Text(text = value.toString(), fontSize = 20.sp, fontWeight = FontWeight(500),
            )

            Button(
                onClick = { onIncrease() },
                modifier = Modifier
                    .size(40.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Grey50Color
                ),
                contentPadding = PaddingValues(0.dp)
            ){
                Text("+", fontSize = 20.sp, color = PrimaryColor,)
            }
        }
    }
}
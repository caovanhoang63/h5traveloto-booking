package com.example.h5traveloto_booking.details.presentation.hoteldetails.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.theme.StarColor

@Composable
fun StarIconBox(
    state: MutableState<Boolean>,
    starNumber: String,
    onClick: (String) -> Unit
) {
    Row(
        Modifier
            .width(60.dp)
            .fillMaxHeight()
            .border(1.dp, if (state.value) StarColor else Color.Gray)
            .clickable {
                state.value = !state.value
                onClick(starNumber) // Gọi callback khi click

            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = starNumber,
            style = androidx.compose.ui.text.TextStyle(
                color = if (state.value) StarColor else Color.Gray
            )
        )
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.baseline_star_16),
            contentDescription = "Favorite Icon",
            tint = if (state.value) StarColor else Color.Gray
        )

    }
}
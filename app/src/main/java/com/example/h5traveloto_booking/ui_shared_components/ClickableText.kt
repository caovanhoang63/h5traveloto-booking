@file:Suppress("UNUSED_EXPRESSION")

package com.example.h5traveloto_booking.ui_shared_components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.theme.PrimaryColor

@Composable
fun ClickableText(text : String, onClick : () -> Unit) {
    Text(
        text = text,
        fontSize = 16.sp,
        color = PrimaryColor,
        modifier = Modifier.clickable { onClick}
    )
}
package com.example.h5traveloto_booking.details.presentation.hoteldetails.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun MultiColorText(vararg textWithColors: Triple<String, Color, FontWeight>) {
    Text(buildAnnotatedString {
        textWithColors.forEach { (text, color, fontWeight) ->
            withStyle(
                style = SpanStyle(
                    color = color,
                    fontWeight = fontWeight,
                    fontSize = 16.sp,
                )
            ) {
                append(text)
            }
        }
    })
}
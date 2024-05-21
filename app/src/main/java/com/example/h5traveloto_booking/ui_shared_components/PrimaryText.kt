package com.example.h5traveloto_booking.ui_shared_components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.h5traveloto_booking.theme.PrimaryColor

@Composable
fun PrimaryText(text : String,modifier: Modifier = Modifier ) {
        Text(
            modifier = Modifier.then(modifier),
            fontSize = 14.sp,
            fontWeight =  FontWeight.Bold,
            color = PrimaryColor,
            text = text

        )
}
@Composable
fun PrimaryText2(text : String,modifier: Modifier = Modifier,style: TextStyle ) {
    Text(
        modifier = Modifier.then(modifier),
        fontSize = 14.sp,
        fontWeight =  FontWeight.Bold,
        color = PrimaryColor,
        text = text,
        style = style,

    )
}
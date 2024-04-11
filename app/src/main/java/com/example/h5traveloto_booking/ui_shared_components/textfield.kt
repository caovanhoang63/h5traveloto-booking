package com.example.h5traveloto_booking.ui_shared_components

import android.graphics.drawable.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.h5traveloto_booking.theme.PrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldIconLeading(modifier: Modifier,value: String, onValueChange: (String) -> Unit, placeholder: String, leadingIcon:  @Composable () -> Unit){
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = { Text(placeholder) },
        leadingIcon = leadingIcon,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = PrimaryColor,
            containerColor = Color.Transparent,
        ),
        maxLines = 1,
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 14.sp,
            lineHeight = 14.sp
        )
    )
}
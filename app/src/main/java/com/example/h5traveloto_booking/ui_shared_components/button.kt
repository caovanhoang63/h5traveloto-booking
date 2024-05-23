package com.example.h5traveloto_booking.util.ui_shared_components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.h5traveloto_booking.theme.PrimaryColor

@Composable
fun PrimaryButton(onClick : ()->Unit,  text : String = "", modifier : Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = Modifier.then(modifier).height(52.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(PrimaryColor)
    ) {
        Text(text, fontSize = 16.sp)
    }
}

@Composable
fun PrimaryButton2(onClick : ()->Unit,  text : String = "", modifier : Modifier = Modifier,enabled:Boolean) {
    Button(
        enabled =enabled,
        onClick = onClick,
        modifier = Modifier.then(modifier).height(52.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(PrimaryColor)
    ) {
        Text(text, fontSize = 16.sp)
    }
}
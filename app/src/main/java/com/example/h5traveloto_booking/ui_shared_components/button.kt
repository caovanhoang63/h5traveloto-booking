package com.example.h5traveloto_booking.util.ui_shared_components

import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.h5traveloto_booking.theme.PrimaryColor

@Composable
fun PrimaryButton(onClick : ()->Unit ,modifier : androidx.compose.ui.Modifier, text : String) {
    Button(
        onClick = onClick,
        modifier = androidx.compose.ui.Modifier.then(modifier).height(52.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(PrimaryColor)
    ) {
        Text(text)
    }
}
package com.example.h5traveloto_booking.ui_shared_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.h5traveloto_booking.R



// AppLogo Return an image of logo with default size is 20dp
@Composable
fun AppLogo(size : Int = 20 ) {
    Image(
        painter = painterResource(id = R.drawable.onlylogo),
        contentDescription = "LoginLogo",
        modifier = Modifier.size(size.dp)
    )
}
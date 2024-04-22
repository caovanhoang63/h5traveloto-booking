package com.example.h5traveloto_booking.auth.presentation.signup.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.h5traveloto_booking.R

@Composable
fun SignUpHeader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.onlylogo),
            contentDescription = "logo",
            modifier = Modifier
                .height(45.dp)
                .width(50.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Create Account", fontSize = 30.sp, fontWeight = FontWeight.Bold)
    }
}
package com.example.h5traveloto_booking.ui_shared_components
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.h5traveloto_booking.theme.BorderStroke


@Composable
fun PrimaryIconButton(DrawableId : Int ,onClick : () -> Unit, alt : String , modifier: Modifier = Modifier ){
    Button(
        onClick = onClick,
        modifier = modifier
            .border(2.dp, BorderStroke, RoundedCornerShape(8.dp))
            .padding(0.dp).size(40.dp),
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(0.dp), // cái củ lồn này làm mất hơn 2 tiếng của bố m, đkm m
        colors = ButtonDefaults.buttonColors(Color.Transparent),
    ) {
        Icon(
            painter = painterResource(id = DrawableId),
            contentDescription = alt,
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
    }

}


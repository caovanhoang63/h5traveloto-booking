package com.example.h5traveloto_booking.ui_shared_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.theme.PrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopupRegisterSuccess(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    textButton: String
) {
    AlertDialog(
        icon = {
            Image(painterResource(id = R.drawable.onlylogo), contentDescription = "logo", modifier = Modifier.size(40.dp))
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(text = dialogText)
            }
        },
        onDismissRequest = {
           onDismissRequest()
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirmation()
                },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryColor,
                    contentColor = Color.White
                ),
            ) {
                Text(text = textButton)
            }
        },
        containerColor = Color.White,
    )
}
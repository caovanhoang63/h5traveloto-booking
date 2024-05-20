package com.example.h5traveloto_booking.details.presentation.hoteldetails.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SliderTextBox(number: String,modifier: Modifier){

    OutlinedTextField(
        
        modifier = modifier,
        value = "$number VND",
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.White,
            containerColor = Color.Transparent,
            focusedLabelColor = Color.White
        ),
        onValueChange = { },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        readOnly = true
    )

}
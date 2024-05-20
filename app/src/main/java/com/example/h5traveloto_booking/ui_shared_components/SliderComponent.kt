package com.example.h5traveloto_booking.ui_shared_components

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.RangeSlider
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.h5traveloto_booking.details.presentation.hoteldetails.components.SliderTextBox
import com.example.h5traveloto_booking.util.ui_shared_components.TextBox
import com.example.h5traveloto_booking.util.ui_shared_components.TextBoxSingle

@Composable
fun SliderComponent(maxValue: Float, steps: Int) {
    var sliderPosition by remember { mutableFloatStateOf(maxValue) }
    Column {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            SliderTextBox(number = "0", modifier = Modifier.width(150.dp))
            SliderTextBox(number = sliderPosition.toString(), modifier = Modifier.width(150.dp))
        }
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 0f..maxValue,
            steps = steps,
            )
    }
}
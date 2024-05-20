package com.example.h5traveloto_booking.ui_shared_components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RadioButtonComponent(
    radioOptions: List<String>,
    onOptionSelected: (String) -> Unit
) {
    val (selectedOption, onOptionChangedInternal) = remember { mutableStateOf(radioOptions[0]) }
    Column {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionChangedInternal(text)
                            onOptionSelected(text) // Gọi hàm callback
                        }
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = {
                        onOptionChangedInternal(text)
                        onOptionSelected(text) // Gọi hàm callback
                    }
                )
                Text(
                    text = text,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
            HorizontalDivider(thickness = 0.8.dp, color = Color.LightGray)
        }
    }
}

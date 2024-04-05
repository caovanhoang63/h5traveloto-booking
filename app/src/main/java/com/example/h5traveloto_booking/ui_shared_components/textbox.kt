package com.example.h5traveloto_booking.util.ui_shared_components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextBox(modifier: Modifier,placeholder: String,label: String, value: String, onValueChange: (String) -> Unit) {
        OutlinedTextField(
            placeholder = { Text(placeholder) },
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            modifier =    modifier,
        )
}
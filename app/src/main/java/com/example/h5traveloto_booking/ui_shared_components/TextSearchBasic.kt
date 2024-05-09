package com.example.h5traveloto_booking.ui_shared_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.reflect.KProperty
import com.example.h5traveloto_booking.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextSearchBasic(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier,
) {
    Row(
        modifier = modifier
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){
        Spacer(modifier = Modifier.width(10.dp))
        Image(painter = painterResource(id = R.drawable.search), contentDescription = "search", modifier = Modifier.size(24.dp))
        BasicTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            modifier = Modifier
                .background(Color.White)
                .padding(8.dp,0.dp)
                .fillMaxWidth()
                .background(Color.Transparent),
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 14.sp,
                lineHeight = 16.sp,
            ),
            singleLine = true,
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(placeholder, color = Color.Gray, fontSize = 14.sp)
                }
                innerTextField()
            }
        )
    }
}

package com.example.h5traveloto_booking.chat.presentation.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.chat.presentation.data.dto.SendMessageDTO
import com.example.h5traveloto_booking.theme.Grey50Color
import com.example.h5traveloto_booking.theme.PrimaryColor
import websocket.socketHandler1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTextField(
    text: String,
    modifier: Modifier,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = text, onValueChange = { onValueChange(it) },
        placeholder = {
            Text(
                text = "Nhập tin nhắn",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Black
                ),
                textAlign = TextAlign.Center
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.White,
            containerColor = Color.Transparent,
            focusedLabelColor = Color.White
        ),
        trailingIcon = {
            ChatSendButton(
                Icons.Default.Send,
                onClick = {
                    Log.d("ChatTextField", "Send message: $text")
                    socketHandler1.sendMessage(
                        SendMessageDTO(
                            "66472bbdf70ec79d3c5d6709", text
                        )
                    )
                },
            )
        },
        modifier = Modifier.fillMaxWidth(),
    )

}

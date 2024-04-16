package com.example.h5traveloto_booking.main.presentation.homesearch.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.h5traveloto_booking.theme.OrangeColor
import com.example.h5traveloto_booking.theme.PrimaryColor
import com.example.h5traveloto_booking.ui_shared_components.InputIncrease
import com.example.h5traveloto_booking.R

@Composable
fun ChoosePersonScreen(
    onDismiss: () -> Unit,
    onConfirm: (adult: Int,child: Int, room: Int) -> Unit,
) {
    var adult by rememberSaveable { mutableStateOf(1) }
    var child by rememberSaveable { mutableStateOf(0) }
    var room by rememberSaveable { mutableStateOf(1) }

    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .background(Color.White),
            shape = RoundedCornerShape(0.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 0.dp, 0.dp, 0.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                        .background(PrimaryColor),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Khách & Phòng",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp),
                    )
                    IconButton(onClick = { onDismiss() },
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 16.dp, 0.dp)
                            .size(24.dp)
                        ) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
                    }
                }
                Box(
                    modifier = Modifier.weight(1f)
                ){
                    Column(){
                        InputIncrease(icon = {Image(painterResource(id = R.drawable.adult_icon_search), contentDescription = "", modifier = Modifier.size(20.dp))}, title = "Người lớn", value = adult, { if (adult < 32) adult++ }, { if(adult > 1) adult-- })
                        Spacer(modifier = Modifier.height(16.dp))

                        InputIncrease(icon = {Image(painterResource(id = R.drawable.child_icon_search), contentDescription = "", modifier = Modifier.size(22.dp))}, title = "Trẻ em", value = child, { if (child < 6) child++ }, { if(child > 0) child-- })
                        Spacer(modifier = Modifier.height(16.dp))

                        InputIncrease(icon = {Image(painterResource(id = R.drawable.room_icon_search), contentDescription = "", modifier = Modifier.size(20.dp))}, title = "Phòng", value = room, { if(room < 8) room++ }, { if (room > 1) room-- })
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 0.dp, 0.dp, 0.dp)
                ) {
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.LightGray)
                    )
                    Button(
                        onClick = { onConfirm(adult,child,room) },
                        modifier = Modifier.fillMaxWidth()
                            .height(100.dp)
                            .padding(24.dp, 24.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = OrangeColor,
                        )
                    ) {
                        Text("Chọn",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )
                    }
                }
            }
        }
    }
}
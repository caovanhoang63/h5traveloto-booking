package com.example.h5traveloto_booking.chat.presentation

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.chat.presentation.components.AllChatCard
import com.example.h5traveloto_booking.details.presentation.roomdetails.components.NotFoundRoomTypes
import com.example.h5traveloto_booking.details.presentation.roomdetails.components.RoomDetailCard
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.ui_shared_components.BoldText2
import com.example.h5traveloto_booking.ui_shared_components.PrimaryIconButton
import com.example.h5traveloto_booking.ui_shared_components.YSpacer
import com.example.h5traveloto_booking.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun AllChatScreen(
    navController: NavController,
    viewModel: AllChatScreenViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getAllChat()
    }

    val allChatResponse = viewModel.allChatResponse.collectAsState().value

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    PrimaryIconButton(
                        DrawableId = R.drawable.backbutton,
                        onClick = {
                            Log.d("nav", "nav");
                            navController.popBackStack()
                        },
                        alt = ""
                    )
                    BoldText2(text = "Danh sách tin nhắn", modifier = Modifier.align(Alignment.Center))

                }

            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(24.dp)
        ) {
            when (allChatResponse) {
                is Result.Loading -> {
                    Log.d("allChat", "dang load")

                    // Hieu ung load
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator()

                    }
                }

                is Result.Error -> {
                    Log.d("allChat", "loi roi")
                    NotFoundRoomTypes()
                }

                is Result.Success -> {
                    Log.d("allChat", "thanh cong")
                    val messages = allChatResponse.data.data

                    messages.forEachIndexed { index, inbox ->
                        if (inbox.lastMessage != null) {
                            AllChatCard(inboxData = inbox, navController = navController)
                            YSpacer(height = 10)
                        }
                    }
                }

                else -> Unit
            }
        }
    }
}
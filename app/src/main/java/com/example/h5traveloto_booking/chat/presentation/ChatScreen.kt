package com.example.h5traveloto_booking.chat.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.chat.presentation.components.ChatRow
import com.example.h5traveloto_booking.chat.presentation.components.ChatTextField
import com.example.h5traveloto_booking.chat.presentation.components.chatList
import com.example.h5traveloto_booking.details.presentation.hoteldetails.HotelDetailsScreenViewModel
import com.example.h5traveloto_booking.details.presentation.roomdetails.components.RoomDetailCard
import com.example.h5traveloto_booking.ui_shared_components.PrimaryIconButton
import com.example.h5traveloto_booking.util.Result

@Composable
fun ChatScreen(
    navController: NavController,
    viewModel: ChatListViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getChatList()
    }
    val chatListResponse = viewModel.ChatListResponse.collectAsState().value


    var message by remember { mutableStateOf("") }
    Scaffold(
        containerColor = Color.White,
        topBar = {
            PrimaryIconButton(
                DrawableId = R.drawable.backbutton, onClick = { navController.popBackStack() }, alt = "",
                modifier = Modifier.padding(start = 15.dp, top = 15.dp)
            )
        },
        bottomBar = {
            ChatTextField(
                text = message, onValueChange = { message = it },
                modifier = Modifier
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Color.White
                        )
                        .padding(top = 25.dp)

                ) {
                    LazyColumn(
                        modifier = Modifier.padding(
                            start = 15.dp,
                            end = 15.dp,
                        )
                    ) {
                        item {
                            when (chatListResponse) {
                                is Result.Loading -> {
                                    Log.d("ChatList", "dang load")

                                    // Hieu ung load
                                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                        CircularProgressIndicator()

                                    }
                                }

                                is Result.Error -> {
                                    Log.d("ChatList", "loi roi")
                                }

                                is Result.Success -> {
                                    Log.d("ChatList", "thanh cong")
                                    val chatlist = chatListResponse.data.data
                                    chatlist.forEachIndexed { index, chatListDTO ->
                                        ChatRow(chat = chatlist[index])
                                    }
                                }

                                else -> Unit

                            }
                            /*items(chatList, key = { it.id }) {
                            ChatRow(chat = it)
                        }*/
                        }
                    }
                }
            }
        }
    }
}
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
import androidx.compose.ui.platform.LocalFocusManager
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
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchHotel.Data
import com.example.h5traveloto_booking.share.shareDataHotelDetail
import com.example.h5traveloto_booking.ui_shared_components.BoldText2
import com.example.h5traveloto_booking.ui_shared_components.PrimaryIconButton
import com.example.h5traveloto_booking.util.Result
import websocket.socketHandler1

@Composable
fun ChatScreen(
    navController: NavController,
    viewModel: ChatListViewModel = hiltViewModel(),
    chatRoomviewModel: ChatRoomViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getChatList()
        chatRoomviewModel.getChatRoom()
    }
    val focusManager = LocalFocusManager.current

    val chatListResponse = viewModel.ChatListResponse.collectAsState().value
    var roomId by remember {
        mutableStateOf("")
    };


    val chatRoomResponse = chatRoomviewModel.ChatRoomResponse.collectAsState().value
    when(chatRoomResponse){
        is Result.Success -> {
            Log.d("ChatRoom", "thanh cong")
            Log.d("ChatRoom", chatRoomResponse.data.data.id)
            roomId = chatRoomResponse.data.data.id
            socketHandler1.joinRoom(chatRoomResponse.data.data.id)
            socketHandler1.onJoinedRoom()
        }
        is Result.Error -> {
            Log.d("ChatRoom", "loi roi")
        }
        else -> Unit
    }
    Log.d("ChatRoom", chatRoomResponse.toString())
    var message by remember { mutableStateOf("") }
    var messages by remember { mutableStateOf(listOf<com.example.h5traveloto_booking.chat.presentation.data.dto.Data>()) }

    Scaffold(
        containerColor = Color.White,
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
                        DrawableId = R.drawable.backbutton, onClick = { navController.popBackStack() }, alt = ""
                    )
                    BoldText2(text = shareDataHotelDetail.getHotelName(), modifier = Modifier.align(Alignment.Center))

                }

            }
        },
        bottomBar = {
            ChatTextField(
                text = message, onValueChange = { message = it },
                onclick = {
                    val newMessage = com.example.h5traveloto_booking.chat.presentation.data.dto.Data(
                        message = message,
                        isFromCustomer = true,
                        updatedAt = "2021-10-10T10:10:10.000Z",

                    );
                    val sendMessage = com.example.h5traveloto_booking.chat.presentation.data.dto.SendMessageDTO(
                        message = message,
                        room_id = roomId,

                    );
                    socketHandler1.sendMessage(sendMessage)
                    socketHandler1.onNewMessage()
                    messages = messages + newMessage;
                    message = ""
                },
                modifier = Modifier,
                focusManager = focusManager // Truyền FocusManager vào đây

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
                        ),
                        reverseLayout = true // Thêm dòng này để đảo ngược thứ tự hiển thị

                    ) {
                        /*item {
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

                            }*/
                        items(messages.reversed()) { message ->
                            ChatRow(chat = message)
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

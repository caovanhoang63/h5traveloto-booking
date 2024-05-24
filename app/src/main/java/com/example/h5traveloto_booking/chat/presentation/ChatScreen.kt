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
import androidx.compose.ui.Alignment.Companion.Center
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
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import websocket.SocketHandler
import websocket.socketHandler1

@Composable
fun ChatScreen(
    navController: NavController,
    viewModel: ChatListViewModel = hiltViewModel(),
    chatRoomviewModel: ChatRoomViewModel = hiltViewModel(),
) {
    var a by remember { mutableStateOf("") }

// Khởi tạo Moshi và adapter
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory()) // Để hỗ trợ các tính năng của Kotlin
        .build()

    val jsonAdapter = moshi.adapter(com.example.h5traveloto_booking.chat.presentation.data.dto.Data::class.java)


    var isCustomerRender by remember {
        mutableStateOf(true)
    }
    var isRender by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(Unit) {
        chatRoomviewModel.getChatRoom(getChat = { roomId ->
            viewModel.getChatList(roomId)
        })

    }
    val focusManager = LocalFocusManager.current


    val chatListResponse = viewModel.ChatListResponse.collectAsState().value
    var roomId by remember {
        mutableStateOf("")
    };


    val chatRoomResponse = chatRoomviewModel.ChatRoomResponse.collectAsState().value


    when (chatRoomResponse) {
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

                    val sendMessage = com.example.h5traveloto_booking.chat.presentation.data.dto.SendMessageDTO(
                        message = message,
                        room_id = roomId,

                        );
                    socketHandler1.sendMessage(sendMessage)
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
                        item {
                            when (chatListResponse) {
                                is Result.Loading -> {
                                    Log.d("ChatList", "dang load")
                                    /*androidx.compose.material.CircularProgressIndicator()*/
                                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
                                        CircularProgressIndicator()
                                    }

                                }

                                is Result.Error -> {
                                    Log.d("ChatList", "loi roi")
                                }

                                is Result.Success -> {
                                    //
                                    socketHandler1.onNewMessage(object : SocketHandler.MessageCallback {
                                        override fun onMessageReceived(message: Any?) {
                                            Log.d("New message", message.toString())
                                            a = message.toString()
                                            Log.d("ChatList a", a)
                                            isRender = true // Đặt isRender = true mỗi khi nhận được một tin nhắn mới

                                        }
                                    })
                                    try {
                                        /*val b =// Chuyển đổi JSON sang đối tượng*/
                                        val b: com.example.h5traveloto_booking.chat.presentation.data.dto.Data? =
                                            jsonAdapter.fromJson(a)
                                        Log.d("ChatList b", b.toString())
                                        if (isRender) {
                                            messages = messages + b!!
                                            isRender = false
                                        }
                                        Log.d("ChatList message", messages.toString())
                                    } catch (e: Exception) {
                                        Log.d("ChatList E", e.message.toString())
                                    }


                                    // Ở đây, thay vì hiển thị chatListResponse.data.data, ta sẽ hiển thị messages
                                    messages.reversed().forEach {
                                        ChatRow(chat = it)
                                    }
                                    val chatlist = chatListResponse.data.data
                                    chatlist.forEachIndexed { index, chatListDTO ->
                                        ChatRow(chat = chatlist[index])
                                    }


                                }

                                else -> Unit

                            }
                        }
                    }
                }
            }
        }
    }
}

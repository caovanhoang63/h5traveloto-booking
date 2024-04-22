package com.example.h5traveloto_booking.main.presentation.homesearch

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backup
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.main.presentation.home.components.HotelTagSmall
import com.example.h5traveloto_booking.main.presentation.homesearch.screens.ChoosePersonScreen
import com.example.h5traveloto_booking.main.presentation.homesearch.screens.SearchLocationScreen
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.theme.*
import com.example.h5traveloto_booking.ui_shared_components.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun HomeSearchScreen(navController: NavController) {

    val scrollState = rememberScrollState()
    var showChosePerson by remember { mutableStateOf(false) }
    var showChoseLocation by remember { mutableStateOf(false) }
    var adult by rememberSaveable { mutableIntStateOf(1) }
    var child by rememberSaveable { mutableIntStateOf(0) }
    var room by rememberSaveable { mutableIntStateOf(1) }
    var location by rememberSaveable { mutableStateOf("Khách sạn gần tôi") }
    var isMyLocation by rememberSaveable { mutableStateOf(true) }

    Scaffold (
        topBar = {
           Row (
               horizontalArrangement = Arrangement.SpaceBetween,
               verticalAlignment = Alignment.CenterVertically,
               modifier = Modifier
                   .background(color = Grey50Color)
                   .fillMaxWidth()
                   .padding(21.dp, 0.dp, 21.dp, 0.dp)
                   .height(64.dp)
           ){
               Button(
                   onClick = {navController.navigate(Screens.HomeScreen.name)},
                   modifier = Modifier
                       .border(2.dp, BorderStroke, RoundedCornerShape(8.dp))
                       .size(40.dp),
                   shape = RoundedCornerShape(8.dp),
                   contentPadding = PaddingValues(0.dp),
                   colors = ButtonDefaults.buttonColors(
                       containerColor = Color(0xFFFFFFFF),
                   ),
               ){
                    Image(
                        painterResource(id = R.drawable.iconback),
                        contentDescription = "Back",
                        modifier = Modifier.size(24.dp)
                    )
               }
               Text(text = "Accommodation", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
               PrimaryIconButton(DrawableId = R.drawable.more, onClick = {}, alt = "Back")
           }
       }

    ){ innerPaddings ->
        Column(
            modifier = Modifier
                .padding(innerPaddings)
                .fillMaxSize()

        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = scrollState)
            ) {
                Box(
                    modifier = Modifier
                        .height(27.dp)
                        .background(color = Grey50Color)
                        .fillMaxWidth()
                )
                Box(
                    modifier = Modifier
                        .height(277.dp)
                        .background(color = Color(0xFFFBFBFB))
                        .fillMaxWidth()
                        .offset( y = (-20).dp),
                    contentAlignment = Alignment.TopCenter
                ){
                    Box(
                        modifier = Modifier
                            .height(260.dp)
                            .fillMaxWidth()
                            .padding(30.dp, 0.dp)
                            .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(color = Color(0xFFFFFFFF)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp, 8.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp),
                            ){
                                TextButtonDialog(
                                    modifier = Modifier
                                        .height(52.dp)
                                        .weight(1f)
                                        .clickable { showChoseLocation = true},
                                    content = location,
                                    icon = {
                                        Image(painterResource(id = R.drawable.location1),
                                            contentDescription = "",
                                            modifier = Modifier.size(20.dp))
                                    },
                                )
                                if(showChoseLocation){
                                    SearchLocationScreen(
                                        onDismiss = { showChoseLocation = false },
                                        onComplete = { locations ->
                                            location = locations
                                            showChoseLocation = false
                                        }
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = {
                                        isMyLocation = true
                                        location = "Khách sạn gần tôi"
                                    },
                                    modifier = Modifier.size(40.dp),
                                    contentPadding = PaddingValues(0.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Transparent,
                                    ),
                                )
                                {
                                    Image(
                                        painterResource(id = R.drawable.targeticon),
                                        contentDescription = "",
                                        modifier = Modifier.size(40.dp)
                                    )
                                }
                            }
                            TextButtonDialog(
                                modifier = Modifier
                                    .height(52.dp)
                                    .fillMaxWidth()
                                    .clickable { },
                                content = "Ngày Tháng",
                                icon = {
                                    Image(painterResource(id = R.drawable.calendar),
                                        contentDescription = "",
                                        modifier = Modifier.size(20.dp))
                                },
                            )
                            TextButtonDialog(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp)
                                    .clickable { showChosePerson = true },
                                content = "$adult người lớn, $child trẻ em, $room phòng",
                                icon = {
                                    Image(painterResource(id = R.drawable.useralt),
                                        contentDescription = "",
                                        modifier = Modifier.size(22.dp))
                                },
                            )
                            if(showChosePerson){
                                ChoosePersonScreen(
                                    onDismiss = { showChosePerson = false },
                                    onConfirm = { adults, childs, rooms ->
                                        adult = adults
                                        child = childs
                                        room = rooms
                                        showChosePerson = false
                                    },
                                    adult,child,room
                                )
                            }
                            Spacer(modifier = Modifier.height(24.dp))
                            Button(
                                onClick = {},
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp),
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = OrangeColor
                                )
                            ) {
                                Text(text = "Booking Now",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                Column (modifier = Modifier.padding(16.dp,0.dp)) {
                    Row (modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        BoldText("Đã xem gần đây")
                        ClickableText("See all", {})
                    }
                    YSpacer(10)
                    HotelTagSmall()
                    HotelTagSmall()
                    HotelTagSmall()
                    HotelTagSmall()
                    HotelTagSmall()
                    HotelTagSmall()
                    HotelTagSmall()
                }
            }
        }
    }
}


package com.example.h5traveloto_booking.main.presentation.homesearch

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backup
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.theme.*
import com.example.h5traveloto_booking.ui_shared_components.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun HomeSearchScreen(navController: NavController) {

    val scrollState = rememberScrollState()

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
                            .height(280.dp)
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
                                    .fillMaxWidth(),
                            ){
                                TextFieldIconLeading(
                                    value = "",
                                    onValueChange = {},
                                    placeholder = "Khách sạn gần",
                                    leadingIcon = {
                                        Image(painterResource(id = R.drawable.location1),
                                            contentDescription = "",
                                            modifier = Modifier.size(28.dp))
                                    },
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth()
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = {},
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
                            TextFieldIconLeading(
                                value = "",
                                onValueChange = {},
                                placeholder = "Ngày tháng",
                                leadingIcon = {
                                    Image(painterResource(id = R.drawable.calendar),
                                        contentDescription = "",
                                        modifier = Modifier.size(24.dp))
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                            TextFieldIconLeading(
                                value = "",
                                onValueChange = {},
                                placeholder = "1 người lớn,0 trẻ em, 1 phòng",
                                leadingIcon = {
                                    Image(painterResource(id = R.drawable.useralt),
                                        contentDescription = "",
                                        modifier = Modifier.size(28.dp))
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                            Button(
                                onClick = {},
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFFF5E1F)
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
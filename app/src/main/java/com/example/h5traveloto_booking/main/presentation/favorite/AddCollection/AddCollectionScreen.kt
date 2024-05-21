package com.example.h5traveloto_booking.main.presentation.favorite.AddCollection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.account.personal_information.UpdateInformation.translateGendertoEnglish
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.theme.ScreenBackGround
import com.example.h5traveloto_booking.ui_shared_components.BoldText
import com.example.h5traveloto_booking.ui_shared_components.PrimaryIconButton
import com.example.h5traveloto_booking.ui_shared_components.XSpacer
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton
import com.example.h5traveloto_booking.util.ui_shared_components.TextBoxSingle

@Composable
fun AddCollectionScreen(navController: NavController)
{
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBackGround),
        topBar = {
            Row (
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                /*IconButton(onClick = {
                    navController.navigate(Screens.MainScreen.name)
                },
                    modifier = Modifier
                     *//*   .padding(16.dp)
                        .wrapContentSize(),*//*
                ) {
                    Icon(
                        modifier = Modifier.size(48.dp),
                        painter = painterResource(id = R.drawable.backarrow48),
                        contentDescription = "Back",
                    )
                }*/
                Box(modifier = Modifier.fillMaxWidth()) {
                    PrimaryIconButton(R.drawable.backarrow48, onClick = {navController.navigateUp() /*navController.popBackStack*/},"", modifier = Modifier )
                    XSpacer(60)
                    Text(
                        fontSize = 16.sp,
                        fontWeight =  FontWeight.Bold,
                        text = "Tạo bộ sưu tập",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                    //  fontWeight = FontWeight.Bold,
                    // fontSize = 20.sp)

            }
        },bottomBar = { Row (
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ){
            PrimaryButton(onClick = {
                navController.navigate(Screens.AddImageInCollectionScreen.name)},
                modifier = Modifier
                    .fillMaxWidth()
                    //.padding(vertical = 20.dp, horizontal = 20.dp)
                ,text = "Tiếp theo",
            )
        }
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            )
            {
                var CollectionName by rememberSaveable { mutableStateOf("") }

                TextBoxSingle(label = "Tên bộ sưu tập", value = CollectionName, onValueChange = { CollectionName = it },modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 20.dp)
                    .fillMaxWidth(), placeholder = "")
            }
        }
    )
}

@Composable
fun AddImageInCollectionScreen(navController: NavController)
{
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBackGround),
        topBar = {
            Row (
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                /*IconButton(onClick = {
                    navController.navigate(Screens.MainScreen.name)
                },
                    modifier = Modifier
                     *//*   .padding(16.dp)
                        .wrapContentSize(),*//*
                ) {
                    Icon(
                        modifier = Modifier.size(48.dp),
                        painter = painterResource(id = R.drawable.backarrow48),
                        contentDescription = "Back",
                    )
                }*/
                Box(modifier = Modifier.fillMaxWidth()) {
                    PrimaryIconButton(R.drawable.backarrow48, onClick = {navController.navigateUp() /*navController.popBackStack*/},"", modifier = Modifier )
                    XSpacer(60)
                    Text(
                        fontSize = 16.sp,
                        fontWeight =  FontWeight.Bold,
                        text = "Thêm ảnh",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                //  fontWeight = FontWeight.Bold,
                // fontSize = 20.sp)

            }
        },bottomBar = { Row (
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ){
            PrimaryButton(onClick = {
                navController.navigate(Screens.AddHotelInCollectionScreen.name)},
                modifier = Modifier
                    .fillMaxWidth()
                //.padding(vertical = 20.dp, horizontal = 20.dp)
                ,text = "Tiếp theo",
            )
        }
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            )
            {
                var CollectionName by rememberSaveable { mutableStateOf("") }

                TextBoxSingle(label = "Tên bộ sưu tập", value = CollectionName, onValueChange = { CollectionName = it },modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 20.dp)
                    .fillMaxWidth(), placeholder = "")
            }
        }
    )
}
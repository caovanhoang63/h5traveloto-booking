package com.example.h5traveloto_booking.account.personal_information

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PermContactCalendar
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.main.presentation.BottomNavigationItem
import com.example.h5traveloto_booking.main.presentation.history.HistoryScreen
import com.example.h5traveloto_booking.main.presentation.home.HomeScreen
import com.example.h5traveloto_booking.main.presentation.schedule.ScheduleScreen
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.theme.ScreenBackGround
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.example.h5traveloto_booking.account.AccountItem
import com.example.h5traveloto_booking.ui_shared_components.*
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton
import com.example.h5traveloto_booking.util.ui_shared_components.TextBox


@OptIn(ExperimentalMaterial3Api::class,ExperimentalComposeUiApi::class)
@Composable
fun PersonalInformationScreen(navController: NavController) {
    var name by rememberSaveable() { mutableStateOf("Hoàng Huy")}
    var email by rememberSaveable() { mutableStateOf("22520533@gm.uit.edu.vn")}
    var phone by rememberSaveable() { mutableStateOf("0xxxxxxx")}

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBackGround),
        topBar = {
            Row (Modifier.padding(16.dp).fillMaxWidth(),
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
                PrimaryIconButton(R.drawable.backarrow48, onClick = {navController.navigateUp()},"", modifier = Modifier )
                XSpacer(60)
                BoldText(text = "Personal Information",
                  //  fontWeight = FontWeight.Bold,
                   // fontSize = 20.sp)
                )
            }
        },
        content = {
            innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
            )
            {
                PersonalData()
                DeleteAccount()
            }
        }
    )
}
@Composable
fun PersonalData(){
    YSpacer(20)
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,) {
        Image(
            painter = painterResource(R.drawable.onlylogo),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(0.1.dp, MaterialTheme.colorScheme.secondary, CircleShape),
        )
    }
    YSpacer(30)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        GreyText(
            text = "Personal Data",
            modifier = Modifier
               //.padding(start = 15.dp),
            //fontWeight = FontWeight.SemiBold,
            //color = colorResource(id = R.color.third_font)
        )
        ClickableText(text = "Update", onClick = {})
    }
    Card(
        modifier = Modifier
            .padding(10.dp)
            .wrapContentWidth(),
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(Color.White)
    )
    {
        Column(
            modifier = Modifier
                //.fillMaxSize()
                //.padding(10.dp)
                .wrapContentSize(),
        ) {

            PersonalItem("Full Name",false,"Hoàng Huy")
            PersonalItem("Gender",false,"Male")
            PersonalItem("Birthdate",false,"20/12/19")
            PersonalItem("Phone",false,"0xxxxxxx")
            PersonalItem("Email",false,"22520533@gm.uit.edu.vn")
            PersonalItem("City of Residence",true)




        }
    }
}
@Composable
fun PersonalItem(
    label: String,
    isLastChild: Boolean,
    value: String="Not Set",
){
    Column (modifier = Modifier
        .wrapContentSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            PrimaryText(label)
            GreyText(value)
        }
        if(!isLastChild) {

            Canvas(modifier = Modifier.fillMaxWidth().height(2.dp)) {
                drawLine(
                    color = Color.LightGray,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 1.dp.toPx(),
                    cap = StrokeCap.Butt,
                )
            }
        }
    }
}

@Composable
fun DeleteAccount(){
    Card(
        modifier = Modifier
            .padding(10.dp)
            .wrapContentWidth(),
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(Color.White)
    )
    {
        DeleteItem(title = "Delete Account",{},null,true)
    }
}
@Composable
fun DeleteItem(
    title: String,
    onClick: () -> Unit,
    description: String?,
    isLastChild: Boolean,
) {
    Column (modifier = Modifier
        .wrapContentSize()
        .clickable {
            onClick()
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            PrimaryText(title)
            Image(
                painter = painterResource(id = R.drawable.arrowright48),
                contentDescription = "Next",
                modifier = Modifier
                    .size(25.dp)
            )
        }
        if (!description.isNullOrEmpty()) {
            GreyText(
                text = description,
                //    fontWeight = FontWeight.Light,
                //    color = colorResource(id = R.color.third_font),
                //   overflow = TextOverflow.Ellipsis,
                //    maxLines = 1,
                modifier = Modifier
                    .padding(start = 27.dp, bottom = 8.dp),
            )
        }
        if(!isLastChild) {

            Canvas(modifier = Modifier.fillMaxWidth().height(2.dp)) {
                drawLine(
                    color = Color.LightGray,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 1.dp.toPx(),
                    cap = StrokeCap.Butt,
                )
            }
        }
    }
}

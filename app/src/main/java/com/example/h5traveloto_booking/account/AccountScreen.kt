package com.example.h5traveloto_booking.account

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
import androidx.navigation.NavHostController
import com.example.h5traveloto_booking.ui_shared_components.*
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AccountScreen(navController: NavController) {


    Scaffold(
        modifier = Modifier
            .background(ScreenBackGround)
            .fillMaxSize(),
        topBar = {
            Row (Modifier.padding(10.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,) {
                BoldText(text = "Account",
                  //  fontWeight = FontWeight.Bold,
                   // fontSize = 20.sp)
                )
            }
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier.padding(innerPadding)
            )
            {
                item {
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(1.dp),
                        colors = CardDefaults.cardColors(Color.White)
                    )
                    {
                        InformationAccount(test)
                    }
                }
                item {
                    ManageProfile(navController)
                }
                item{
                    ManagePoint()
                }
                item {
                    AppSetting()
                }
                item{
                    SupportAndInformation()
                }
                item{
                    PrimaryButton(onClick = {},"Sign Out",modifier = Modifier.fillMaxWidth().padding(16.dp))
                }
            }
        }
    )

}

@Composable
fun AccountItem(
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
            BoldText14(title)
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
                    .padding(start = 20.dp, bottom = 8.dp),
            )
        }

        //dung divider nhanh hon
        Divider(thickness = 0.8.dp, color = Color.LightGray)

        if(!isLastChild) {
            /*Canvas(modifier = Modifier.fillMaxWidth().height(2.dp)) {
                drawLine(
                    color = Color.LightGray,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 1.dp.toPx(),
                    cap = StrokeCap.Butt,
                )
            }*/
        }
    }
}

val test = Account("Hoang Huy","22520533@gm.uit.edu.vn")
data class Account(val name: String, val email: String)
@Composable
fun InformationAccount(acc: Account) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        XSpacer(12)
        Image(
            painter = painterResource(R.drawable.onlylogo),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(0.1.dp, MaterialTheme.colorScheme.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Column {
            PrimaryText(text = acc.name,
                //color = MaterialTheme.colorScheme.primary,
               // style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(start = 4.dp,top = 8.dp,)
            )
            Spacer(modifier = Modifier.height(5.dp))
            GreyText(text = acc.email,
                modifier = Modifier.padding(all = 4.dp),
               // color = colorResource(id= R.color.secondary_font),
               // style = MaterialTheme.typography.bodyLarge,)
            )
        }
    }
}
@Composable
fun ManageProfile(navController: NavController){
    Spacer(modifier = Modifier.height(5.dp))
    GreyText(
        text = "Manage Profile",
        modifier = Modifier
            .padding(start = 15.dp),
       // fontWeight = FontWeight.SemiBold,
       // color = colorResource(id = R.color.third_font)
    )

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
                AccountItem(
                    title = "Personal Information",
                    onClick =
                    {
                        Log.d("personal:", "hehe")
                        navController.navigate(Screens.PersonalInformationScreen.name)
                    },
                    null,
                    false,
                )
                AccountItem(
                    title = "Change Password",
                    {},
                    null,
                    false,
                )
                AccountItem(
                    title = "Payment Information",
                    {},
                    "Add a credit card",
                    true,
                )


        }
    }
}

@Composable
fun ManagePoint(){
    Spacer(modifier = Modifier.height(5.dp))
    GreyText(
        text = "Manage Point",
        modifier = Modifier
            .padding(start = 15.dp),
        //fontWeight = FontWeight.SemiBold,
       // color = colorResource(id = R.color.third_font)
    )
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

                AccountItem(
                    title = "Total point",
                    {},
                    null,
                    false,
                )

                AccountItem(
                    title = "My promotions",
                    {},
                    null,
                    false,
                )

                AccountItem(
                    title = "Program benefits",
                    {},
                    null,
                    true,
                )


        }
    }
}

@Composable
fun AppSetting(){
    Spacer(modifier = Modifier.height(5.dp))
    GreyText(
        text = "App setting",
        modifier = Modifier
            .padding(start = 15.dp),
        //fontWeight = FontWeight.SemiBold,
        //color = colorResource(id = R.color.third_font)
    )
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

            AccountItem(
                title = "Stay notification",
                {},
                "Get room ready, check in, check out",
                true,
            )

        }
    }
}
@Composable
fun SupportAndInformation (){
    Spacer(modifier = Modifier.height(5.dp))
    GreyText(
        text = "Support and information",
        modifier = Modifier
            .padding(start = 15.dp),
       // fontWeight = FontWeight.SemiBold,
        //color = colorResource(id = R.color.third_font)
    )
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

                AccountItem(
                    title = "Visit help center",
                    {},
                    null,
                    false,
                )


                AccountItem(
                    title = "Give us feedback",
                    {},
                    null,
                    false,
                )

                AccountItem(
                    title = "Term and conditions",
                    {},
                    null,
                    false,
                )

                AccountItem(
                    title = "Data privacy center",
                    {},
                    null,
                    false,
                )

                AccountItem(
                    title = "About us",
                    {},
                    null,
                    true,
                )

        }
    }
}
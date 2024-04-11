package com.example.h5traveloto_booking.main.presentation.account

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
import com.example.h5traveloto_booking.ui_shared_components.PrimaryIconButton
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AccountScreen(navController: NavController) {
    Spacer(modifier = Modifier.height(10.dp))
    Spacer(modifier = Modifier.height(10.dp))
    Scaffold(
        modifier = Modifier.background(ScreenBackGround),
        topBar = {
            Row (Modifier.padding(24.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,) {
                Text(text = "Account",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
         //       .verticalScroll(rememberScrollState())
        ) {
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(Color.White)
            ){
                InformationAccount(test)

            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Manage Profile",
                modifier = Modifier
                    .padding(start = 15.dp),
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id=R.color.third_font)
                )
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .wrapContentWidth(),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(Color.White)

            )
            {
                LazyColumn(
                    modifier = Modifier
                        //.fillMaxSize()
                        .padding(10.dp),
                ) {
                    item{
                        AccountItem(
                            title = "Personal Information",{},null
                        )
                    }

                    item {
                        AccountItem(
                            title = "Change Password",{},null
                        )
                    }
                    item {
                        AccountItem(
                            title = "Payment Information",{},"Add a credit card"
                        )
                    }

                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Manage Profile",
                modifier = Modifier
                    .padding(start = 15.dp),
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id=R.color.third_font)
            )
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .wrapContentWidth(),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(Color.White)
            )
            {
                LazyColumn(
                    modifier = Modifier
                        //.fillMaxSize()
                        .padding(10.dp),
                ) {
                    item{
                        AccountItem(
                            title = "Total point",{},null
                        )
                    }

                    item {
                        AccountItem(
                            title = "My promotions",{},null
                        )
                    }
                    item {
                        AccountItem(
                            title = "Program benefits",{},"Add a credit card"
                        )
                    }

                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Support and information",
                modifier = Modifier
                    .padding(start = 15.dp),
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id=R.color.third_font)
            )
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .wrapContentWidth(),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(Color.White)
            )
            {
                LazyColumn(
                    modifier = Modifier
                        //.fillMaxSize()
                        .padding(10.dp),
                ) {
                    item{
                        AccountItem(
                            title = "Visit help center",{},null
                        )
                    }

                    item {
                        AccountItem(
                            title = "Give us feedback",{},null
                        )
                    }
                    item {
                        AccountItem(
                            title = "Term and conditions",{},null
                        )
                    }
                    item{
                        AccountItem(
                            title = "Data privacy center",{},null
                        )
                    }
                    item{
                        AccountItem(
                            title = "About us",{},null
                        )
                    }
                }
            }
        }

    }

}

@Composable
fun AccountItem(
    title: String,
    onClick: () -> Unit,
    description: String?
) {
    Column (modifier = Modifier.clickable { onClick() }) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 27.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(title, fontWeight = FontWeight.SemiBold)
            Image(
                painter = painterResource(id = R.drawable.arrowright48),
                contentDescription = "Next",
                modifier = Modifier
                    .size(25.dp)
            )
        }
        if (!description.isNullOrEmpty()) {
            Text(
                text = description,
                fontWeight = FontWeight.Light,
                color = colorResource(id = R.color.third_font),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .padding(start = 27.dp, bottom = 8.dp),
            )
        }
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

val test = Account("Hoang Huy","22520533@gm.uit.edu.vn")
data class Account(val name: String, val email: String)
@Composable
fun InformationAccount(acc: Account) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.onlylogo),
            contentDescription = null,
            modifier = Modifier
                //set image size to 60dp
                .size(60.dp)
                //clip image to be shape circle
                .clip(CircleShape)
                .border(0.1.dp, MaterialTheme.colorScheme.secondary, CircleShape)
        )
        // Add a horizontal space between the image and the column
        Spacer(modifier = Modifier.width(20.dp))
        // We keep track if the message is expanded or not in this
        // variable
        //var isExpanded by remember { mutableStateOf(false) }
        // surfaceColor will be updated gradually from one color to the other
        //val surfaceColor by animateColorAsState(
        //    if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        //)
        // We toggle the isExpanded variable when we click on this Column
        Column() {
            Text(text = acc.name,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 4.dp,top = 8.dp,)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = acc.email,
                modifier = Modifier.padding(all = 4.dp),
                color = colorResource(id= R.color.secondary_font),
                // If the message is expanded, we display all its content
                // otherwise we only display the first line
                //maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                style = MaterialTheme.typography.bodyLarge,)


        }
    }
}
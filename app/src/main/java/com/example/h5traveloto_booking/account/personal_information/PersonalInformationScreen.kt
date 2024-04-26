package com.example.h5traveloto_booking.account.personal_information

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.account.PersonalInformationViewModel
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ProfileDTO
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.theme.PrimaryColor
import com.example.h5traveloto_booking.theme.ScreenBackGround
import com.example.h5traveloto_booking.ui_shared_components.*
import com.example.h5traveloto_booking.util.Result


@OptIn(ExperimentalMaterial3Api::class,ExperimentalComposeUiApi::class)
@Composable
fun PersonalInformationScreen(navController: NavController,
                              viewModel: PersonalInformationViewModel = hiltViewModel()
)
{

    LaunchedEffect(Unit){
        viewModel.getProfile()
    }
    val ProfileDataResponse = viewModel.ProfileDataResponse.collectAsState().value
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
                PrimaryIconButton(R.drawable.backarrow48, onClick = {navController.navigateUp() /*navController.popBackStack*/},"", modifier = Modifier )
                XSpacer(60)
                BoldText(text = "Thông tín cá nhân",
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
                PersonalData(navController = navController,ProfileDataResponse)
                DeleteAccount()
            }
        }
    )
}
@Composable
fun PersonalData(navController: NavController,acc: Result<ProfileDTO>) {
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
            text = "Dữ liệu cá nhân",
            modifier = Modifier
               //.padding(start = 15.dp),
            //fontWeight = FontWeight.SemiBold,
            //color = colorResource(id = R.color.third_font)
        )
        /*Text(
            text = "Update",
            fontSize =16.sp,
            color = PrimaryColor,
            modifier = Modifier.clickable { navController.navigate(Screens.UpdateInformationScreen.name)}
        )*/
        ClickableText("Cập nhật", onClick = {navController.navigate(Screens.UpdateInformationScreen.name) })
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
            when (acc) {
                is Result.Loading -> {
                    Log.d("Home ", "dang load")

                    // Hieu ung load
                }

                is Result.Error -> {
                    Log.d("Home ", "loi roi")
                }

                is Result.Success -> {
                    val Profile = acc.data
                    PersonalItem("Họ tên",false,Profile.data.lastName +" " +Profile.data.firstName)
                    PersonalItem("Giới tính",false,)
                    PersonalItem("Ngày sinh",false,)
                    Log.e("test",Profile.data.phone)
                    PersonalItem("Số điện thoại",false,Profile.data.phone)
                    PersonalItem("Email",false,Profile.data.email)
                    PersonalItem("Thành phố đang ở",true)

                }

                else -> {}
            }
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
            if(value.isNullOrBlank()){
                GreyText("Not set")

            } else {
                GreyText(value)

            }
        }

        //dung divider nhanh hon
        HorizontalDivider(thickness = 0.8.dp, color = Color.LightGray)
   /*     if(!isLastChild) {
            *//*Canvas(modifier = Modifier.fillMaxWidth().height(2.dp)) {
                drawLine(
                    color = Color.LightGray,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 1.dp.toPx(),
                    cap = StrokeCap.Butt,
                )
            }*//*
        }*/
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
        DeleteItem(title = "Xóa tài khoản",{},null,true)
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

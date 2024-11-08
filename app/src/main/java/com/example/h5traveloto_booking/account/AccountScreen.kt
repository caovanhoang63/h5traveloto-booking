package com.example.h5traveloto_booking.account

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.h5traveloto_booking.account.personal_information.TransparentPainter
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ProfileDTO
import com.example.h5traveloto_booking.main.presentation.home.components.HotelTagLarge
import com.example.h5traveloto_booking.payment.openSdk
import com.example.h5traveloto_booking.ui_shared_components.*
import com.example.h5traveloto_booking.util.Result
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton
import createPayment

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AccountScreen(navController: NavController, nav2Controller: NavController,
                  viewModel: AccountViewModel = hiltViewModel()
)
{
    LaunchedEffect(Unit){
        viewModel.getProfile()
    }
    val ProfileDataResponse = viewModel.ProfileDataResponse.collectAsState().value
    when (ProfileDataResponse){
        is Result.Loading -> {
            CircleLoading()
        }
        is Result.Success -> {
            Scaffold(
                modifier = Modifier
                    .background(ScreenBackGround)
                    .fillMaxSize(),
                topBar = {
                    Row (
                        Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,) {
                        BoldText(text = "Tài khoản",
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
                                InformationAccount(ProfileDataResponse.data)
                            }
                        }
                        item {
                            ManageProfile(nav2Controller)
                        }
                        item{
                            ManagePoint()
                        }
                        item {
                            AppSetting()
                        }
                        item{
                            SupportAndInformation(navController)
                        }
                        item{
                            val context = LocalContext.current
                            SignOut(navController = nav2Controller, context = context)
                        }
                    }
                }
            )
        }
        is Result.Error -> {PrimaryButton(onClick = {viewModel.signOut(navController)} ,"Đăng xuất",modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp))}
        else ->{}

    }


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
        HorizontalDivider(thickness = 0.8.dp, color = Color.LightGray)

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

data class Account(val name: String, val email: String)
@Composable
fun InformationAccount(acc: ProfileDTO) {

//                    Log.d("Home Screen", result.data.data.size.toString())
            val Profile = acc.data
            Row(modifier = Modifier
                .padding(all = 8.dp)
                .fillMaxHeight()) {
                XSpacer(12)
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clip(CircleShape)
                        .clickable(onClick = {
                        })
                ){
                    ProfileImageMini(url = acc.data.avatar?.url.toString(), placeholder = TransparentPainter(),
                        error = painterResource(
                            id = R.drawable.onlylogo
                        ))
                }

                /*Image(
                    painter = painterResource(R.drawable.onlylogo),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .size(50.dp)
                        .clip(CircleShape)
                        .border(0.1.dp, MaterialTheme.colorScheme.secondary, CircleShape)
                )*/
                Spacer(modifier = Modifier.width(20.dp))
                Column {
                    PrimaryText(text = Profile.lastName +" "+ Profile.firstName,
                        //color = MaterialTheme.colorScheme.primary,
                        // style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .padding(start = 4.dp,top = 8.dp,)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    GreyText(text = Profile.email,
                        modifier = Modifier.padding(all = 4.dp),
                        // color = colorResource(id= R.color.secondary_font),
                        // style = MaterialTheme.typography.bodyLarge,)
                    )
                }
            }

    /*Row(modifier = Modifier.padding(all = 8.dp)) {
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
            PrimaryText(text = "",
                //color = MaterialTheme.colorScheme.primary,
               // style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(start = 4.dp,top = 8.dp,)
            )
            Spacer(modifier = Modifier.height(5.dp))
            GreyText(text = "",
                modifier = Modifier.padding(all = 4.dp),
               // color = colorResource(id= R.color.secondary_font),
               // style = MaterialTheme.typography.bodyLarge,)
            )
        }
    }*/
}
@Composable
fun ProfileImageMini(url: String, placeholder: Painter, error: Painter) {
    val context = LocalContext.current

    // Sử dụng remember để giữ cho ImageRequest ổn định qua các lần recomposition
    val imageRequest = remember(url) {
        ImageRequest.Builder(context)
            .data(url)
            .crossfade(true)
            .build()
    }

    AsyncImage(
        model = imageRequest,
        placeholder = placeholder,
        error = error,
        contentDescription = null, // Cung cấp mô tả nội dung phù hợp
        modifier = Modifier.size(50.dp)
    )
}
@Composable
fun ManageProfile(navController: NavController){
    Spacer(modifier = Modifier.height(5.dp))
    GreyText(
        text = "Quản lý hồ sơ",
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
                    title = "Thông tin cá nhân",
                    onClick =
                    {
                        navController.navigate(Screens.PersonalInformationScreen.name)
                    },
                    null,
                    false,
                )
                AccountItem(
                    title = "Đổi mật khẩu",
                    onClick = {
                      navController.navigate(Screens.ChangePasswordScreen.name)
                    },
                    null,
                    false,
                )
                AccountItem(
                    title = "Thông tin thanh toán",
                    {},
                    "Thêm credit card",
                    true,
                )


        }
    }
}

@Composable
fun ManagePoint(){
    Spacer(modifier = Modifier.height(5.dp))
    GreyText(
        text = "Quản lý điểm",
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
                    title = "Tổng điểm",
                    {},
                    null,
                    false,
                )

                AccountItem(
                    title = "Mã giảm giá của tôi",
                    {},
                    null,
                    false,
                )

                AccountItem(
                    title = "Lợi ích chương trình",
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
        text = "Cài đặt chương trình",
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
                title = "Quản lý thông báo",
                {},
                "Get room ready, check in, check out",
                true,
            )

        }
    }
}
@Composable
fun SupportAndInformation (navController: NavController){
    Spacer(modifier = Modifier.height(5.dp))
    GreyText(
        text = "Hỗ trợ và thông tin",
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
                    title = "Trung tâm hỗ trợ",
                    {},
                    null,
                    false,
                )


                AccountItem(
                    title = "Liên hệ chúng tôi",
                    {},
                    null,
                    false,
                )

                AccountItem(
                    title = "Điều khoản và điều kiện",
                    {},
                    null,
                    false,
                )

                AccountItem(
                    title = "Chính sách quyền riêng tư",
                    {},
                    null,
                    false,
                )

                AccountItem(
                    title = "About us",
                    {
                        navController.navigate(Screens.AboutUsScreen.name)
                    },
                    null,
                    true,
                )

        }
    }
}
@Composable
fun SignOut(navController: NavController, viewModel: AccountViewModel= hiltViewModel(),context: Context){
        PrimaryButton(onClick = {viewModel.signOut(navController)
          val url = createPayment(id = "1", amount = "100000", navController = navController, context = context)
            //navController.navigate("webview/${Uri.encode(url)}")
           // openSdk(context = context)
        } ,"Đăng xuất",modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        )
}
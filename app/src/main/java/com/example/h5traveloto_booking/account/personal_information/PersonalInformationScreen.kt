package com.example.h5traveloto_booking.account.personal_information

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.createBitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.IntSize
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.createBitmap
import androidx.core.net.toFile
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.account.PersonalInformationViewModel
import com.example.h5traveloto_booking.account.PickImage
import com.example.h5traveloto_booking.account.personal_information.UpdateInformation.translateGenderToVietnamese
import com.example.h5traveloto_booking.account.personal_information.UpdateInformation.translateGendertoEnglish
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.Profile
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.ProfileDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.UpdateProfileResponse
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.theme.PrimaryColor
import com.example.h5traveloto_booking.theme.ScreenBackGround
import com.example.h5traveloto_booking.ui_shared_components.*
import com.example.h5traveloto_booking.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import kotlin.math.log


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
    when(ProfileDataResponse){

        is Result.Loading -> {
            CircleLoading()
        }
        is Result.Success -> {
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
                        PersonalData(navController = navController,ProfileDataResponse.data,viewModel)
                        DeleteAccount()
                    }
                }
            )
        }
        is Result.Error -> {}
        else -> {}
    }

}
@Composable
fun PersonalData(navController: NavController, acc: ProfileDTO, viewModel: PersonalInformationViewModel = hiltViewModel()) {
            val result = remember { mutableStateOf<Uri?>(null) }
            var image = remember { mutableStateOf<String?>(acc.data.avatar?.url) }
            val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                result.value = uri
                Log.d("personal", "uri: $uri")
                image.value = uri.toString()
            }
            var currentImagePainter = remember { mutableStateOf<Painter?>(null) }
            var flag = remember { mutableStateOf(false) }
            Log.d("personal", "avatar url: ${acc.data.avatar?.url}")

            /*if (acc.data.avatar?.url !=null){
                Log.d("personal", "avatar 2 url: ${acc.data.avatar?.url}")
                currentImagePainter.value = rememberAsyncImagePainter(
                    ImageRequest
                        .Builder(LocalContext.current)
                        .data(data = acc.data.avatar.url)
                        .build()
                )
            }*/
            Log.d("personal", "avatar 2 url: check")

            YSpacer(20)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                /*result.value?.let { image ->
                    //Use Coil to display the selected image
                    currentImagePainter.value = rememberAsyncImagePainter(
                        ImageRequest
                            .Builder(LocalContext.current)
                            .data(data = image)
                            .build()
                    )
*/
                   // Log.d("personal", "image: $image")
                    //val file = uriToFile(LocalContext.current.contentResolver,image)
                if (result.value!=null){
                    val image = result.value!!
                    val path = getRealPathFromUri(LocalContext.current.applicationContext, image)
                    if(path != null && !flag.value) {
                        val file = File(path)
                        Log.d("personal", "file: $file")
                        flag.value = true
                        viewModel.uploadProfile(file,"user_avatar")
                        /* when(UploadFile){
                             is Result.Success -> {
                                 viewModel.updateProfile(UploadFile.data.avatar)
                                 Log.d("PersonalInformation", "upload duoc r")
                             }
                             is Result.Error -> {}
                             is Result.Loading -> {}
                             is Result.Idle ->{}
                             else -> {}
                         }*/
                    }
                }

              //  }

                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable(onClick = {
                            launcher.launch("image/*")
                            Log.d("personal", "hehe")
                            flag.value = false
                        })
                ) {
                    ProfileImage(url = image.value.toString(), placeholder = TransparentPainter(),
                        error = painterResource(R.drawable.onlylogo)
                    )
                   /* Image(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(100.dp)
                            .border(0.1.dp, MaterialTheme.colorScheme.secondary, CircleShape)
                            .padding(8.dp)
                            .fillMaxSize(),
                        painter = currentImagePainter.value ?: painterResource(id = R.drawable.onlylogo),
                        contentDescription = null,
                    )*/
                }
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

                    val Profile = acc.data
                    PersonalItem("Họ tên",false, Profile.lastName +" " +Profile.firstName)
                    PersonalItem("Giới tính",false, translateGenderToVietnamese(Profile.gender))
                    PersonalItem("Ngày sinh",false,Profile.dateOfBirth)
                    Log.e("test",Profile.phone)
                    PersonalItem("Số điện thoại",false,Profile.phone)
                    PersonalItem("Email",false,Profile.email)
                    PersonalItem("Thành phố đang ở",true)


                }
            }
}
@Composable
fun PersonalItem(
    label: String,
    isLastChild: Boolean,
    value: String?="Not Set",
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
fun ProfileImage(url: String, placeholder: Painter, error: Painter) {
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
        modifier = Modifier.size(100.dp)
    )
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

            Canvas(modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)) {
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
fun uriToFile(contentResolver: ContentResolver, uri: Uri): File? {
    val filePath = arrayOf(android.provider.MediaStore.Images.ImageColumns.DATA)
    val cursor = contentResolver.query(uri, filePath, null, null, null)
    cursor?.moveToFirst()
    val columnIndex = cursor?.getColumnIndex(filePath[0])
    val path = cursor?.getString(columnIndex!!)
    cursor?.close()
    return if (path != null) File(path) else null
}

fun getRealPathFromUri(context: Context, uri: Uri): String? {
    var realPath: String? = null
    val projection = arrayOf(MediaStore.Images.Media.DATA)
    val cursor = context.contentResolver.query(uri, projection, null, null, null)
    cursor?.use {
        if (it.moveToFirst()) {
            val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            realPath = it.getString(columnIndex)
        }
    }
    return realPath
}
@Composable
fun TransparentPainter(): Painter {
    // Tạo một bitmap trong suốt
    val size = IntSize(1, 1) // Kích thước nhỏ nhất có thể cho bitmap
    val bitmap = createBitmap(size.width, size.height).apply {
        eraseColor(Color.Transparent.toArgb()) // Đặt màu trong suốt
    }

    // Chuyển đổi bitmap thành ImageBitmap và tạo Painter từ nó
    val imageBitmap: ImageBitmap = bitmap.asImageBitmap()
    return BitmapPainter(imageBitmap)
}
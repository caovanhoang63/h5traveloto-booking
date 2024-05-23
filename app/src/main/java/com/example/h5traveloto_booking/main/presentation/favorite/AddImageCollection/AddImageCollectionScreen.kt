package com.example.h5traveloto_booking.main.presentation.favorite.AddImageCollection

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.account.PersonalInformationViewModel
import com.example.h5traveloto_booking.account.personal_information.ProfileImage
import com.example.h5traveloto_booking.account.personal_information.TransparentPainter
import com.example.h5traveloto_booking.account.personal_information.getRealPathFromUri
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.Avatar
import com.example.h5traveloto_booking.main.presentation.favorite.AddCollection.AddCollectionViewModel
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.theme.ScreenBackGround
import com.example.h5traveloto_booking.ui_shared_components.CircleLoading
import com.example.h5traveloto_booking.ui_shared_components.PrimaryIconButton
import com.example.h5traveloto_booking.ui_shared_components.XSpacer
import com.example.h5traveloto_booking.ui_shared_components.YSpacer
import com.example.h5traveloto_booking.util.Result
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton2
import com.example.h5traveloto_booking.util.ui_shared_components.TextBoxSingle
import com.google.gson.Gson
import java.io.File


@Composable
fun AddImageInCollectionScreen(navController: NavController,
                               collectionName:String,
                               viewModel: AddCollectionViewModel = hiltViewModel(),
                               UploadviewModel:PersonalInformationViewModel = hiltViewModel()
                               )
{
    var CollectionImage = remember { mutableStateOf<Avatar?>(null)}
    val CreateResponse = viewModel.CreateCollectionDataResponse.collectAsState().value
    var CollectionId:String = ""
    when (CreateResponse) {
        is Result.Error -> {
        }
        is Result.Idle -> {}
        is Result.Loading -> {
            CircleLoading()
        }
        is Result.Success -> {
            CollectionId = CreateResponse.data.collectionId
            val isNew = true
            val IsNew = Gson().toJson(isNew)
            navController.navigate("${Screens.AddHotelInCollectionScreen.name}/${CollectionId}/${IsNew}")
        }
    }
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
            }
        },bottomBar = { Row (
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ){
            PrimaryButton(
                onClick = {
                    viewModel.createCollection(name = collectionName, image = CollectionImage.value)
                    Log.d("TEST",CollectionId)
                },
                modifier = Modifier
                    .fillMaxWidth(),
                //.padding(vertical = 20.dp, horizontal = 20.dp)
                text = "Tạo bộ sưu tập",
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
                val UploadResponse = UploadviewModel.UploadImageResponse.collectAsState().value
                val result = remember { mutableStateOf<Uri?>(null) }
                var image = remember { mutableStateOf<String?>(null) }
                val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                    result.value = uri
                    Log.d("personal", "uri: $uri")
                    image.value = uri.toString()
                }
                var flag = remember { mutableStateOf(false) }

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
                            UploadviewModel.uploadImage(file,"collection_image")

                        }
                    }
                    when(UploadResponse){
                        is Result.Error -> {
                            Log.d("AddImage5",CollectionImage.value.toString())

                        }
                        is Result.Idle -> {
                            Log.d("AddImage6",CollectionImage.value.toString())
                        }
                        is Result.Loading -> {
                            Log.d("AddImage7",CollectionImage.value.toString())

                        }
                        is Result.Success -> {
                            CollectionImage.value = UploadResponse.data.avatar
                            Log.d("AddImage2",CollectionImage.value.toString())
                        }
                    }
                    //  }

                    Box(
                        modifier = Modifier.fillMaxSize()
                    )
                    {
                        Log.d("AddImage3",CollectionImage.value.toString())
                        AsyncImage(
                            model = CollectionImage.value?.url ?:R.drawable.example,
                            contentDescription = "Hotel Image",
                            modifier = Modifier
                                .size(120.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .align(Alignment.TopCenter),
                            contentScale = ContentScale.Crop
                        )
                        PrimaryButton(onClick = { launcher.launch("image/*")
                            Log.d("personal", "hehe")
                            flag.value = false},
                            text = "Đổi ảnh bộ sưu tập",
                            modifier = Modifier.align(Alignment.Center)
                            )
                    }
                }
            }
        }
    )
}

package com.example.h5traveloto_booking.main.presentation.favorite.AddHotelInCollection


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.account.personal_information.UpdateInformation.translateGendertoEnglish
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.DataX
import com.example.h5traveloto_booking.main.presentation.favorite.AllFavorite.AllFavoriteViewModel
import com.example.h5traveloto_booking.main.presentation.favorite.AllFavorite.HotelItemTag
import com.example.h5traveloto_booking.main.presentation.favorite.HotelTag
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.theme.ScreenBackGround
import com.example.h5traveloto_booking.ui_shared_components.*
import com.example.h5traveloto_booking.util.Result
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton
import com.example.h5traveloto_booking.util.ui_shared_components.TextBoxSingle

@Composable
fun AddHotelInCollectionScreen(navController: NavController,
                               collectionId:String,
                               new:Boolean,
                               AllViewModel : AllFavoriteViewModel = hiltViewModel(),
                               viewModel: AddHotelInCollectionViewModel= hiltViewModel()
)
{
    LaunchedEffect(Unit){
        AllViewModel.getAllSavedHotels()
    }
    val AllSavedHotlesDataResponse = AllViewModel.AllSavedHotelsDataResponse.collectAsState().value
    when(AllSavedHotlesDataResponse) {
        is Result.Error -> {
        }

        Result.Idle -> {
        }

        Result.Loading -> {
            CircleLoading()
        }

        is Result.Success -> {
            var listHotel by remember {
                mutableStateOf((AllSavedHotlesDataResponse.data.data))
            }
            var listHotelChecked by remember {
                mutableStateOf(mutableStateListOf<DataX>())
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
                            PrimaryIconButton(R.drawable.backarrow48, onClick = {

                            },"", modifier = Modifier )
                            XSpacer(60)
                            Text(
                                fontSize = 16.sp,
                                fontWeight =  FontWeight.Bold,
                                text = "Thêm mục",
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
                    PrimaryButton(onClick = {
                        if(listHotelChecked.isNotEmpty()) {
                            listHotelChecked.forEach {it->
                                viewModel.addHotelInCollection(collectionId =collectionId, hotelId = it.id.toString() )
                            }
                        }
                        repeat(if (new) 4 else 1){
                            navController.popBackStack()
                        }
                       // navController.navigate(Screens.FavoriteScreen.name)
                    },
                        modifier = Modifier
                            .fillMaxWidth(),
                        //.padding(vertical = 20.dp, horizontal = 20.dp),
                        text = "Hoàn thành",
                    )
                } },
                content = { innerPadding ->




                    LazyColumn(
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                    {
                        if(listHotel.size==0){
                            item{
                                Image(painter = painterResource(id = R.drawable.nullsaved),
                                    contentDescription ="List is empty",
                                    modifier = Modifier.fillMaxSize())
                            }
                        }
                        items(listHotel){
                                item ->
                            HotelItemTagCheck(
                                hotelName = item.name,
                                rating = 4.5f,
                                reviewCount = item.totalRating,
                                star = item.star,
                                isChecked = false,
                                onCheckedChange = {
                                    if(!listHotelChecked.contains(item)){
                                        listHotelChecked.add(item)
                                    }else listHotelChecked.remove(item)
                                },
                                imagePainter = item.logo.url,

                                )
                        }
                    }
                }
            )
        }
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotelItemTagCheck(
    hotelName: String,
    rating: Float,
    reviewCount: Int,
    star:Int,
    isChecked: Boolean,
    onCheckedChange: () -> Unit,
    imagePainter: String,
) {
    var checked by remember { mutableStateOf(isChecked) }

    Row(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { isChecked ->
                    checked = isChecked
                    onCheckedChange()
            },
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        AsyncImage(
            model = imagePainter,
            contentDescription = "Hotel Image",
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterVertically)
                .weight(0.5f)
        ) {
            Text(
                text = hotelName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                repeat(5) { index ->
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = if (index < star) R.drawable.baseline_star_16 else R.drawable.baseline_star_outline_16
                        ),
                        contentDescription = "Star Rating",
                        tint = Color.Yellow,
                        modifier = Modifier.size(16.dp)
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "%.1f/10".format(rating), color = Color.Blue, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "($reviewCount)", color = Color.Gray, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(4.dp))
            // Text(text = "${price.formatPrice()} VND", color = Color.Red, fontSize = 14.sp)
        }

    }
}
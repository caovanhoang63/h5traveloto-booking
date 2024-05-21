package com.example.h5traveloto_booking.main.presentation.favorite.DetailCollection

import com.example.h5traveloto_booking.main.presentation.favorite.AllFavorite.AllFavoriteViewModel

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.AllInbox
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Android
import androidx.compose.material.icons.sharp.AllInbox
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
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
import com.example.h5traveloto_booking.account.*
import com.example.h5traveloto_booking.account.personal_information.DeleteAccount
import com.example.h5traveloto_booking.account.personal_information.PersonalData
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.DataX
import com.example.h5traveloto_booking.main.presentation.favorite.Bookmark2
import com.example.h5traveloto_booking.main.presentation.favorite.HotelTag
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.theme.ScreenBackGround
import com.example.h5traveloto_booking.ui_shared_components.*
import com.example.h5traveloto_booking.util.Result
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue



@Composable
fun DetailCollectionScreen(navController: NavController,
                           collectionID:String,
                           viewModel: DetailCollectionViewModel = hiltViewModel(),
                           AllViewModel: AllFavoriteViewModel = hiltViewModel()
)
{
    LaunchedEffect(Unit){
        viewModel.getAllHotelsByCollectionId(collectionID)
    }
    val AlldHotlesByCollectionIdDataResponse = viewModel.AllHotelsDataResponse.collectAsState().value
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
                Box(modifier = Modifier.fillMaxWidth()){
                    PrimaryIconButton(R.drawable.backarrow48, onClick = {navController.navigateUp()/* navController.popBackStack*/},"", modifier = Modifier )
                    XSpacer(60)
                    Text(
                        fontSize = 16.sp,
                        fontWeight =  FontWeight.Bold,
                        text = "Tất cả sản phẩm đã lưu",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

            }
        },
        content = {
                innerPadding ->
            when(AlldHotlesByCollectionIdDataResponse){
                is Result.Error -> {
                }
                is Result.Idle -> {
                }
                is Result.Loading -> {
                    CircleLoading()
                }
                is Result.Success -> {
                    var listHotel by remember {
                        mutableStateOf((AlldHotlesByCollectionIdDataResponse.data.data))
                    }
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
                            HotelItemTag(
                                hotelName = item.name,
                                rating = 4.5f,
                                reviewCount = item.totalRating,
                                star = item.star,
                                isFavorite = true,
                                UnSave = {
                                   AllViewModel.unsaveHotel(item.id.toString())
                                },
                                GetOutCollection = {
                                    viewModel.deleteHotel(collectionID, item.id.toString())
                                },
                                imagePainter = item.logo.url,
                                // price = item.price
                            )
                        }
                    }

                }
            }

        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotelItemTag(
    hotelName: String,
    rating: Float,
    reviewCount: Int,
    star:Int,
    isFavorite: Boolean,
    UnSave: () -> Unit,
    GetOutCollection: () -> Unit,
    imagePainter: String,
    // price: Long,
) {
    var favoriteState by remember { mutableStateOf(isFavorite) }
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpened by remember { mutableStateOf(false) }
    if(isSheetOpened){
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { isSheetOpened = false },
            dragHandle = null
        ){
            Column(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()) {
                Text(
                    text = hotelName,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            HorizontalDivider(thickness = 0.8.dp, color = Color.LightGray)
            Column(modifier = Modifier.fillMaxWidth()) {

                Row(
                    modifier = Modifier
                        .clickable {
                            GetOutCollection()
                           // favoriteState = !favoriteState
                            isSheetOpened = false
                        }
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = "Xóa khỏi bộ sưu tập")
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = R.drawable.baseline_add_circle_outline_24
                        ),
                        contentDescription = "Favorite Icon",
                        tint = Color.Blue
                    )
                }
                HorizontalDivider(thickness = 0.8.dp, color = Color.LightGray)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 20.dp)
                        .clickable(onClick = {
                            UnSave()
                          //  favoriteState = !favoriteState
                            isSheetOpened = false
                        }),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = "Xóa khỏi danh sách đã lưu")
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = R.drawable.baseline_playlist_remove_24
                        ),
                        contentDescription = "Favorite Icon",
                        tint = Color.Blue
                    )
                }
                HorizontalDivider(thickness = 0.8.dp, color = Color.LightGray)
                PrimaryButton(onClick = { isSheetOpened = false },
                    text = "Hủy",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp))
                YSpacer(height = 5)
            }
        }
    }

    Row(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
            .fillMaxWidth()
    ) {
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
        Column {
            XSpacer(width = 10)
            IconButton(
                onClick = {
                    //favoriteState = !favoriteState
                    //onFavoriteClick()
                    //if(favoriteState)
                    isSheetOpened = true
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(
                        id = if (favoriteState) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24
                    ),
                    contentDescription = "Favorite Icon",
                    tint = if (favoriteState) Color.Red else Color.Gray
                )
            }
        }

    }
}
fun Long.formatPrice(): String {
    val formatter = java.text.DecimalFormat("#,###")
    return formatter.format(this)
}
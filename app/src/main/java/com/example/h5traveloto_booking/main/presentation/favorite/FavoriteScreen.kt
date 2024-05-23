package com.example.h5traveloto_booking.main.presentation.favorite

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.AllInbox
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.outlined.Android
import androidx.compose.material.icons.sharp.AllInbox
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.account.*
import com.example.h5traveloto_booking.main.presentation.data.dto.Account.Avatar
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.CollectionDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Favorite.Data
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.theme.ScreenBackGround
import com.example.h5traveloto_booking.ui_shared_components.*
import com.example.h5traveloto_booking.util.Result
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.google.gson.Gson
import kotlin.math.absoluteValue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(navController: NavController,
                   viewModel: FavoriteViewModel= hiltViewModel()
                   )
{
    LaunchedEffect(Unit){
        viewModel.getCollectionData()
    }
    val CollectionDataResponse = viewModel.CollectionDataResponse.collectAsState().value
    when (CollectionDataResponse) {
        is Result.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    )
                    {
                            Image(
                                painterResource(id = R.drawable.error404),
                                contentDescription = "Error",
                                modifier = Modifier.fillMaxSize()
                            )
                    }
                }
        is Result.Idle -> {

        }
        is Result.Loading -> {
            CircleLoading()
        }

        is Result.Success -> {
            Scaffold(
                modifier = Modifier
                    .background(ScreenBackGround)
                    .fillMaxSize(),
                topBar = {
                    Row(
                        Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        BoldText(
                            text = "Yêu thích",
                            //  fontWeight = FontWeight.Bold,
                            // fontSize = 20.sp)
                        )
                    }
                },
                content = { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .verticalScroll(rememberScrollState())
                    )
                    {
                        Card(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                                .height(150.dp)
                                .clickable(onClick = { navController.navigate(Screens.AllFavoriteScreen.name) }),

                            elevation = CardDefaults.cardElevation(5.dp),
                            colors = CardDefaults.cardColors(Color.White)
                        )
                        {
                            Row(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth()
                            ) {
                                Column(
                                    Modifier
                                        .weight(0.5f)
                                        .fillMaxHeight(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    FavoriteItem(
                                        title = "Xem tất cả sản phẩm đã lưu",
                                        description = null
                                    )
                                }
                                Column(
                                    Modifier
                                        .weight(0.5f)
                                        .fillMaxHeight()
                                ) {
                                    BookmarkSection()
                                }
                            }

                        }
                        val hotelList = listOf(
                            HotelTag(
                                id = 1,
                                hotelName = "Khách sạn Ánh Dương",
                                rating = 4.5f,
                                reviewCount = 200,
                                star = 4,
                                isFavorite = false,
                                onFavoriteClick = { /* handle favorite click */ },
                                imageRes = "https://pistachiohotel.com/UploadFile/Gallery/Overview/a2.jpg",
                                price = 1200000,
                            ),
                            HotelTag(
                                id = 2,
                                hotelName = "Khách sạn Biển Xanh",
                                rating = 4.2f,
                                reviewCount = 300,
                                star = 5,
                                isFavorite = true,
                                onFavoriteClick = { /* handle favorite click */ },
                                imageRes = "https://pistachiohotel.com/UploadFile/Gallery/Overview/a1.jpg",
                                price = 1440000,
                            ),
                            // Add more HotelTag objects
                            HotelTag(
                                id = 3,
                                hotelName = "Khách sạn Thiên Đường",
                                rating = 4.8f,
                                reviewCount = 450,
                                star = 5,
                                isFavorite = false,
                                onFavoriteClick = { /* handle favorite click */ },
                                imageRes = "https://pistachiohotel.com/UploadFile/Gallery/Overview/a3.jpg",
                                price = 2440000,
                            ),
                            HotelTag(
                                id = 4,
                                hotelName = "Khách sạn Hạ Long",
                                rating = 3.9f,
                                reviewCount = 180,
                                star = 4,
                                isFavorite = true,
                                onFavoriteClick = { /* handle favorite click */ },
                                imageRes = "https://pistachiohotel.com/UploadFile/Gallery/Lobby/a1.jpg",
                                price = 3240000,
                            ),
                            HotelTag(
                                id = 5,
                                hotelName = "Khách sạn Cát Bà",
                                rating = 4.3f,
                                reviewCount = 320,
                                star = 4,
                                isFavorite = false,
                                onFavoriteClick = { /* handle favorite click */ },
                                imageRes = "https://pistachiohotel.com/UploadFile/Gallery/Rooms/Deluxe/Deluxe-Double-1.jpg",
                                price = 4240000,
                            ),
                            HotelTag(
                                id = 6,
                                hotelName = "Khách sạn Sapa",
                                rating = 4.6f,
                                reviewCount = 290,
                                star = 5,
                                isFavorite = true,
                                onFavoriteClick = { /* handle favorite click */ },
                                imageRes = "https://pistachiohotel.com/UploadFile/Gallery/Rooms/Deluxe/Deluxe-Twin-1.jpg",
                                price = 4350000,
                            ),
                            HotelTag(
                                id = 7,
                                hotelName = "Khách sạn Đà Lạt",
                                rating = 4.1f,
                                reviewCount = 210,
                                star = 4,
                                isFavorite = false,
                                onFavoriteClick = { /* handle favorite click */ },
                                imageRes = "https://pistachiohotel.com/UploadFile/Gallery/Rooms/Superior/Superior-Twin-2.jpg",
                                price = 1230000,
                            ),
                            HotelTag(
                                id = 8,
                                hotelName = "Khách sạn Bình Dương",
                                rating = 4.4f,
                                reviewCount = 350,
                                star = 4,
                                isFavorite = true,
                                onFavoriteClick = { /* handle favorite click */ },
                                imageRes = "https://pistachiohotel.com/UploadFile/Gallery/Restaurant/a2.jpg",
                                price = 1940000,
                            ),
                            HotelTag(
                                id = 9,
                                hotelName = "Khách sạn Hội An",
                                rating = 4.7f,
                                reviewCount = 420,
                                star = 5,
                                isFavorite = false,
                                onFavoriteClick = { /* handle favorite click */ },
                                imageRes = "https://pistachiohotel.com/UploadFile/Gallery/Restaurant/a11.jpg",
                                price = 1540000,
                            )
                        )
                        Album(CollectionDataResponse.data, navController = navController)
                        HotelSeen(hotelList)
                    }
                }
            )
        }
    }
}

@Composable
fun Album(data : CollectionDTO,navController: NavController){
    Row( modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
        horizontalArrangement = Arrangement.Start) {
        Icon(imageVector = Icons.Sharp.AllInbox, contentDescription =null,
            tint =Color.Blue  )
        XSpacer(width = 3)
        Row( modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            BoldText20(text = "Bộ Sưu Tập")
            ClickableText("Xem tất cả", onClick = {})
        }
    }
    var listData = data.data
    var bookmarks: List<Bookmark2> = listData.map{
        data->
        Bookmark2(
            id=data.id,
            name = data.name,
            imageRes = data.cover?.url
        )
    }
    var listCollection by remember {
        mutableStateOf(mutableListOf<Bookmark2>())
    }
    listCollection = bookmarks.toMutableList()
    val EX = Bookmark2(id = (bookmarks.size +1).toString(),null,R.drawable.collection)
    val checkAdd = EX.id
    Log.d("HEHEHE", EX.id.toString())
    listCollection.add(0,EX)

    Row( modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp),
        ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            elevation = CardDefaults.cardElevation(5.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            //GridColumn(items = listCollection, navController = navController, checkAdd = checkAdd.toString())
            LazyVerticalGrid(
           columns = GridCells.Fixed(2),
           contentPadding = PaddingValues(5.dp),
           modifier = Modifier
               .clickable(onClick = {})
               .padding(10.dp)
               .height(if(listCollection.size <=2) 170.dp else 310.dp),
           verticalArrangement = Arrangement.spacedBy(16.dp),
           horizontalArrangement = Arrangement.spacedBy(10.dp),
           userScrollEnabled = false,
       ){
           items(listCollection){
                   item -> BookmarkItem2(item,listData, navController = navController, modifier = Modifier,checkAdd.toString())
           }
       }
        }
    }
}
@Composable
fun GridColumn(
    items: List<Bookmark2>,
    modifier: Modifier = Modifier,
    columns: Int = 2,
    navController:NavController,
    checkAdd:String
) {
    Column(
        modifier = modifier
            .padding(10.dp),
           // .height(270.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        items.chunked(columns).forEach { rowItems ->
           // RowItem(rowItems, navController = navController,checkAdd)
        }
    }
}

/*@Composable
private fun RowItem(rowItems: List<Bookmark2>,navController: NavController,checkAdd: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        rowItems.forEach { item ->
            BookmarkItem2(item,rowItems, navController = navController, modifier = Modifier,checkAdd)
        }
    }
}*/
@Composable
fun HotelSeen(
    hotelList: List<HotelTag>,
    ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            imageVector = Icons.Sharp.Home, contentDescription = null,
            tint = Color.Blue
        )
        XSpacer(width = 3)
            BoldText20(text = "Khách sạn đã xem gần đây")
    }
    ImageCarousel(images =hotelList )
}
@Composable
fun FavoriteItem(
    title: String,
    description: String?,
) {
    Column (modifier = Modifier
        .wrapContentSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
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
                    .padding(start = 20.dp, bottom = 8.dp),
            )
        }

        //dung divider nhanh hon
        //HorizontalDivider(thickness = 0.8.dp, color = Color.LightGray)

    }
}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageCarousel(
    images: List<HotelTag>,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
    ) {
        HorizontalPager(
            count = images.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 48.dp),
            itemSpacing = 16.dp
        ) { page ->
            Column {
                HotelItemTag(
                    hotelName = images[page].hotelName,
                    rating = images[page].rating,
                    reviewCount = images[page].reviewCount,
                    isFavorite =images[page].isFavorite,
                    onFavoriteClick = { /*TODO*/ },
                    imagePainter =images[page].imageRes,
                    star = images[page].star,
                    price = images[page].price
                )
            }

        }
    }
}

fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return start + (stop - start) * fraction
}
@Composable
fun BookmarkSection(
    modifier: Modifier = Modifier,
    bookmarks: List<Bookmark> = listOf(
        Bookmark(id = 1, name = "Bookmark 1", imageRes = R.drawable.bookmark1),
        Bookmark(id = 2, name = "Bookmark 2", imageRes = R.drawable.bookmark2),
        Bookmark(id = 3, name = "Bookmark 3", imageRes = R.drawable.bookmark3),
        // Thêm các bookmark khác nếu cần
    )
) {
    Row(){
        BookmarkItem1(bookmark = bookmarks[0])
        XSpacer(width = 3)
        Column() {
            BookmarkItem(bookmark = bookmarks[1])
            YSpacer(height = 3)
            BookmarkItem(bookmark = bookmarks[2])
        }
    }
}

@Composable
fun BookmarkItem(
    bookmark: Bookmark
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = bookmark.imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
    }
}
@Composable
fun BookmarkItem1(
    bookmark: Bookmark
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = bookmark.imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .width(80.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.FillHeight
        )
    }
}
@Composable
fun BookmarkItem2(
    bookmark: Bookmark2,
    Items:List<Data>,
    navController: NavController,
    modifier: Modifier,
    checkAdd: String
) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            /*modifier = Modifier.clickable(indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = {}
            )*/
            modifier = modifier
                .clickable(indication=null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {
                        if(bookmark.id ==checkAdd){
                            navController.navigate(Screens.AddCollectionScreen.name)
                        }else{
                            val data = Items.find { it.id == bookmark.id }
                            val Collection = Gson().toJson(data)
                            //val Collection = data?.cover?.url
                            navController.navigate("detailcollection/${bookmark.id}/${bookmark.name}/${Uri.encode(Collection)}")
                        }
                    }
                )
        ) {
            AsyncImage(
                model = bookmark.imageRes ?: R.drawable.example,
                contentDescription = "Hotel Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    //.width(170.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Text(
                text = bookmark.name?:"",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
}
data class Bookmark(
    val id: Int,
    val name: String,
    val imageRes: Int
)
data class Bookmark2(
    val id: String?,
    val name: String?,
    val imageRes: Any?
)
data class HotelTag(
    val id: Int,
    val hotelName: String,
    val rating: Float,
    val reviewCount: Int,
    val isFavorite: Boolean,
    val onFavoriteClick: () -> Unit,
    val imageRes: String,
    val star:Int,
    val price: Long
)
@Composable
fun HotelItemTag(
    hotelName: String,
    rating: Float,
    reviewCount: Int,
    star:Int,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    imagePainter: String,
    price: Long,

) {
    var favoriteState by remember { mutableStateOf(isFavorite) }
    Card(modifier = Modifier.wrapContentSize()
        ,
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(Color.White)){
        Column(
            modifier = Modifier
                .padding(8.dp)
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Box(modifier = Modifier.height(180.dp)) {
                AsyncImage(
                    model = imagePainter,
                    contentDescription = "Hotel Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.FillBounds
                )
                IconButton(
                    onClick = {
                        favoriteState = !favoriteState
                        onFavoriteClick()
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = if (favoriteState) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24
                        ),
                        contentDescription = "Favorite Icon",
                        tint = if (favoriteState) Color.Red else Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = hotelName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            // Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                repeat(5) { index ->
                    Icon(
                        imageVector = ImageVector.vectorResource(
                            id = if (index < star.toInt()) R.drawable.baseline_star_16
                            else R.drawable.baseline_star_outline_16
                        ),
                        contentDescription = "Star Rating",
                        tint = if (index < star.toInt()) Color.Yellow else Color.Transparent,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically){
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "%.1f/10".format(rating), color = Color.Blue, fontSize = 14.sp,fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "($reviewCount)", color = Color.Gray, fontSize = 14.sp)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "${price.formatPrice()} VND",
                    color = Color.Red,
                    fontSize = 14.sp
                ) // Sử dụng hàm mở rộng để định dạng giá tiền
            }
        }
    }

}
fun Long.formatPrice(): String {
    val formatter = java.text.DecimalFormat("#,###")
    return formatter.format(this)
}
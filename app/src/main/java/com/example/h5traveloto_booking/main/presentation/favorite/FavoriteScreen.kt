package com.example.h5traveloto_booking.main.presentation.favorite

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.AllInbox
import androidx.compose.material.icons.sharp.AllInbox
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.account.*
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.theme.ScreenBackGround
import com.example.h5traveloto_booking.ui_shared_components.*
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(navController: NavController) {

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
                BoldText(text = "Yêu thích",
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
                            .clickable(onClick = {}),

                        elevation = CardDefaults.cardElevation(5.dp),
                        colors = CardDefaults.cardColors(Color.White)
                    )
                    {
                        Row (modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()) {
                            Column (
                                Modifier
                                    .weight(0.5f)
                                    .fillMaxHeight(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally) {
                                FavoriteItem(title = "Xem tất cả sản phẩm đã lưu",
                                    description = null)
                            }
                            Column (
                                Modifier
                                    .weight(0.5f)
                                    .fillMaxHeight()
                            ){
                                BookmarkSection()
                            }
                        }

                    }
                    Album()
                    HotelSeen()
                Album()
            }
        }
    )
}

@Composable
fun Album(){
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
    var bookmarks2: List<Bookmark> = listOf(
        Bookmark(id = 1, name = "Bookmark 1", imageRes = R.drawable.bookmark1),
        Bookmark(id = 2, name = "Bookmark 2", imageRes = R.drawable.bookmark2),
        Bookmark(id = 3, name = "Bookmark 3", imageRes = R.drawable.bookmark3),
        Bookmark(id = 4, name = "Bookmark 4", imageRes = R.drawable.bookmark3),
       // Bookmark(id = 5, name = "Bookmark 5", imageRes = R.drawable.bookmark3),
    )
    /*LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(5.dp),
        modifier = Modifier
            .clickable(onClick = {})
            .padding(10.dp)
            .height(270.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        userScrollEnabled = false
    ){
        items(bookmarks2){
                item -> BookmarkItem2(bookmark = item)
        }
    }*/
    GridColumn(items = bookmarks2)
}
@Composable
fun GridColumn(
    items: List<Bookmark>,
    modifier: Modifier = Modifier,
    columns: Int = 2,
    onClick: (Any) -> Unit = {},
) {
    Column(
        modifier = modifier
            .clickable(onClick = {})
            .padding(10.dp),
           // .height(270.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items.chunked(columns).forEach { rowItems ->
            RowItem(rowItems, onClick)
        }
    }
}

@Composable
private fun RowItem(rowItems: List<Bookmark>, onClick: (Any) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        rowItems.forEach { item ->
            BookmarkItem2(item)
        }
    }
}
@Composable
fun HotelSeen() {
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
    images: List<Int>,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        HorizontalPager(
            count = images.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 48.dp),
            itemSpacing = 16.dp
        ) { page ->
            Image(
                painter = painterResource(id = images[page]),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }

                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
                    .clip(RoundedCornerShape(16.dp))
            )
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
    bookmark: Bookmark
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        /*modifier = Modifier.clickable(indication = null,
            interactionSource = remember { MutableInteractionSource() },
            onClick = {}
        )*/
        modifier = Modifier.clickable(onClick = {})
    ) {
        Image(
            painter = painterResource(id = bookmark.imageRes),
            contentDescription = null,
            modifier = Modifier
                .height(100.dp)
                .width(175.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Text(
            text = bookmark.name,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
data class Bookmark(
    val id: Int,
    val name: String,
    val imageRes: Int
)
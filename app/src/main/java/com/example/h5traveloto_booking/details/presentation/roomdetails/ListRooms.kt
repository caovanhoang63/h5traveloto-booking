import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.account.ListHotelsViewModel
import com.example.h5traveloto_booking.details.presentation.hoteldetails.components.HotelDetailCard
import com.example.h5traveloto_booking.details.presentation.roomdetails.ListRoomViewModel
import com.example.h5traveloto_booking.details.presentation.roomdetails.RoomDetailsScreenViewModel
import com.example.h5traveloto_booking.details.presentation.roomdetails.components.NotFoundRoomTypes
import com.example.h5traveloto_booking.details.presentation.roomdetails.components.RoomDetailCard
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchRoomType.SearchRoomTypeDTO
import com.example.h5traveloto_booking.share.shareDataHotelDetail
import com.example.h5traveloto_booking.theme.Grey50Color
import com.example.h5traveloto_booking.ui_shared_components.PrimaryIconButton
import com.example.h5traveloto_booking.ui_shared_components.XSpacer
import com.example.h5traveloto_booking.ui_shared_components.YSpacer
import com.example.h5traveloto_booking.util.Result
import kotlin.math.log

@Composable
fun ListRooms(
    navController: NavController,
    viewModel: ListRoomViewModel = hiltViewModel(),

) {
    LaunchedEffect(Unit) {
        viewModel.getListRooms()

    }
    val listRoomResponse = viewModel.ListRoomsResponse.collectAsState().value
    Log.d("ListRooms View", listRoomResponse.toString())



    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Grey50Color)
            ) {
                Column(modifier = Modifier.padding(top = 21.dp, bottom = 21.dp, start = 27.dp, end = 27.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        PrimaryIconButton(
                            DrawableId = R.drawable.backbutton,
                            onClick = { navController.popBackStack() },
                            alt = "",
                        )

                        Column { //Current location
                            Text(
                                text = shareDataHotelDetail.getHotelName(),
                                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "${shareDataHotelDetail.getStartDate()}, ${shareDataHotelDetail.getPersonOption().first} đêm, ${shareDataHotelDetail.getPersonOption().third} phòng",
                                style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 12.sp)
                            )
                        }
                        PrimaryIconButton(DrawableId = R.drawable.search, onClick = {}, alt = "")
                    }
                }
            }

        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 54.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                item {
                    when (listRoomResponse) {
                        is Result.Loading -> {
                            Log.d("ListRooms", "dang load")

                            // Hieu ung load
                            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                CircularProgressIndicator()

                            }
                        }

                        is Result.Error -> {
                            Log.d("List Hotel ", "loi roi")
                            NotFoundRoomTypes()
                        }

                        is Result.Success -> {
                            Log.d("ListRooms", "thanh cong")
                            val rooms = listRoomResponse.data.data
                            rooms.forEachIndexed { index, hotelDTO ->
                                RoomDetailCard(roomDTO = rooms[index], navController = navController)

                                if (index < rooms.lastIndex) {
                                    Spacer(modifier = Modifier.height(15.dp))
                                }
                            }
                        }

                        else -> Unit
                    }
                }
            }
        }

    }
}
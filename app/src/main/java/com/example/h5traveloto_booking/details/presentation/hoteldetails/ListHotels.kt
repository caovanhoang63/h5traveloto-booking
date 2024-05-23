package com.example.h5traveloto_booking.details.presentation.hoteldetails

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.account.ListHotelsViewModel
import com.example.h5traveloto_booking.details.presentation.data.`class`.HotelClass
import com.example.h5traveloto_booking.details.presentation.hoteldetails.components.HotelDetailCard2
import com.example.h5traveloto_booking.details.presentation.hoteldetails.components.StarFilter
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchHotel.Data
import com.example.h5traveloto_booking.main.presentation.map.LocationProvider
import com.example.h5traveloto_booking.share.shareDataHotelDetail
import com.example.h5traveloto_booking.share.shareHotelDataViewModel
import com.example.h5traveloto_booking.theme.Grey50Color
import com.example.h5traveloto_booking.ui_shared_components.*
import com.example.h5traveloto_booking.util.Result
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListHotels(
    navController: NavController,
    viewModel: ListHotelsViewModel = hiltViewModel()

) {
    val radioOption = listOf("Giá thấp đến cao", "Giá cao đến thấp", "Xếp hạng cao đến thấp", "Xếp hạng thấp đến cao")
    var isSortSheetOpened by remember { mutableStateOf(false) }
    var isSortSelected by remember { mutableStateOf("Giá thấp đến cao") }
    var isFilterSheetOpened by remember { mutableStateOf(false) }

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOption[0]) }
    val (starSelected, onStarSelected) = remember { mutableStateOf(mutableListOf<Int>()) }
    Log.d("Star selected", starSelected.toString())
    val sortSheetState = rememberModalBottomSheetState()
    val filterSheetState = rememberModalBottomSheetState()


    val context = LocalContext.current
    val permissions = arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )
    val launchMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.all { it.value }) {
            if(LocationProvider.isLocationEnabled(context)){
                viewModel.startLocationUpdates()
            }
            else{
                LocationProvider.createLocationRequest(context)
            }
        } else {
            Log.d("LocationProvider", "Permissions denied")
        }
    }

    val enableGpsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            viewModel.startLocationUpdates()
        } else {
            Log.d("LocationProvider", "GPS enabling denied")
        }
    }

    LaunchedEffect(Unit) {
        LocationProvider.setGpsLauncher(enableGpsLauncher)
        viewModel.initLocationProvider(context)
        if (shareHotelDataViewModel.checkExistedData() && !shareHotelDataViewModel.getOnClickBooking()) {
            viewModel.setStateHotelSearchSuccess(shareHotelDataViewModel.getListHotel()!!)
        } else {
            if (!shareHotelDataViewModel.isCurrentLocation()) {
                viewModel.getHotelSearch()
            }
        }
    }

    val listHotelSearch = viewModel.ListHotelSearch.collectAsState().value

    if (isFilterSheetOpened) {
        ModalBottomSheet(
            onDismissRequest = { isFilterSheetOpened = false },
            sheetState = filterSheetState,
            dragHandle = null
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                PrimaryText(text = "Khoảng giá một phòng, một đêm")
                YSpacer(height = 5)

                //slider
                SliderComponent(maxValue = 5000000f, steps = 9)
                YSpacer(height = 5)
                HorizontalDivider(thickness = 0.8.dp, color = Color.LightGray)
                YSpacer(height = 5)

                //Hang sao
                PrimaryText(text = "Hạng sao")
                YSpacer(height = 5)
                StarFilter(
                    onStarSelected = { starNumber ->
                        val starNumberInt = starNumber.toIntOrNull() ?: return@StarFilter
                        onStarSelected(starSelected.toMutableList().apply {
                            if (contains(starNumberInt)) {
                                remove(starNumberInt)
                            } else {
                                add(starNumberInt)
                            }
                        })
                    }
                )
            }
            PrimaryButton(
                onClick = {
                    isFilterSheetOpened = false;
                    viewModel.filterHotelList(starSelected)
                },
                text = "Đóng",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            YSpacer(height = 5)

        }
    }


    if (isSortSheetOpened) {

        ModalBottomSheet(
            sheetState = sortSheetState,
            onDismissRequest = { isSortSheetOpened = false },
            dragHandle = null
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                RadioButtonComponent(
                    radioOptions = radioOption,
                    onOptionSelected = { option ->
                        onOptionSelected(option)
                    },
                    selectedOptionPara = selectedOption,
                    onClick = {
                        isSortSheetOpened = false;
                        viewModel.sortHotelList(selectedOption)
                    }

                )
                HorizontalDivider(thickness = 0.8.dp, color = Color.LightGray)
                PrimaryButton(
                    onClick = {
                        isSortSheetOpened = false;
                    },
                    text = "Đóng",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                YSpacer(height = 5)
            }
        }
    }
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(121.dp)
                    .background(Grey50Color),
            ) {
                Column(modifier = Modifier.padding(top = 21.dp, start = 27.dp, end = 27.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        PrimaryIconButton(
                            DrawableId = R.drawable.backbutton,
                            onClick = { navController.popBackStack() },
                            alt = "",
                        )

                        Column(verticalArrangement = Arrangement.SpaceBetween) { //Current location
                            Text(
                                text = shareDataHotelDetail.getSearchText(),
                                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            )
                            Text(
                                text = "${shareDataHotelDetail.getStartDate()}, ${shareDataHotelDetail.getPersonOption().first} đêm, ${shareDataHotelDetail.getPersonOption().third} phòng",
                                style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 12.sp)
                            )
                        }
                        PrimaryIconButton(DrawableId = R.drawable.search, onClick = {}, alt = "")
                    }
                    YSpacer(15)
                    Row(modifier = Modifier.fillMaxWidth()) {
                        PrimaryIconButton(DrawableId = R.drawable.filter, onClick = {
                            isFilterSheetOpened = true
                        }, alt = "")
                        XSpacer(25)
                        PrimaryIconButton(
                            DrawableId = R.drawable.sort,
                            onClick = {
                                isSortSheetOpened = true

                            },
                            alt = "",
                        )
                    }
                }

            }

        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            when (listHotelSearch) {
                is Result.Idle -> {
                    if (shareHotelDataViewModel.isCurrentLocation()) {
                        if (ActivityCompat.checkSelfPermission(
                                context,
                                android.Manifest.permission.ACCESS_FINE_LOCATION
                            ) != android.content.pm.PackageManager.PERMISSION_GRANTED
                            ||
                            ActivityCompat.checkSelfPermission(
                                context,
                                android.Manifest.permission.ACCESS_COARSE_LOCATION
                            ) != android.content.pm.PackageManager.PERMISSION_GRANTED
                            ||
                            !LocationProvider.isLocationEnabled(context)
                        ) {
                            ButtonRequestLocationPermission(onClick = {
                                viewModel.initLocationProvider(context)
                                launchMultiplePermissions.launch(permissions)
                            })
                        } else {
                            viewModel.initLocationProvider(context)
                            launchMultiplePermissions.launch(permissions)
                        }
                    } else {
                        viewModel.setStateHotelSearchLoading()
                    }
                }

                is Result.Loading -> {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator()
                    }
                }

                is Result.Error -> {
                    NotFoundHotel()
                }

                is Result.Success -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 54.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                    ) {
                        item {
                            Log.d("List Hotel data ", listHotelSearch.data.data.toString())
                            if (listHotelSearch.data.data != null) {
                                /*val hotellist = if (hotelListSorted.isNotEmpty()) {
                                    hotelListSorted
                                } else {
                                    listHotelSearch.data.data
                                }*/
                                Log.d("Star selected", listHotelSearch.data.data.toString())
                                listHotelSearch.data.data.forEachIndexed { index, hotelDTO ->
                                    HotelDetailCard2(hotelDTO = hotelDTO, navController = navController, click = {viewModel.postClickHotel(hotelDTO.id)})
                                    if (index < listHotelSearch.data.data.lastIndex) {
                                        Spacer(modifier = Modifier.height(15.dp))
                                    }
                                }
                            } else {
                                viewModel.setStateHotelSearchError()
                            }
                        }
                    }
                }
            }
        }
    }
}

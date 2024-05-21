package com.example.h5traveloto_booking.details.presentation.hoteldetails

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.h5traveloto_booking.account.AccountViewModel
import com.example.h5traveloto_booking.account.ListHotelsViewModel
import com.example.h5traveloto_booking.details.presentation.hoteldetails.components.HotelDetailCard
import com.example.h5traveloto_booking.details.presentation.hoteldetails.components.HotelDetailCard2
import com.example.h5traveloto_booking.main.presentation.home.components.HotelTagLarge
import com.example.h5traveloto_booking.share.shareHotelDataViewModel
import com.example.h5traveloto_booking.theme.Grey50Color
import com.example.h5traveloto_booking.ui_shared_components.*
import com.example.h5traveloto_booking.util.Result
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListHotels(navController: NavController,
               viewModel: ListHotelsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val permissions = arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )
    val launchMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.all { it.value }) {
            viewModel.startLocationUpdates()
        } else {
            Log.d("LocationProvider", "Permissions denied")
        }
    }

    LaunchedEffect(Unit){
        viewModel.initLocationProvider(context)
        if(shareHotelDataViewModel.checkExistedData()){
            viewModel.setStateHotelSearchSuccess(shareHotelDataViewModel.getListHotel()!!)
        }
        else{
            if(!shareHotelDataViewModel.isCurrentLocation()){
                viewModel.getHotelSearch()
            }
        }
    }
    val listHotelResponse = viewModel.ListHotelResponse.collectAsState().value
    val listHotelSearch = viewModel.ListHotelSearch.collectAsState().value

    Scaffold(
        topBar = {
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(121.dp)
                .background(Grey50Color),) {
                Column( modifier = Modifier.padding(top = 21.dp, start = 27.dp, end = 27.dp )) {
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,) {
                        PrimaryIconButton(DrawableId = R.drawable.backbutton, onClick = {navController.popBackStack()},alt = "",)

                        Column { //Current location
                            Text(text = "Khách sạn gần tôi", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp))
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "Th 6, 15 / 3 / 2024, 1 đêm, 1 phòng", style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 12.sp))
                        }
                        PrimaryIconButton(DrawableId = R.drawable.search, onClick = {},alt = "",)
                    }
                    YSpacer(15)
                    Row(modifier = Modifier.fillMaxWidth(),) {
                        PrimaryIconButton(DrawableId = R.drawable.filter, onClick = {},alt = "",)
                        XSpacer(25)
                        PrimaryIconButton(DrawableId = R.drawable.sort, onClick = {},alt = "",)
                    }
                }
                
            }

        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            when(listHotelSearch){
                is Result.Idle -> {
                    if(shareHotelDataViewModel.isCurrentLocation()){
                        if(ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != android.content.pm.PackageManager.PERMISSION_GRANTED
                            || ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != android.content.pm.PackageManager.PERMISSION_GRANTED
                        ){
                            ButtonRequestLocationPermission(onClick = {
                                launchMultiplePermissions.launch(permissions)
                            })
                        }
                        else{
                            viewModel.initLocationProvider(context)
                            launchMultiplePermissions.launch(permissions)
                        }
                    }
                    else{
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
                            .padding(vertical = 54.dp, horizontal = 15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                    ) {
                        item {
                            if(listHotelSearch.data.data!= null){
                                listHotelSearch.data.data.forEachIndexed { index, hotelDTO ->
                                    HotelDetailCard2(hotelDTO = hotelDTO, navController = navController)
                                    if (index < listHotelSearch.data.data.lastIndex) {
                                        Spacer(modifier = Modifier.height(15.dp))
                                    }
                                }
                            }
                            else{
                                viewModel.setStateHotelSearchError()
                            }
                        }
                    }
                }
            }
        }
    }
}

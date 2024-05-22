package com.example.h5traveloto_booking.main.presentation.homesearch.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.placeholder
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.main.presentation.data.dto.Search.Suggestion
import com.example.h5traveloto_booking.theme.Grey100Color
import com.example.h5traveloto_booking.theme.Grey500Color
import com.example.h5traveloto_booking.theme.OrangeColor
import com.example.h5traveloto_booking.theme.PrimaryColor
import com.example.h5traveloto_booking.ui_shared_components.InputIncrease
import com.example.h5traveloto_booking.ui_shared_components.ItemSearch
import com.example.h5traveloto_booking.ui_shared_components.TextSearchBasic

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchLocationScreen(
    onDismiss: () -> Unit,
    onComplete: (String, Suggestion) -> Unit,
    locationPre: String,
    isCurrentLocation: Boolean,
    setIsCurrentLocation: (Boolean) -> Unit,
    viewModel: SearchLocationViewModel = hiltViewModel()
) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val searchResults by viewModel.filteredCities.collectAsState()
    val searchPopular = CreateSearchPopular()


    Log.d("SearchLocationScreen", "render")

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchDistrictsVN()
    }

    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .background(Color.White),
            shape = RoundedCornerShape(0.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 0.dp, 0.dp, 0.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(PrimaryColor),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextSearchBasic(
                        value = searchQuery,
                        onValueChange = { query -> viewModel.updateSearchQuery(query) },
                        placeholder = "Thành phố, khách sạn",
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                            .padding(16.dp, 0.dp),

                    )
                    TextButton(
                        onClick = {
                            if(isCurrentLocation){
                                viewModel.updateSearchQuery("")
                                onDismiss()
                            }else{
                                viewModel.updateSearchQuery(locationPre)
                                onDismiss()
                            }
                        },
                        content = {
                            Text(
                                "HOÀN TẤT",
                                color = Color.White,
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp
                            )
                        }
                    )
                }
                if(searchQuery.isEmpty()) {
                    ItemSearch(
                        icon = {
                            Image(
                                painter = painterResource(id = R.drawable.targeticon),
                                contentDescription = "Clear",
                                colorFilter = ColorFilter.tint(PrimaryColor),
                                modifier = Modifier.size(36.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                        },
                        title = "Khách sạn gần tôi",
                        detail = "",
                        type = "",
                        onClick = {
                            viewModel.updateSearchQuery("")
                            setIsCurrentLocation(true)
                            onComplete("Khách sạn gần tôi", Suggestion("", "", "", "", 0.0, null, null, null))
                        },
                        isFirst = true
                    )
                    Text(
                        text = "Điểm đến phổ biến",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(20.dp, 32.dp)
                    )
                    searchPopular.forEach { item ->
                        ItemSearch(
                            title = item.title,
                            detail = item.detail,
                            type = item.type,
                            onClick = {
                                onComplete(item.title, Suggestion("", "", "", "", 0.0, null, null, null))
                                viewModel.updateSearchQuery(item.title)
                                setIsCurrentLocation(false)
                            },
                            icon = {},
                        )
                    }
                }

                // When Search
                Box(
                    modifier = Modifier.weight(1f)
                        .fillMaxSize()
                ){
                    LazyColumn {
                        items(searchResults) { city ->
                            ItemSearch(
                                title = city.name,
                                detail = if(city.district != null && city.province != null){
                                    city.district.fullName + ", " + city.province.name
                                }else if(city.province != null){
                                    city.province.name
                                } else {
                                    ""
                                },
                                onClick = {
                                    onComplete(city.name, city)
                                    viewModel.updateSearchQuery(city.name)
                                    setIsCurrentLocation(false)
                                },
                                icon = {},
                                type = if(city.index == "hotels_enriched"){
                                    "Khách sạn"
                                } else if(city.index == "provinces"){
                                    "Tỉnh thành"
                                }else if(city.index == "landmarks_enriched"){
                                    "Địa danh"
                                }
                                else{
                                    "Vùng"
                                }
                            )

                        }
                    }
                }
            }
        }
    }
}



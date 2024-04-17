package com.example.h5traveloto_booking.main.presentation.homesearch.screens

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
import com.example.h5traveloto_booking.theme.Grey100Color
import com.example.h5traveloto_booking.theme.Grey500Color
import com.example.h5traveloto_booking.theme.OrangeColor
import com.example.h5traveloto_booking.theme.PrimaryColor
import com.example.h5traveloto_booking.ui_shared_components.InputIncrease
import com.example.h5traveloto_booking.ui_shared_components.TextSearchBasic

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchLocationScreen(
    onDismiss: () -> Unit,
    onComplete: (String) -> Unit,
    viewModel: SearchLocationViewModel = hiltViewModel()
) {
    var textSearch by remember { mutableStateOf("") }
    val searchQuery by viewModel.searchQuery.collectAsState()
    val searchResults by viewModel.filteredCities.collectAsState()
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
                        placeholder = "Tìm kiếm",
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                            .padding(16.dp, 0.dp)
                    )
                    TextButton(
                        onClick = { onComplete("Ha Noi") },
                        content = {
                            Text(
                                "Hoàn tất",
                                color = Color.White,
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp
                            )
                        }
                    )
                }
                Box(
                    modifier = Modifier.weight(1f)
                        .fillMaxSize()
                ){
                    LazyColumn {
                        items(searchResults) { city ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .clickable {
                                        onComplete(city)
                                    },
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    city,
                                    fontSize = 16.sp,
                                    color = Color.Black,
                                    modifier = Modifier.padding(16.dp, 0.dp)
                                )
                            }
                            Divider(
                                color = Grey100Color,
                                thickness = 1.dp
                            )
                        }
                    }
                }
            }
        }
    }
}



package com.example.h5traveloto_booking.ui_shared_components.my_calendar.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.wear.compose.material.Icon
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MonthYearPicker (
    month : Month,
    year : Int,
    onComplete : (month : Month, year : Int) -> Unit,
    onDismiss : () -> Unit
) {
    val showMonthPicker = remember {
        mutableStateOf(true)
    }
    val showYearPicker = remember {
        mutableStateOf(false)
    }
    val yearSelected = remember {
        mutableStateOf(year)
    }

    Dialog(onDismissRequest = { onDismiss() }) {
        Card (
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            modifier = Modifier
        ) {
            if (showMonthPicker.value) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row {
                        Text(
                            text = "${yearSelected.value}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .clickable {
                                    showYearPicker.value = true
                                    showMonthPicker.value = false
                                }
                                .padding(vertical = 5.dp)
                                .align(Alignment.CenterVertically),
                            textAlign = TextAlign.Center
                        )
                    }
                    MonthPicker (
                        onMonthSelected = {
                            onComplete(it, yearSelected.value)
                            showMonthPicker.value = false
                        },
                        monthCount = 12
                    )
                }
            }
            if (showYearPicker.value) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row (
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                    ) {
                        Row (
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            IconButton(
                                onClick = {
                                showMonthPicker.value = true
                                showYearPicker.value = false
                                },
                                modifier = Modifier
                                    .fillMaxWidth(0.25f)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBackIosNew,
                                    tint = Color.Black,
                                    contentDescription = "Previous Month"
                                )
                            }
                            Text(
                                text = "${yearSelected.value}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .fillMaxWidth(0.66f)
                                    .align(Alignment.CenterVertically),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    YearPicker (
                        startDate = LocalDate(year,month,1),
                        onYearSelected = {
                            yearSelected.value = it
                            showMonthPicker.value = true
                            showYearPicker.value = false
                        },
                        yearView = { year ->
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    year.toString(),
                                    modifier = Modifier
                                        .padding(10.dp))
                            }
                        }
                    )
                }
            }
        }
    }
}
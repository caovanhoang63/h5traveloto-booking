package com.example.h5traveloto_booking.details.presentation.hoteldetails.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.theme.StarColor
import com.example.h5traveloto_booking.ui_shared_components.PrimaryIconButton


@Composable
fun StarFilter(
    onStarSelected: (String) -> Unit
) {
    val oneStarState = remember {
        mutableStateOf(false)
    }
    val twoStarState = remember {
        mutableStateOf(false)
    }
    val threeStarState = remember {
        mutableStateOf(false)
    }
    val fourStarState = remember {
        mutableStateOf(false)
    }
    val fiveStarState = remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        StarIconBox(oneStarState, "1") { starNumber ->
            onStarSelected(starNumber)
        }
        StarIconBox(twoStarState, "2"){ starNumber ->
            onStarSelected(starNumber)
        }
        StarIconBox(threeStarState, "3"){ starNumber ->
            onStarSelected(starNumber)
        }
        StarIconBox(fourStarState, "4"){ starNumber ->
            onStarSelected(starNumber)
        }
        StarIconBox(fiveStarState, "5"){ starNumber ->
            onStarSelected(starNumber)
        }


    }
}
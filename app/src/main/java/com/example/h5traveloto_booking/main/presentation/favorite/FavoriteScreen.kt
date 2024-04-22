package com.example.h5traveloto_booking.main.presentation.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.h5traveloto_booking.account.*
import com.example.h5traveloto_booking.theme.ScreenBackGround
import com.example.h5traveloto_booking.ui_shared_components.BoldText
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(navController: NavController) {

    Scaffold(
        modifier = Modifier
            .background(ScreenBackGround)
            .fillMaxSize(),
        topBar = {
            Row (
                Modifier.padding(10.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,) {
                BoldText(text = "Favorite",
                    //  fontWeight = FontWeight.Bold,
                    // fontSize = 20.sp)
                )
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            )
            {

            }
        }
    )

}

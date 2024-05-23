package com.example.h5traveloto_booking.main.presentation.schedule.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.UserBookingDTO

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListBookingDialog (
    bookingList: List<UserBookingDTO>,
    onDismiss: () -> Unit,
    navController: NavController
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {
        LazyColumn {
            items(bookingList) { item: UserBookingDTO ->
                BookingCard(
                    isStatusVisible = false,
                    bookingData = item,
                    navController = navController
                )
            }
        }
    }
}
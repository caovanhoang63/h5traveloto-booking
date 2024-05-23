package com.example.h5traveloto_booking.details.presentation.bookingdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.Button
import com.example.h5traveloto_booking.details.presentation.bookingdetails.screens.BookingDetailsFillingScreen
import com.example.h5traveloto_booking.details.presentation.bookingdetails.screens.BookingReviewScreen
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.CreateBookingDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.IdRespondDTO
import com.example.h5traveloto_booking.navigate.Screens

@Composable
fun BookingScreen (
    navController: NavController,
    bookingData: CreateBookingDTO,
    viewModel: BookingScreenViewModel = hiltViewModel(),
) {
    val bookingNavController = rememberNavController()

    NavHost(
        navController = bookingNavController,
        startDestination = Screens.BookingDetailsFillingScreen.name
    ) {
        composable(route = Screens.BookingDetailsFillingScreen.name) {
            BookingDetailsFillingScreen(
                navController = bookingNavController,
                bookingData = bookingData
            )
        }
        composable(route = Screens.BookingDetailsReviewScreen.name) {
            BookingReviewScreen(
                navController = bookingNavController,
                bookingData = bookingData
            )
        }
    }
}
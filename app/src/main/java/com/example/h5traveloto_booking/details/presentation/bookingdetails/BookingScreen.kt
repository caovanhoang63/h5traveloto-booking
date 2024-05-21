package com.example.h5traveloto_booking.details.presentation.bookingdetails

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.h5traveloto_booking.details.presentation.bookingdetails.screens.BookingDetailsFillingScreen
import com.example.h5traveloto_booking.details.presentation.bookingdetails.screens.BookingReviewScreen
import com.example.h5traveloto_booking.navigate.Screens

@Composable
fun BookingScreen (
    navController: NavController
) {
    val bookingNavController = rememberNavController()

    NavHost(
        navController = bookingNavController,
        startDestination = Screens.BookingDetailsFillingScreen.name
    ) {
        composable(route = Screens.BookingDetailsFillingScreen.name) {
            BookingDetailsFillingScreen(
                navController = bookingNavController,
                parentNavController = navController
            )
        }
        composable(route = Screens.BookingDetailsReviewScreen.name) {
            BookingReviewScreen(
                navController = bookingNavController
            )
        }
    }
}
package com.example.h5traveloto_booking.main.presentation.homenavigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.h5traveloto_booking.main.presentation.home.HomeScreen
import com.example.h5traveloto_booking.main.presentation.homesearch.HomeSearchScreen
import com.example.h5traveloto_booking.navigate.Screens

@Composable
fun HomeNavigation(navController: NavController) {
    val homeNavController = rememberNavController()

    NavHost(
        navController = homeNavController,
        startDestination = Screens.HomeScreen.name
    ) {
        composable(route = Screens.HomeScreen.name) {
            HomeScreen(navController = homeNavController, navAppController = navController)
        }
        composable(route = Screens.HomeSearchScreen.name) {
            HomeSearchScreen(navController = homeNavController, navAppNavController = navController)
        }
    }
}
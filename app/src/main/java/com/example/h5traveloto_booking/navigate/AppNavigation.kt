package com.example.h5traveloto_booking.navigate

import ListRooms
import WebViewScreen
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.h5traveloto_booking.account.ChangePassword.ChangePasswordScreen
import com.example.h5traveloto_booking.auth.presentation.login.LoginScreen
import com.example.h5traveloto_booking.auth.presentation.signup.SignUpScreen
import com.example.h5traveloto_booking.main.presentation.MainScreen
import com.example.h5traveloto_booking.account.personal_information.PersonalInformationScreen
import com.example.h5traveloto_booking.account.personal_information.UpdateInformation.UpdateInformationScreen
import com.example.h5traveloto_booking.details.presentation.hoteldetails.HotelDetailsScreen
import com.example.h5traveloto_booking.details.presentation.hoteldetails.ListHotels
import com.example.h5traveloto_booking.main.presentation.favorite.AllFavorite.AllFavoriteScreen
import com.example.h5traveloto_booking.payment.WebViewScreen2

@Composable
fun AppNavigation(startDestination : String ) {

    val navController = rememberNavController()


    NavHost (
        navController = navController,
        startDestination = startDestination

    ) {
        composable(route = Screens.SignUpScreen.name) {
            SignUpScreen(navController = navController)
        }
        composable(route = Screens.LoginScreen.name) {
            LoginScreen(navController = navController )
        }
        composable(route = Screens.MainScreen.name  ) {
            MainScreen(navController =navController)
        }
        composable(route = Screens.ListHotels.name  ) {
            ListHotels(navController = navController)
        }
        composable(route = Screens.PersonalInformationScreen.name) {
            PersonalInformationScreen(navController = navController)
        }
        composable(route = Screens.ListRooms.name) {
            ListRooms(navController = navController)
        }
        composable(route = Screens.HotelDetailsScreen.name  ) {
            HotelDetailsScreen(navController =navController)
        }
        composable(route = Screens.UpdateInformationScreen.name ) {
            UpdateInformationScreen(navController = navController)
        }
        composable(route = Screens.ChangePasswordScreen.name ) {
            ChangePasswordScreen(navController = navController)
        }
        composable(route = Screens.AllFavoriteScreen.name ) {
            AllFavoriteScreen(navController = navController)
        }
        composable("webview/{url}"){
            backStackEntry ->
            val url = backStackEntry.arguments?.getString("url")?:""
            Log.d("hehe",url.toString())
         //  WebViewScreen2(url = url, tmnCode = "CXE5IZGS",scheme="resultactivity",isSandbox = true)
          WebViewScreen(url = url)
        }
    }
}
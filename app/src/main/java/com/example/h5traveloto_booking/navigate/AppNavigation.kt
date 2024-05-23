package com.example.h5traveloto_booking.navigate

import ListRooms
import WebViewScreen3
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.h5traveloto_booking.account.ChangePassword.ChangePasswordScreen
import com.example.h5traveloto_booking.auth.presentation.login.LoginScreen
import com.example.h5traveloto_booking.auth.presentation.signup.SignUpScreen
import com.example.h5traveloto_booking.main.presentation.MainScreen
import com.example.h5traveloto_booking.account.personal_information.PersonalInformationScreen
import com.example.h5traveloto_booking.account.personal_information.UpdateInformation.UpdateInformationScreen
import com.example.h5traveloto_booking.chat.presentation.ChatScreen
import com.example.h5traveloto_booking.details.presentation.bookingdetails.BookingScreen
import com.example.h5traveloto_booking.details.presentation.data.dto.hotelDetails.HotelDetailsDTO
import com.example.h5traveloto_booking.details.presentation.hoteldetails.HotelDetailsScreen
import com.example.h5traveloto_booking.details.presentation.hoteldetails.ListHotels
import com.example.h5traveloto_booking.details.presentation.hoteldetails.components.ListPolicies
import com.example.h5traveloto_booking.details.presentation.hoteldetails.components.ListReviews
import com.example.h5traveloto_booking.details.presentation.roomdetails.RoomDetailsScreen
import com.example.h5traveloto_booking.main.presentation.favorite.AddCollection.AddCollectionScreen
import com.example.h5traveloto_booking.main.presentation.favorite.AddCollection.AddImageInCollectionScreen
import com.example.h5traveloto_booking.main.presentation.favorite.AddHotelInCollection.AddHotelInCollectionScreen
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.BookingDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.Booking.CreateBookingDTO
import com.example.h5traveloto_booking.main.presentation.data.dto.SearchRoomType.SearchRoomTypeDTO
import com.example.h5traveloto_booking.main.presentation.favorite.AllFavorite.AllFavoriteScreen
import com.example.h5traveloto_booking.main.presentation.favorite.DetailCollection.DetailCollectionScreen
import com.example.h5traveloto_booking.payment.WebViewScreen2
import com.example.h5traveloto_booking.main.presentation.map.LocationProvider
import com.google.gson.Gson

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
        composable(route = Screens.ChatScreen.name ) {
            ChatScreen(navController = navController)
        }
        composable(route = Screens.AllFavoriteScreen.name ) {
            AllFavoriteScreen(navController = navController)
        }
        composable(route ="${Screens.ListPolicies.name}/{Object}" ) {
            backStackEntry ->
            val Object = Gson().fromJson(backStackEntry.arguments?.getString("Object"), HotelDetailsDTO::class.java)
            ListPolicies(navController = navController, Object = Object.data)

        }
        composable(route = Screens.ListReviews.name ) {
            ListReviews(navController = navController)
        }
        composable(route = Screens.AddCollectionScreen.name ) {
            AddCollectionScreen(navController = navController)
        }
        composable(route = Screens.AddHotelInCollectionScreen.name){
            AddHotelInCollectionScreen(navController = navController, collectionId = "")
        }
        composable(route = Screens.AddImageInCollectionScreen.name ) {
            AddImageInCollectionScreen(navController = navController)
        }
        composable(route="detailcollection/{CollectionID}",
            arguments = listOf(navArgument("CollectionID")
            {type = NavType.StringType }
            )
        ) {
            backStackEntry ->
            val collectionID = backStackEntry.arguments?.getString("CollectionID")
            Log.d("hehehee",collectionID.toString())
            if (collectionID != null) {
                DetailCollectionScreen(navController = navController, collectionID = collectionID)
            }
        }
        composable(route = "${Screens.BookingScreen.name}/{bookingData}") { backstabEntry ->
            val bookingData = Gson().fromJson(backstabEntry.arguments?.getString("bookingData"), CreateBookingDTO::class.java)
            BookingScreen(navController = navController, bookingData = bookingData)
        }
        composable("webview/{url}"){
                backStackEntry ->
            val url = backStackEntry.arguments?.getString("url")?:""
            Log.d("hehe1",url.toString())
            //  WebViewScreen2(url = url, tmnCode = "CXE5IZGS",scheme="resultactivity",isSandbox = true)
            val onPaymentResult: (String) -> Unit = { paymentResult ->
                // Xử lý kết quả thanh toán tại đây
                when (paymentResult) {
                    "SuccessBackAction" -> {
                        // Xử lý khi thanh toán thành công
                        navController.navigateUp()
                        //navController.popBackStack()
                    }
                    "FaildBackAction" -> {
                        // Xử lý khi thanh toán thất bại
                        Log.d("URL","hehe")
                        //navController.navigate(Screens.AccountScreen.name)
                        //  navController.navigateUp()
                        navController.popBackStack()
                        // navController.navigate(Screens.AccountScreen.name)
                    }
                    "WebBackAction" -> {
                        // Xử lý khi quay lại từ web
                        navController.navigateUp()
                    }
                }
            }
            WebViewScreen3(url = url, scheme = "resultactivity",onPaymentResult = onPaymentResult)
        }

        composable(route ="${Screens.RoomDetailsScreen.name}/{Object}" ) {
                backStackEntry ->
            val Object = Gson().fromJson(backStackEntry.arguments?.getString("Object"), com.example.h5traveloto_booking.main.presentation.data.dto.SearchRoomType.Data::class.java)
            RoomDetailsScreen(navController = navController, Object = Object)

        }
    }
}
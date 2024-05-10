package com.example.h5traveloto_booking



import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.h5traveloto_booking.navigate.AppNavigation
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.navigate.SplashScreen
import com.example.h5traveloto_booking.theme.H5travelotobookingTheme
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : ComponentActivity(
) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            H5travelotobookingTheme {
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                NavigationOnApp()
                //AppNavigation("MainScreen")
            }
        }
    }


}

@Composable
fun NavigationOnApp() {
    val navController = rememberNavController()
    var screen: String = rememberSaveable { mutableStateOf("LoginScreen") }.value
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.name
    ) {
        composable(Screens.SplashScreen.name) {
            SplashScreen(navController, setScreenStart = { name -> screen = name })
        }
        composable(Screens.AppNavigation.name){
            AppNavigation(screen)
        }
    }
}






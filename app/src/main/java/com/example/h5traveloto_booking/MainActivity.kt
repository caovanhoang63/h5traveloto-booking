package com.example.h5traveloto_booking



import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.WindowManager
import android.widget.Toast
import android.window.OnBackInvokedDispatcher
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.h5traveloto_booking.chat.presentation.ChatScreen
import com.example.h5traveloto_booking.details.presentation.roomdetails.RoomDetailsScreen
import com.example.h5traveloto_booking.navigate.AppNavigation
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.navigate.SplashScreen
import com.example.h5traveloto_booking.share.BackStack
import com.example.h5traveloto_booking.theme.H5travelotobookingTheme
import dagger.hilt.android.AndroidEntryPoint
import websocket.SocketHandler
import websocket.setupEvent
import websocket.socketHandler1
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity @Inject constructor(
) : ComponentActivity (
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            H5travelotobookingTheme {
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                setupEvent(socketHandler1)
                NavigationOnApp()

            }
        }
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        when (keyCode) {
            KeyEvent.KEYCODE_BACK -> BackStack.callBackIndexNavBar()
        }
        return super.onKeyDown(keyCode, event)
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






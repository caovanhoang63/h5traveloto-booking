package com.example.h5traveloto_booking


import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.h5traveloto_booking.navigate.AppNavigation
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.theme.H5travelotobookingTheme
import com.example.h5traveloto_booking.util.SharedPrefManager
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity(
) {
    val start: String = Screens.MainScreen.name
    var token: String ?= null

    fun getToken(){
        val sharedPrefManager = SharedPrefManager(this)
        token = sharedPrefManager.getToken()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            H5travelotobookingTheme {
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                getToken()
                if(token == null){
                    AppNavigation("LoginScreen")
                }else
                    AppNavigation("MainScreen")
            }
        }
    }
}


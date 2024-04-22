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
import getUserLocation
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity(
) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            H5travelotobookingTheme {
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

                AppNavigation(Screens.ListHotels.name)

            }
        }
    }
}


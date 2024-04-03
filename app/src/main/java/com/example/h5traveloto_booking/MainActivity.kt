package com.example.h5traveloto_booking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.h5traveloto_booking.navigate.AppNavigation
import com.example.h5traveloto_booking.theme.H5travelotobookingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            H5travelotobookingTheme {
              H5travelotobookingTheme {
                  AppNavigation()
              }
            }
        }
    }
}


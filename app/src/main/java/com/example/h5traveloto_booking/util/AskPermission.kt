package com.example.h5traveloto_booking.util

import android.app.Activity
import android.content.Context
import androidx.core.app.ActivityCompat

fun askPermissions(context: Context, requestCode: Int, vararg permissions: String) {
    ActivityCompat.requestPermissions(context as Activity, permissions, requestCode)
}
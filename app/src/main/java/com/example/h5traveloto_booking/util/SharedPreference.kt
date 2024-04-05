package com.example.h5traveloto_booking.util

import LatandLong
import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("TOKEN", token)
        editor.apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("TOKEN", null)
    }

    fun saveRefreshToken(token: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("REFRESH_TOKEN", token)
        editor.apply()
    }

    fun getRefreshToken(): String? {
        return sharedPreferences.getString("REFRESH_TOKEN", null)
    }

    fun saveCurLocation(location : LatandLong)  {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("lat", location.latitude.toString())
        editor.putString("lng" , location.longitude.toString())
        editor.apply()
    }

    fun getCurLatitude() : Double  {
        sharedPreferences.getString("lat",null)?.toDouble()?.let {
            return it
        }
        return 0.0
    }


    fun getCurLng() : Double {
        sharedPreferences.getString("lng",null)?.toDouble()?.let {
            return it
        }
        return 0.0
    }




}
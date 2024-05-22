package com.example.h5traveloto_booking.main.presentation.map


import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import android.os.Looper
import android.util.Log
import com.example.h5traveloto_booking.MainActivity
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng


object LocationProvider {

    public fun isLocationEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        try{
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        } catch (e: Exception) {
            Log.e("LocationProvider", "Error checking location provider")
        }
        return false
    }

    public fun createLocationRequest(context: Context) {
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,10000
        ).setMinUpdateIntervalMillis(2000).build()

        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)

        val client = LocationServices.getSettingsClient(context)
        val task = client.checkLocationSettings(builder.build())

        task.addOnSuccessListener {
        }

        task.addOnFailureListener { exception ->
            if(exception is ResolvableApiException) {
                try {
                    exception.startResolutionForResult(context as MainActivity, 100)
                } catch (sendEx: Exception) {
                    Log.e("LocationProvider", "Error getting location settings resolution")
                }
            }
        }
    }
}

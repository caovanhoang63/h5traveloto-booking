package com.example.h5traveloto_booking.main.presentation.map


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.example.h5traveloto_booking.MainActivity
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng

class LocationProvider(val context: Context) {
    private val permissions = arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )
    private var fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    private var locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            Log.d("LocationProvider", "Location received")
            for (location in locationResult.locations) {
                Log.d("LocationProvider", "Callback set Latitude: ${location.latitude}, Longitude: ${location.longitude}")
                currentLocation = LatLng(location.latitude, location.longitude)
            }
        }
    }
    private var currentLocation: LatLng? = null


    @SuppressLint("MissingPermission")
    public fun startLocationUpdates() {
        locationCallback?.let {
            val locationRequest = LocationRequest.Builder(
                Priority.PRIORITY_HIGH_ACCURACY, 100
            )
                .setWaitForAccurateLocation(true)
                .setMinUpdateIntervalMillis(3000)
                .setMaxUpdateDelayMillis(100)
                .build()
            fusedLocationClient.requestLocationUpdates(locationRequest, it, Looper.getMainLooper())
            Log.d("LocationProvider", "Requesting location updates")
        }
    }

    @SuppressLint("MissingPermission")
    public fun getCurrentLocation(setLocation: (crtLocation: LatLng?) -> Unit) {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                Log.d("LocationProvider", "Latitude: ${location.latitude}, Longitude: ${location.longitude}")
                currentLocation = LatLng(location.latitude, location.longitude)
                setLocation(currentLocation)
            }
        }
    }

    public fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }
}

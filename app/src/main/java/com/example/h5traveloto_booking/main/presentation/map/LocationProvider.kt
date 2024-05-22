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
//    private val permissions = arrayOf(
//        android.Manifest.permission.ACCESS_FINE_LOCATION,
//        android.Manifest.permission.ACCESS_COARSE_LOCATION
//    )
//    private lateinit var fusedLocationClient: FusedLocationProviderClient
//    private val locationCallback: LocationCallback = object : LocationCallback() {
//        override fun onLocationResult(locationResult: LocationResult) {
//            super.onLocationResult(locationResult)
//            Log.d("LocationProvider", "Location received")
//            for (location in locationResult.locations) {
//                Log.d("LocationProvider", "Callback set Latitude: ${location.latitude}, Longitude: ${location.longitude}")
//                currentLocation = LatLng(location.latitude, location.longitude)
//            }
//            stopLocationUpdates()
//        }
//    }
//    private var currentLocation: LatLng? = null
//
//
//    @SuppressLint("MissingPermission")
//    public fun startLocationUpdates() {
//        locationCallback?.let {
//            val locationRequest = LocationRequest.Builder(
//                Priority.PRIORITY_HIGH_ACCURACY, 100
//            )
//                .setWaitForAccurateLocation(true)
//                .setMinUpdateIntervalMillis(3000)
//                .setMaxUpdateDelayMillis(100)
//                .build()
//            fusedLocationClient.requestLocationUpdates(locationRequest, it, Looper.getMainLooper())
//            Log.d("LocationProvider", "Requesting location updates")
//        }
//    }
//
//    @SuppressLint("MissingPermission")
//    public fun getCurrentLocation(setLocation: (crtLocation: LatLng?) -> Unit) {
//        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
//            location?.let {
//                Log.d("LocationProvider", "Latitude: ${location.latitude}, Longitude: ${location.longitude}")
//                currentLocation = LatLng(location.latitude, location.longitude)
//                setLocation(currentLocation)
//            }
//        }
//    }
//
//    public fun initLocationProvider(context: Context) {
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
//    }
//
//    public fun stopLocationUpdates() {
//        fusedLocationClient.removeLocationUpdates(locationCallback)
//    }

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
        ).setMinUpdateIntervalMillis(5000).build()

        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)

        val client = LocationServices.getSettingsClient(context)
        val task = client.checkLocationSettings(builder.build())

        task.addOnSuccessListener {
            Log.d("LocationProvider", "Location settings satisfied")
        }

        task.addOnFailureListener { exception ->
            if(exception is ResolvableApiException) {
                try {
                    Log.d("LocationProvider", "Location settings not satisfied")
                    exception.startResolutionForResult(context as MainActivity, 100)
                } catch (sendEx: Exception) {
                    Log.e("LocationProvider", "Error getting location settings resolution")
                }
            }
        }
    }
}

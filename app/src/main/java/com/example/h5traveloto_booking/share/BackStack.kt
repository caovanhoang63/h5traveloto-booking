package com.example.h5traveloto_booking.share

import android.util.Log

object BackStack {
    private var setIndexNavBar: () -> Unit = { Log.d("BackStack","oke") }

    fun setIndexNavBar(setIndexNavBar: () -> Unit) {
        this.setIndexNavBar = setIndexNavBar
    }

    fun callBackIndexNavBar() {
        setIndexNavBar()
    }
}
package com.example.h5traveloto_booking.util

import androidx.activity.OnBackPressedCallback
import androidx.compose.runtime.*

@Composable
fun BackHandler(
    enabled: Boolean = true,
    onBack: () -> Unit,
) {
    val currentOnBackPressed by rememberUpdatedState(onBack)
    val backCallback = remember {
        object : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() {
                currentOnBackPressed()
            }
        }
    }
    DisposableEffect(enabled) {
        backCallback.isEnabled = enabled
        onDispose {
            backCallback.remove()
        }
    }
}
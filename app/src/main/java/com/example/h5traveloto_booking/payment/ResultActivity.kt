package com.example.h5traveloto_booking.payment

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.setContent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.h5traveloto_booking.navigate.AppNavigation
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.navigate.SplashScreen
import com.example.h5traveloto_booking.theme.H5travelotobookingTheme
class ResultActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            H5travelotobookingTheme {
                val resultMessage = remember { mutableStateOf("Processing payment result...") }

                handleIntent(intent) { message ->
                    resultMessage.value = message
                }

                ResultScreen(resultMessage.value)
            }
        }
    }
    private fun handleIntent(intent: Intent, onResultProcessed: (String) -> Unit) {
        val data: Uri? = intent.data
        if (data != null) {
            val transactionStatus = data.getQueryParameter("vnp_TransactionStatus")
            val transactionId = data.getQueryParameter("vnp_TransactionNo")
            val amount = data.getQueryParameter("vnp_Amount")

            if (transactionStatus == "00") {
                // Giao dịch thành công
                updateOrderStatus(transactionId, "PAID")
                onResultProcessed("Payment successful!")
            } else {
                // Giao dịch thất bại
                onResultProcessed("Payment failed with status: $transactionStatus")
            }
        } else {
            onResultProcessed("Payment result is null.")
        }
    }

    private fun updateOrderStatus(transactionId: String?, status: String) {
        // Cập nhật trạng thái đơn hàng trong cơ sở dữ liệu
    }
}

@Composable
fun ResultScreen(message: String) {
    Surface(
        color = MaterialTheme.colors.background,
    ) {
        Text(text = message)
    }
}


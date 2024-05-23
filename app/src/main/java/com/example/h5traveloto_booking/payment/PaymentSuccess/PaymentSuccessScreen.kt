package com.example.h5traveloto_booking.payment.PaymentSuccess

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.ui_shared_components.BoldText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentSuccessScreen(navController: NavController) {
    Scaffold(
        topBar = {
            Row(
                Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                BoldText(
                    text = "Thanh toán thành công",
                    //  fontWeight = FontWeight.Bold,
                    // fontSize = 20.sp)
                )
            }
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = Color.Green,
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Thanh toán của bạn đã thành công!",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { navController.popBackStack(Screens.MainScreen.name, inclusive = false) }) {
                        Text("Trở về trang chủ")
                    }
                }
            }
        }
    )
}
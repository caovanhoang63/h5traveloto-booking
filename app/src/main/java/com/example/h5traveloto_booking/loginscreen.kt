// Main Screen
package com.example.h5traveloto_booking

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 28.dp, end = 28.dp, top = 40.dp),
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "LoginLogo",
                modifier = Modifier
                    .height(25.dp)
                    .width(200.dp)
            )
            Spacer(modifier = Modifier.height(19.dp))
            Text(text = "Welcome Back!", fontSize = 16.sp, fontWeight = FontWeight.Bold)

        }
        Spacer(modifier = Modifier.height(128.dp))
        Column {
            Text(text = "Please login to continue", fontSize = 12.sp)
            Spacer(modifier = Modifier.height(30.dp))

            Text(text = "Email", fontSize = 12.sp)
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Enter your email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(40.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Password", fontSize = 12.sp)
                Text(
                    text = "Forgot your password",
                    fontSize = 12.sp,
                    modifier = Modifier.clickable { }
                )
            }

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Enter your password") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {},
                modifier = Modifier
                    .height(52.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),

                ) {

                Text(text = "Login")
            }
        }
        Spacer(modifier = Modifier.height(128.dp))
        Column(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "New to H5Traveloto?", fontSize = 12.sp)
            Text(
                text = "Create Account Here",
                fontSize = 14.sp,
                color = Color.Blue,
                modifier = Modifier.clickable { }
            )
        }
    }
}
package com.example.h5traveloto_booking

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
fun SignupScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 21.dp, end = 21.dp, top = 40.dp),
    ) {
        Column(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.onlylogo),
                contentDescription = "logo",
                modifier = Modifier
                    .height(45.dp)
                    .width(50.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "Create Account", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(62.dp))
        Column {
            Text(text = "First Name", fontSize = 12.sp)
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Enter your first name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "Last Name", fontSize = 12.sp)
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Enter your last name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "Email", fontSize = 12.sp)
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Enter your email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "Password", fontSize = 12.sp)
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Enter your password") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = {},
                modifier = Modifier
                    .height(52.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),

                ) {

                Text(text = "Create Account")
            }

        }
        Spacer(modifier = Modifier.height(22.dp))
        Column(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Already have an account?", fontSize = 12.sp)
            Text(
                text = "Login",
                fontSize = 14.sp,
                color = Color.Blue,
                modifier = Modifier.clickable { }
            )
        }
    }
}
// Main Screen
package screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.h5traveloto_booking.R
import common.PrimaryColor
import components.PrimaryButton
import components.TextBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
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

            TextBox(
                modifier = Modifier.fillMaxWidth(),
                lable = "Email",
                placeHolder = "Enter your email"
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

            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
                text = "Login"
            )
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
                color = PrimaryColor,
                modifier = Modifier.clickable { navController.navigate("signup") {
                } }
            )
        }
    }
}
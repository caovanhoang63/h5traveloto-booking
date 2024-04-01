package screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
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
fun SignupScreen(navController: NavController) {
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
            TextBox(
                modifier = Modifier.fillMaxWidth(),
                lable = "First Name",
                placeHolder = "Enter your first name"
            )

            Spacer(modifier = Modifier.height(30.dp))
            TextBox(
                modifier = Modifier.fillMaxWidth(),
                lable = "Last Name",
                placeHolder = "Enter your last name"
            )
            Spacer(modifier = Modifier.height(30.dp))
            TextBox(
                modifier = Modifier.fillMaxWidth(),
                lable = "Email Name",
                placeHolder = "Enter your email"
            )
            Spacer(modifier = Modifier.height(30.dp))
            TextBox(
                modifier = Modifier.fillMaxWidth(),
                lable = "Password",
                placeHolder = "Enter your password"
            )
            Spacer(modifier = Modifier.height(30.dp))
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
                text = "Sign up"
            )
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
                color = PrimaryColor,
                modifier = Modifier.clickable { navController.navigate("login")}
            )
        }
    }
}
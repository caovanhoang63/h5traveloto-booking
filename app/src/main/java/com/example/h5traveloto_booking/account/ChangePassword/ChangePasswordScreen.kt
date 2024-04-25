package com.example.h5traveloto_booking.account.ChangePassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.account.personal_information.DeleteAccount
import com.example.h5traveloto_booking.account.personal_information.PersonalData
import com.example.h5traveloto_booking.account.personal_information.UpdateInformation.MyDropdownMenu
import com.example.h5traveloto_booking.theme.ScreenBackGround
import com.example.h5traveloto_booking.ui_shared_components.BoldText
import com.example.h5traveloto_booking.ui_shared_components.PrimaryIconButton
import com.example.h5traveloto_booking.ui_shared_components.XSpacer
import com.example.h5traveloto_booking.ui_shared_components.YSpacer
import com.example.h5traveloto_booking.util.ui_shared_components.PrimaryButton
import com.example.h5traveloto_booking.util.ui_shared_components.TextBoxSingle

@Composable
fun ChangePasswordScreen(navController: NavController) {
    var currentPassword by rememberSaveable { mutableStateOf("") }
    var newPassword by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBackGround),
        topBar = {
            Row (
                Modifier.padding(16.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                PrimaryIconButton(R.drawable.backarrow48, onClick = {navController.navigateUp() /*navController.popBackStack*/},"", modifier = Modifier )
                XSpacer(70)
                BoldText(text = "Change Password",
                    //  fontWeight = FontWeight.Bold,
                    // fontSize = 20.sp)
                )
            }
        },
        content = {
                innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
            ) {
                YSpacer(8)
                TextBoxSingle(label = "Current Password", value = currentPassword, onValueChange = { currentPassword = it },modifier = Modifier.padding(vertical = 16.dp, horizontal = 20.dp).fillMaxWidth(), placeholder = "")
                YSpacer(8)
                TextBoxSingle(label = "New Password", value = newPassword, onValueChange = { newPassword = it },modifier = Modifier.padding(vertical = 16.dp, horizontal = 20.dp).fillMaxWidth(), placeholder = "")
                YSpacer(8)
                TextBoxSingle(label = "Confirm Password", value = confirmPassword, onValueChange = { confirmPassword = it },modifier = Modifier.padding(vertical = 16.dp, horizontal = 20.dp).fillMaxWidth(), placeholder = "")
                YSpacer(32)
                PrimaryButton(onClick = { navController.navigateUp()}, modifier = Modifier.fillMaxWidth().padding(vertical = 0.dp, horizontal = 20.dp),text = "Done",)

            }
        }
    )
}
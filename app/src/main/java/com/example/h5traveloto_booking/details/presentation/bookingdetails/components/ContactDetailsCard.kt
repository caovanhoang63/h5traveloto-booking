package com.example.h5traveloto_booking.details.presentation.bookingdetails.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.h5traveloto_booking.account.AccountViewModel
import com.example.h5traveloto_booking.account.PersonalInformationViewModel
import com.example.h5traveloto_booking.account.personal_information.UpdateInformation.UpdateInformationViewModel
import com.example.h5traveloto_booking.main.presentation.MainViewModel
import com.example.h5traveloto_booking.navigate.Screens
import com.example.h5traveloto_booking.share.UserShare
import com.example.h5traveloto_booking.theme.*
import com.example.h5traveloto_booking.ui_shared_components.BoldText
import com.example.h5traveloto_booking.ui_shared_components.YSpacer

@Composable
fun ContactDetailsCard (
    viewModel: UpdateInformationViewModel =hiltViewModel(),
) {
    val showContactDialog = remember {
        mutableStateOf(false)
    }
    var phone by remember { mutableStateOf(UserShare.User.phone?.ifEmpty { "" })}
    var nameText by remember {
        mutableStateOf(TextFieldValue("${UserShare.User.lastName} ${UserShare.User.firstName}"))
    }
    var phoneText by remember {
        mutableStateOf(UserShare.User.phone?.ifEmpty { "" })
    }
    var emailText by remember {
        mutableStateOf(UserShare.User.email?.ifEmpty { "" })
    }

    if (showContactDialog.value) {
        Dialog(
            onDismissRequest = {
                showContactDialog.value = false
            }
        ) {
            Card (
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
               Column (
                   modifier = Modifier
                        .padding(12.dp)
               ) {
                   Text(
                       text = "Thông tin liên lạc",
                       fontWeight = FontWeight.Bold,
                       fontSize = 16.sp,
                       textAlign = TextAlign.Center,
                       modifier = Modifier
                           .fillMaxWidth()
                   )
                   YSpacer(10)
                   Divider(
                       modifier = Modifier
                           .fillMaxWidth(),
                       color = Grey100Color,
                       thickness = 1.dp
                   )
                   YSpacer(10)
                   OutlinedTextField(
                       value = nameText,
                       onValueChange = {
                           nameText = it
                       },
                       label = {
                           Text(
                               text = "Tên người đặt",
                               fontSize = 14.sp
                           )
                       },
                       modifier = Modifier
                           .height(60.dp)
                           .fillMaxWidth()
                   )
                   YSpacer(10)
                   OutlinedTextField(
                       enabled = false,
                       value = emailText.toString(),
                       onValueChange = {
                           emailText = it
                       },
                       label = {
                           Text(
                               text = "Email",
                               fontSize = 14.sp
                           )
                       },
                       modifier = Modifier
                           .height(60.dp)
                           .fillMaxWidth()
                   )
                   YSpacer(10)
                   OutlinedTextField(
                       value = phoneText.toString(),
                       onValueChange = {
                           phoneText = it
                       },
                       label = {
                           Text(
                               text = "Số điện thoại",
                               fontSize = 14.sp
                           )
                       },
                       modifier = Modifier
                           .height(60.dp)
                           .fillMaxWidth()
                   )
//                   YSpacer(10)
//                   OutlinedTextField(
//                       value = emailText,
//                       onValueChange = {
//                           emailText = it
//                       },
//                       label = {
//                           Text(
//                               text = "Email",
//                               fontSize = 14.sp
//                           )
//                       },
//                       modifier = Modifier
//                           .height(60.dp)
//                           .fillMaxWidth()
//                   )
                   YSpacer(10)
                   Row (
                       horizontalArrangement = Arrangement.SpaceEvenly
                   ) {
                       Button(
                           onClick = { showContactDialog.value = false },
                           modifier = Modifier

                               .height(65.dp)
                               .width(150.dp)
                               .padding(12.dp)
                               .border(2.dp, BorderStroke, RoundedCornerShape(12.dp)),
                           shape = RoundedCornerShape(12.dp),
                           colors = ButtonDefaults.buttonColors(Color.Transparent)
                       ) {
                           Text(
                               text = "Hủy",
                               fontSize = 14.sp,
                               color = PrimaryColor
                           )
                       }

                       Button(
                           onClick = { showContactDialog.value = false
                                        viewModel.updateProfile(phone=phoneText)
                                        viewModel.getProfile()
                                     },
                           modifier = Modifier
                               .height(65.dp)
                               .width(150.dp)
                               .padding(12.dp),
                           shape = RoundedCornerShape(12.dp),
                           colors = ButtonDefaults.buttonColors(OrangeColor)
                       ) {
                           Text(
                               text = "Tiếp tục",
                               fontSize = 14.sp,
                               color = Color.White
                           )
                       }
                   }
               }
            }
        }
    }
    Text(
        text = "Thông tin liên lạc",
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(12.dp)
    )
    Card (
        colors = CardDefaults.cardColors(
            containerColor = Grey50Color,
            contentColor = Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp, 0.dp, 12.dp, 0.dp)
            .clickable(
                onClick = {
                    showContactDialog.value = true
                }
            )
    ) {
        Column (
            modifier = Modifier
                .padding(12.dp)
        ) {
            Text(
                text = "Họ và tên: ${nameText.text}",
                fontSize = 16.sp
            )
            Text(
                text = "Email: ${UserShare.User.email}",
                fontSize = 14.sp,
                color = Grey500Color
            )
            Text(
                text = "Số điện thoại: ${phoneText}",
                fontSize = 14.sp,
                color = Grey500Color
            )
        }
    }
}
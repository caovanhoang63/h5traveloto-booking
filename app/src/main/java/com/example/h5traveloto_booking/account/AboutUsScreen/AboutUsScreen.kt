package com.example.h5traveloto_booking.account.AboutUsScreen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.ui_shared_components.PrimaryIconButton
import com.example.h5traveloto_booking.ui_shared_components.XSpacer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutUsScreen(navController: NavController) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            Row (
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(modifier = Modifier.fillMaxWidth()){
                    PrimaryIconButton(R.drawable.backarrow48, onClick = { navController.popBackStack()},"", modifier = Modifier )
                    XSpacer(60)
                    Text(
                        fontSize = 16.sp,
                        fontWeight =  FontWeight.Bold,
                        text = "Về chúng tôi",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Company Logo
                Image(
                    painter = painterResource(id = R.drawable.onlylogo), // Replace with your logo resource
                    contentDescription = "Company Logo",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Company Name
                Text(
                    text = "H5traveloto",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Company Description
                Text(
                    text = "Chúng tôi là công ty chuyên cung cấp các giải pháp đặt phòng hàng đầu cho mọi nhu cầu của bạn. Sứ mệnh của chúng tôi là mang đến dịch vụ và chất lượng xuất sắc cho khách hàng.",
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Contact Information
                Text(
                    text = "Liên Hệ Với Chúng Tôi",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Email: h5traveloto@company.com",
                    fontSize = 16.sp
                )
                Text(
                    text = "Phone: 0372527661",
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Social Media Links
                Text(
                    text = "Theo dõi chúng tôi",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ClickableText(
                        text = AnnotatedString("Facebook"),
                        onClick = { /* Handle Facebook click */
                            context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW, Uri.parse(
                                        "https://www.facebook.com/vanhoang.12032004"
                                    )
                                )
                            )
                        },
                        style = LocalTextStyle.current.copy(
                            color = Color.Blue,
                            fontSize = 16.sp
                        )
                    )
                    ClickableText(
                        text = AnnotatedString("Twitter"),
                        onClick = { /* Handle Twitter click */
                            context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW, Uri.parse(
                                        "https://www.facebook.com/vanhoang.12032004"
                                    )
                                )
                            )
                        },
                        style = LocalTextStyle.current.copy(
                            color = Color.Blue,
                            fontSize = 16.sp
                        )
                    )
                    ClickableText(
                        text = AnnotatedString("LinkedIn"),
                        onClick = { /* Handle LinkedIn click */
                            context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW, Uri.parse(
                                        "https://www.facebook.com/vanhoang.12032004"
                                    )
                                )
                            )
                        },
                        style = LocalTextStyle.current.copy(
                            color = Color.Blue,
                            fontSize = 16.sp
                        )
                    )
                }
            }
        }
    )
}

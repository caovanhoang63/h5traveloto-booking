package com.example.h5traveloto_booking.ui_shared_components

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.main.presentation.data.dto.Search.District
import com.example.h5traveloto_booking.theme.PrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuDown(items: List<District>, selectedItemIndex: Int, onItemSelected: (Int, District) -> Unit){
    var expanded by remember { mutableStateOf(false) }
    val angle: Float by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        animationSpec = tween(durationMillis = 300),
        label = "angle"
    )

    Box(
        modifier = Modifier
            .clickable (
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ){ expanded = true }
    ) {
        Column() {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(25.dp)
            ) {
                Image(painterResource(id = R.drawable.location), contentDescription = "location", modifier = Modifier.size(20.dp))
                Text(
                    text = items[selectedItemIndex].name,
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(10.dp,0.dp))
                Image(painterResource(id = R.drawable.arrowdown),
                    contentDescription = "down",
                    modifier = Modifier
                        .size(20.dp)
                        .graphicsLayer(
                            rotationZ = -angle,
                            transformOrigin = TransformOrigin(0.5f, 0.5f)

                        )
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(Color.White)
                    .height(200.dp)
                    .width(150.dp)

            ) {
                items.forEachIndexed { index, s ->
                    if(index == 0){
                        DropdownMenuItem(
                            onClick = {
                                onItemSelected(index, s)
                                expanded = false
                            },
                            text = {
                                Text(text = s.name)
                            },
                            modifier = Modifier.padding(0.dp)
                                .height(32.dp)
                                .offset((-8).dp,0.dp),
                            leadingIcon = {
                                //Icon(painterResource(id = R.drawable.targeticon), contentDescription = "location", modifier = Modifier.size(22.dp).offset(8.dp,0.dp), tint = PrimaryColor)
                                Icon(Icons.Default.MyLocation, contentDescription = "location", modifier = Modifier.size(22.dp).offset(8.dp,0.dp), tint = PrimaryColor)
                            }
                        )
                    }else {
                        DropdownMenuItem(
                            onClick = {
                                onItemSelected(index, s)
                                expanded = false
                            },
                            text = {
                                Text(text = s.name)
                            },
                            modifier = Modifier.padding(start = 8.dp)
                                .height(32.dp),
                        )
                    }
                }
            }
        }
    }
}


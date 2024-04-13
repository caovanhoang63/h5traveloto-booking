@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.h5traveloto_booking.account.personal_information.UpdateInformation

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.theme.ScreenBackGround
import com.example.h5traveloto_booking.ui_shared_components.BoldText
import com.example.h5traveloto_booking.ui_shared_components.PrimaryIconButton
import com.example.h5traveloto_booking.ui_shared_components.XSpacer
import com.example.h5traveloto_booking.ui_shared_components.YSpacer
import com.example.h5traveloto_booking.util.ui_shared_components.TextBox
import java.time.LocalDate
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateInformationScreen(navController: NavController) {
    var fullName by rememberSaveable { mutableStateOf("Hoang Huy") }
    var gender by remember { mutableStateOf("Male") }
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
                XSpacer(60)
                BoldText(text = "Update Information",
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
                Text(text = "For your profile name, we will shorten your full name. Get the chance to receive special offers by filling out your birthdate.",
                    modifier = Modifier.padding(vertical = 16.dp,horizontal = 20.dp))

                TextBox(label = "Full Name", value = fullName, onValueChange = { fullName = it },modifier = Modifier.padding(vertical = 16.dp, horizontal = 20.dp).fillMaxWidth(), placeholder = "")

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,) {

                    MyDropdownMenu(
                        label = "Gender",
                        items = listOf("Male","Female","Other"),
                        selectedItem = gender,
                        onItemSelected = { gender = it },
                    )
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDropdownMenu(
    label: String,
    items: List<String>,
    selectedItem:String,
    onItemSelected: (String) -> Unit
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
   var searchText by remember {
       mutableStateOf(selectedItem)
   }

    Box(modifier = Modifier.wrapContentSize(),
        contentAlignment = Alignment.Center)
    {
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it }
        ) {
            OutlinedTextField(
                //moi doi value
                value = searchText,
                // moi them trong on valuechange
                onValueChange = {searchText=it},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                //chinh mau
                //colors = ExposedDropdownMenuDefaults.textFieldColors(),
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                modifier = Modifier.menuAnchor()
                    .fillMaxWidth(),
                label = {Text(label)},
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            )
            {
                items.forEach { item ->
                    DropdownMenuItem(onClick = {
                        onItemSelected(item)
                        isExpanded = false
                        searchText = item
                    },
                        text = {
                            Text(text = item,)
                        })

                }
            }
        }
    }
}

/*
@ExperimentalMaterial3Api
@Composable
fun DatePickerDialog(
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    dismissButton: (@Composable () -> Unit)? = null,
    shape: Shape = DatePickerDefaults.shape,
    tonalElevation: Dp = DatePickerDefaults.TonalElevation,
    colors: DatePickerColors = DatePickerDefaults.colors(),
    properties: DialogProperties = DialogProperties(usePlatformDefaultWidth = false),
    content: @Composable ColumnScope.() -> Unit
): Unit
*/

@Composable
fun DatePickerComponent(selectedDate: MutableState<LocalDate>) {
    var expanded by remember { mutableStateOf(false) }

    // Khi nhấn vào văn bản, hiển thị hoặc ẩn DatePickerDialog
    Text(
        text = selectedDate.value.toString(),
        modifier = Modifier.clickable { expanded = true }
    )

    // DatePickerDialog chỉ hiển thị khi expanded là true
    if (expanded) {
    /*    DatePickerDialog()
        (
            onDismissRequest = { expanded = false }, // Đóng DatePickerDialog khi người dùng bấm ra ngoài
            selectedDate = selectedDate.value, // Ngày được chọn
            onDateChange = { selectedDate.value = it } // Khi ngày được chọn thay đổi, cập nhật giá trị trong selectedDate
        )*/
    }
}



@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.h5traveloto_booking.account.personal_information.UpdateInformation

import android.app.DatePickerDialog
import android.util.Log
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.h5traveloto_booking.R
import com.example.h5traveloto_booking.theme.ScreenBackGround
import com.example.h5traveloto_booking.ui_shared_components.*
import java.time.LocalDate
import com.example.h5traveloto_booking.util.Result
import com.example.h5traveloto_booking.util.ui_shared_components.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateInformationScreen(navController: NavController,
                            viewModel: UpdateInformationViewModel= hiltViewModel()
)
{
    LaunchedEffect(Unit) {
        viewModel.getProfile()
    }
    val ProfileResponse = viewModel.GetProfileResponse.collectAsState().value

    when(ProfileResponse){
        is Result.Idle -> {}
        is Result.Loading ->{
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator() // Hiển thị thanh tiến trình tải
            }
        }
        is Result.Success ->{
            var date = ProfileResponse.data.data.dateOfBirth
            if(date!=null) {
                var arr = date.split("-")
                date = arr[0]+arr[1]+arr[2]
            }
            var fullName by rememberSaveable { mutableStateOf(ProfileResponse.data.data.lastName + " " + ProfileResponse.data.data.firstName) }
            var gender by rememberSaveable { mutableStateOf(translateGenderToVietnamese(ProfileResponse.data.data.gender)) }
            var birthDate by rememberSaveable { mutableStateOf(date)}
            var phoneNumber by rememberSaveable { mutableStateOf(ProfileResponse.data.data.phone) }
            var email by rememberSaveable { mutableStateOf(ProfileResponse.data.data.email) }
            var avatar by rememberSaveable { mutableStateOf(ProfileResponse.data.data.avatar)}
            var city by rememberSaveable { mutableStateOf("Not Set") }
            var showDialog by remember { mutableStateOf(false) }
            var showDialog2 by remember { mutableStateOf(true) }
            var UpdateProfile = viewModel.UpdateProfileResponse.collectAsState().value
            if (showDialog && showDialog2) {
                AlertDialog(
                    onDismissRequest = {
                        // Đóng hộp thoại khi người dùng chọn bất kỳ nơi nào trên màn hình
                        showDialog2 = false
                    },
                    title = {
                        Text(text = "Thành công")
                    },
                    text = {
                        Text(text = "Cập nhật thành công")
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                showDialog2 = false
                                navController.navigateUp()
                            }
                        ) {
                            Text(text = "")
                        }
                    }
                )
            }
            when(UpdateProfile){
                is Result.Idle->{}
                is Result.Loading->{
                    CircleLoading()
                }
                is Result.Success->{
                    showDialog=true;
                }
                is Result.Error->{}
                else -> Unit
            }
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .background(ScreenBackGround),
                topBar = {
                    Row (
                        Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        PrimaryIconButton(R.drawable.backarrow48, onClick = {navController.navigateUp() /*navController.popBackStack*/},"", modifier = Modifier )
                        XSpacer(60)
                        BoldText(text = "Cập nhật thông tin",
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
                        Text(text = "Đối với tên hồ sơ của bạn, chúng tôi sẽ rút ngắn tên đầy đủ của bạn. Có cơ hội nhận được ưu đãi đặc biệt bằng cách điền ngày sinh của bạn.",
                            modifier = Modifier.padding(vertical = 16.dp,horizontal = 20.dp))

                        TextBoxSingle(label = "Họ tên", value = fullName, onValueChange = { fullName = it },modifier = Modifier
                            .padding(vertical = 16.dp, horizontal = 20.dp)
                            .fillMaxWidth(), placeholder = "")

                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,) {

                            MyDropdownMenu(
                                label = "Giới tính",
                                items = listOf("Nam","Nữ","Khác"),
                                selectedItem = gender,
                                onItemSelected = { gender = it },
                            )
                        }
                        TextBoxSingleDate(label = "Ngày sinh", value = if(birthDate.isNullOrBlank()) "" else birthDate.toString(), onValueChange = {if (it.length <= 8) birthDate = it },modifier = Modifier
                            .padding(vertical = 16.dp, horizontal = 20.dp)
                            .fillMaxWidth(), placeholder = "")
                        //YSpacer(16)
                        TextBoxSingle(label = "Số điện thoại", value = phoneNumber, onValueChange = { phoneNumber = it },modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth(), placeholder = "")
                        YSpacer(16)
                        DisableTextBoxSingle(label = "Email", value = email,onValueChange = {},modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth(), placeholder = "")
                        YSpacer(16)
                        TextBoxSingle(label = "Thành phố đang ở", value = city, onValueChange = { city = it },modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth(), placeholder = "")
                        YSpacer(32)
                        PrimaryButton(onClick = {val arr = fullName.split(" ");
                            viewModel.updateProfile(lastName =  arr.dropLast(1).joinToString(" "), firstName = arr.last(),phone=phoneNumber,gender = translateGendertoEnglish(gender), birthDateOfBirth = birthDate, avatar = avatar) ;
                            navController.navigateUp()},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 0.dp, horizontal = 20.dp),text = "Hoàn thành",
                            )

                    }
                }
            )
        }
        is Result.Error ->{}

    }

}

fun translateGenderToVietnamese(gender: String?): String {
    return when (gender) {
        "male" -> "Nam"
        "female" -> "Nữ"
        "other" -> "Khác"
        else -> gender.toString() // Nếu không phải là male, female hoặc other, trả về giá trị ban đầu
    }
}
fun translateGendertoEnglish(gender: String?): String {
    return when(gender){
        "Nam" -> "male"
        "Nữ" -> "female"
        "Khác" ->"other"
        else -> gender.toString()
    }
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
                // moi them trong on
                onValueChange = {searchText=it},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                //chinh mau
                //colors = ExposedDropdownMenuDefaults.textFieldColors(),
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                modifier = Modifier
                    .menuAnchor()
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

/*
@Composable
fun DatePickerComponent(selectedDate: MutableState<LocalDate>) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
    // Khi nhấn vào văn bản, hiển thị hoặc ẩn DatePickerDialog
    Text(
        text = selectedDate.value.toString(),
        modifier = Modifier.clickable { expanded = true }
    )

    // DatePickerDialog chỉ hiển thị khi expanded là true
    if (expanded) {
    }
    }
}
*/

class DateTransformation() : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return dateFilter(text)
    }
}

fun dateFilter(text: AnnotatedString): TransformedText {

    val trimmed = if (text.text.length >= 8) text.text.substring(0..7) else text.text
    var out = ""
    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i % 2 == 1 && i < 4) out += "/"
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 1) return offset
            if (offset <= 3) return offset +1
            if (offset <= 8) return offset +2
            return 10
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <=2) return offset
            if (offset <=5) return offset -1
            if (offset <=10) return offset -2
            return 8
        }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}

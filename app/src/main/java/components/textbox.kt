package components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextBox(modifier: Modifier, lable : String , placeHolder : String ) {
    var text by remember { mutableStateOf("") }
    Text(text = lable, fontSize = 12.sp)
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(placeHolder) },
        modifier = modifier
    )

}
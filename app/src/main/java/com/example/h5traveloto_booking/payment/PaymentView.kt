import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.h5traveloto_booking.payment.VNPayLibrary
import com.vnpay.authentication.VNP_AuthenticationActivity
import java.security.MessageDigest
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

/*@Composable
fun CreateScreen(navController: NavHostController) {
    var id by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text("ID") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { createPayment(id, amount, navController) }) {
            Text("Create Payment")
        }
    }
}*/

fun createPayment(id: String, amount: String, navController: NavController, context: Context) :String {
  /*  val vnp_TmnCode = "CXE5IZGS" // Mã website tại VNPAY
    val vnp_HashSecret = "2MMW18YEFAIAATHVRLN0JI06HZ82C7FZ" // Chuỗi bí mật
    val vnp_Url = "http://sandbox.vnpayment.vn/paymentv2/vpcpay.html"
    val vnp_Returnurl = "http://localhost:8000/return-vnpay"
    val vnp_TxnRef = "166117" // Mã đơn hàng
    val vnp_OrderInfo = "Thanh+toan"
    val vnp_OrderType = "billpayment"
    val vnp_Amount = amount.toDouble() * 100 // Đổi số tiền thành đơn vị VNĐ
    val inputData = mapOf(
        "vnp_Version" to "2.1.0",
        "vnp_TmnCode" to vnp_TmnCode,
        "vnp_Amount" to vnp_Amount,
        "vnp_Command" to "pay",
        "vnp_CreateDate" to Date().toString(),
        "vnp_CurrCode" to "VND",
        "vnp_IpAddr" to "127.0.0.1",
        "vnp_Locale" to "vn",
        "vnp_OrderInfo" to vnp_OrderInfo,
        "vnp_OrderType" to vnp_OrderType,
        "vnp_ReturnUrl" to vnp_Returnurl,
        "vnp_TxnRef" to vnp_TxnRef
    )

    // Sort the input data by keys
    val sortedInputData = inputData.toSortedMap(compareBy { it })

    // Convert the sorted input data into a query string
    val queryString = sortedInputData
        .map { "${it.key}=${it.value}" }
        .joinToString("&")

    val hashData = sortedInputData.map { "${it.key}=${it.value}" }.joinToString("&")

    val vnpSecureHash = hashData.hmacSHA512(vnp_HashSecret)

    val vnpUrlWithHash = "$vnp_Url?$queryString&vnp_SecureHashType=SHA256&vnp_SecureHash=$vnpSecureHash"

    //navController.navigate(vnpUrlWithHash)
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(vnpUrlWithHash)
    }*/
    val vnp_TmnCode = "RKNI1E2L"/*"CXE5IZGS"*/ // bí mật
    val vnp_HashSecret = "PTWJFDXKIJAKRGAYRROTDGQCEIHVXPSX" //"2MMW18YEFAIAATHVRLN0JI06HZ82C7FZ" // Chuỗi bí mật
    val vnp_Url = "http://sandbox.vnpayment.vn/paymentv2/vpcpay.html"
    val vnp_Returnurl = "https://sandbox.vnpayment.vn/merchant_webapi/merchant.html"

    val order = OrderInfo(
        orderId = System.currentTimeMillis(),
        amount = 12300000,
        createdDate = Date(),
        status = "0"
    )
    // Giả lập URL và hash secret của VNPAY

    val vnpay = VNPayLibrary()
    vnpay.addRequestData("vnp_Version", "2.1.0")
    vnpay.addRequestData("vnp_Command", "pay")
    vnpay.addRequestData("vnp_TmnCode", vnp_TmnCode)
    vnpay.addRequestData("vnp_Amount", (order.amount * 100).toString())

    val bankCode = "VNPAYQR" // Giả sử đã chọn VNPAYQR, bạn cần thay đổi logic để lấy từ UI thực tế
   // vnpay.addRequestData("vnp_BankCode", bankCode)

    val dateFormat = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault())
    vnpay.addRequestData("vnp_CreateDate", dateFormat.format(order.createdDate))
    vnpay.addRequestData("vnp_CurrCode", "VND")
    vnpay.addRequestData("vnp_IpAddr", "127.0.0.1") // Giả lập IP, thực tế lấy từ Utils.GetIpAddress()

    val locale = "vn" // Giả sử đã chọn locale VN, bạn cần thay đổi logic để lấy từ UI thực tế
    vnpay.addRequestData("vnp_Locale", locale)
    vnpay.addRequestData("vnp_OrderInfo", "Thanh toan don hang: ${order.orderId}")
    vnpay.addRequestData("vnp_OrderType", "other")
    vnpay.addRequestData("vnp_ReturnUrl", vnp_Returnurl)
    vnpay.addRequestData("vnp_TxnRef", order.orderId.toString())

    val paymentUrl = vnpay.createRequestUrl(vnp_Url, vnp_HashSecret)
    println("VNPAY URL: $paymentUrl")
    Log.d("hehe",paymentUrl.toString())
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(paymentUrl)
    }
    return paymentUrl
    //context.startActivity(intent)
}
@Composable
fun WebViewScreen(url: String) {
    AndroidView(factory = { context ->
        WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            webChromeClient = WebChromeClient()
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                   view?.loadUrl(request?.url.toString())
                    return super.shouldOverrideUrlLoading(view, request)
                }
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    // Xử lý khi trang tải xong
                }
                override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
                    super.onReceivedError(view, errorCode, description, failingUrl)
                    // Xử lý lỗi
                }

            }
            loadUrl(url)
        }
    }, update = { webView ->
        webView.loadUrl(url)
    },
        modifier = Modifier.fillMaxSize()
    )
}

/*fun OpenUrlScreen(vnpUrlWithHash: String,context: Context) {
   // val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Open URL",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.clickable {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(vnpUrlWithHash)
                }
                context.startActivity(intent)
            }
        )
    }
}*/
fun String.sha256(secret: String): String {
    return MessageDigest.getInstance("SHA-256")
        .digest("$this$secret".toByteArray())
        .joinToString("") { "%02x".format(it) }
}
fun String.hmacSHA512(secret: String): String {
    val hmac = Mac.getInstance("HmacSHA512")
    val keySpec = SecretKeySpec(secret.toByteArray(), "HmacSHA512")
    hmac.init(keySpec)
    val result = hmac.doFinal(this.toByteArray())
    return result.joinToString("") { "%02x".format(it) }
}
fun ReturnScreen(navController: NavHostController) {
    val request = navController.previousBackStackEntry?.arguments?.getString("request")
    val url = request?.let { extractUrlFromRequest(it) } ?: "/"

    val responseCode = navController.previousBackStackEntry?.arguments?.getString("vnp_ResponseCode")

    if (responseCode == "00") {
        // Handle success case
        // Call your `apSer.thanhtoanonline(session('cost_id'))` method here
        // For example:
        // apSer.thanhtoanonline(session("cost_id"))

        // Navigate back to previous screen with success message
        navController.popBackStack()
        navController.navigate(url) {
            launchSingleTop = true
        }
        // Show success message
        // You can use a Snackbar or Toast to display success message
        // For example:
        // SnackbarHost(...)
    } else {
        // Navigate back to previous screen with error message
        navController.popBackStack()
        navController.navigate(url) {
            launchSingleTop = true
        }
        // Show error message
        // You can use a Snackbar or Toast to display error message
        // For example:
        // SnackbarHost(...)
    }
}
fun extractUrlFromRequest(request: String): String {
    // Logic to extract URL from request
    return "/"
}
data class OrderInfo(
    var orderId: Long,
    var amount: Long,
    var orderDesc: String? = null,
    var createdDate: Date,
    var status: String,
    var paymentTranId: Long? = null,
    var bankCode: String? = null,
    var payStatus: String? = null
)
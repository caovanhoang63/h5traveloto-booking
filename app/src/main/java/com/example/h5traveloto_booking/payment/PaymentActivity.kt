package com.example.h5traveloto_booking.payment

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.Nullable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.h5traveloto_booking.theme.H5travelotobookingTheme
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.*
import java.util.concurrent.TimeUnit

class PaymentActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            H5travelotobookingTheme {
                WebViewScreen2(url = "https://sandbox.vnpayment.vn/testsdk/", tmnCode = "CXE5IZGS", scheme = "resultactivity", isSandbox = false)
            }
        }
    }
}

@Composable
fun WebViewScreen2(url: String, tmnCode: String, scheme: String, isSandbox: Boolean) {
    val context = LocalContext.current

    var progressDialog by remember { mutableStateOf<ProgressDialog?>(null) }
    var webViewState by remember { mutableStateOf<WebView?>(null) }

    DisposableEffect(Unit) {
        onDispose {
            progressDialog?.dismiss()
        }
    }

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.builtInZoomControls = true
                settings.displayZoomControls = false

                webChromeClient = WebChromeClient()
                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView, url: String) {
                        super.onPageFinished(view, url)
                    }

                    override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                    }

                    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                        request?.url.let {
                            Log.d("heheh",url.toString())
                            when {
                                it.toString().startsWith("intent://") -> {
                                    try {
                                        val intent = Intent.parseUri(it.toString(), Intent.URI_INTENT_SCHEME)
                                        Log.d("heheh", it.toString().startsWith("intent://") .toString())
                                        if (intent != null) {
                                            context.startActivity(intent)
                                            finishActivity(intent)
                                            return true
                                        } else return false
                                    } catch (e: Exception) {
                                        e.printStackTrace()
                                    }
                                }
                                it.toString().contains("cancel.sdk.merchantbackapp") -> {
                                    finishActivity("WebBackAction")
                                    Log.d("heheh", it.toString().contains("cancel.sdk.merchantbackapp").toString())

                                    return true
                                }
                                it.toString().contains("fail.sdk.merchantbackapp") -> {
                                    finishActivity("FaildBackAction")
                                    Log.d("heheh",it.toString().contains("fail.sdk.merchantbackapp").toString())

                                    return true
                                }
                                it.toString().contains("success.sdk.merchantbackapp") -> {
                                    finishActivity("SuccessBackAction")
                                    Log.d("heheh",it.toString().contains("success.sdk.merchantbackapp").toString())

                                    return true
                                }
                                it.toString().startsWith("tel:") -> {
                                    context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse(it.toString())))
                                    Log.d("heheh",it.toString().startsWith("tel:").toString())
                                    return true
                                }
                                it.toString().startsWith("mailto:") -> {
                                    context.startActivity(Intent(Intent.ACTION_SENDTO, Uri.parse(it.toString())))
                                    Log.d("heheh",it.toString().startsWith("mailto:").toString())

                                    return true
                                }
                                !it.toString().startsWith("http") -> {
                                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it.toString())))
                                    finishActivity(it.toString())
                                    Log.d("heheh",it.toString().startsWith("http").toString())
                                    return true
                                }
                                else -> view?.loadUrl(request?.url.toString())
                            }
                        }
                        view?.loadUrl(request?.url.toString())
                        return super.shouldOverrideUrlLoading(view, request)
                    }
                }

                loadUrl(url)
                webViewState = this
            }
        }
    )

    LaunchedEffect(webViewState) {
        if (webViewState != null) {
            if (progressDialog == null || progressDialog?.isShowing == false) {
                progressDialog = ProgressDialog(context).apply {
                    setMessage("Xin đợi trong giây lát...")
                    show()
                }
            }

            try {
                val client = OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(80, TimeUnit.SECONDS)
                    .readTimeout(80, TimeUnit.SECONDS)
                    .build()
                val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
                val body = RequestBody.create(mediaType, "tmn_code=$tmnCode&os_type=ANDROID")
                val request = Request.Builder()
                    .url(if (isSandbox) "https://sandbox.vnpayment.vn/qrpayauth/api/sdk/get_qrpay_support" else "https://pay.vnpay.vn/qrpayauth/api/sdk/get_qrpay_support/")
                    .post(body)
                    .build()
                val response = client.newCall(request).execute()
                response.body?.let { responseBody ->
                    val responseString = responseBody.string()
                    Log.d("PaymentActivity", responseString)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                progressDialog?.dismiss()
            }
        }
    }
}

private fun finishActivity(action: String) {
    // Handle the SDK completed callback action
    Log.wtf("PaymentActivity", "action: $action")
}

private fun finishActivity(intent: Intent) {
    // Handle activity finishing logic
    Log.wtf("PaymentActivity", "Intent action: ${intent.action}")
}

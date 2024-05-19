package com.example.h5traveloto_booking.payment

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import android.annotation.TargetApi
import android.app.ProgressDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.webkit.*
import android.webkit.WebSettings.LayoutAlgorithm
import android.webkit.WebSettings.ZoomDensity
import android.widget.LinearLayout
import android.widget.Toast
import android.widget.ZoomButtonsController
import androidx.activity.ComponentActivity
import androidx.annotation.Nullable
import com.example.h5traveloto_booking.payment.VNP_AuthenticationActivity.Companion.setSdkCompletedCallback
import com.google.gson.GsonBuilder
import com.google.gson.stream.JsonReader
import com.vnpay.merchant.R.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.InputStreamReader
import java.io.StringReader
import java.lang.reflect.InvocationTargetException
import java.util.concurrent.TimeUnit
import java.util.zip.GZIPInputStream
import javax.inject.Inject

class VNP_AuthenticationActivity () : ComponentActivity() {
    private var wvContent: WebView? = null
    private var url: String? = ""
    private var scheme: String? = ""
    private var tmn_code: String? = ""
    private var is_sandbox = false
    private var entity_Response: Array<VNP_BankEntity>? = null
    private var dialog: ProgressDialog? = null
    private var llLoading: LinearLayout? = null
    var Url: String = "https://pay.vnpay.vn/qrpayauth/api/sdk/get_qrpay_support/"
    var Url_sandbox: String = "https://sandbox.vnpayment.vn/qrpayauth/api/sdk/get_qrpay_support"
    var Host: String = "pay.vnpay.vn"
    var Host_sandbox: String = "sandbox.vnpayment.vn"

    override fun onBackPressed() {
        super.onBackPressed()
        if (VNP_AuthenticationActivity.Companion.sdkCompletedCallback != null) {
            VNP_AuthenticationActivity.Companion.sdkCompletedCallback!!.sdkAction("AppBackAction")
        }

        this.finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(layout.vnp_activity_webview)
        this.llLoading = this.findViewById(id.llLoading) as LinearLayout?

        try {
            val bundle = this.getIntent().getExtras()
            if (bundle != null) {
                if (bundle.containsKey("url")) {
                    this.url = bundle.getString("url")
                    if (TextUtils.isEmpty(this.url)) {
                        Toast.makeText(this, "Thiếu tham số", Toast.LENGTH_LONG).show()
                        this.setResult(-1)
                        this.finish()
                    }

                    Log.wtf("SDK", this.url)
                } else {
                    Toast.makeText(this, "Thiếu tham số", Toast.LENGTH_LONG).show()
                    this.setResult(-1)
                    this.finish()
                }

                if (bundle.containsKey("scheme")) {
                    this.scheme = bundle.getString("scheme")
                    if (TextUtils.isEmpty(this.scheme)) {
                        Toast.makeText(this, "Thiếu tham số", Toast.LENGTH_LONG).show()
                        this.setResult(-1)
                        this.finish()
                    } else {
                        if (!scheme!!.endsWith("://")) {
                            this.scheme = this.scheme + "://sdk"
                        }

                        Log.wtf("SDK", this.scheme)
                    }
                }

                if (bundle.containsKey("tmn_code")) {
                    this.tmn_code = bundle.getString("tmn_code")
                    if (TextUtils.isEmpty(this.tmn_code)) {
                        Toast.makeText(this, "Thiếu tham số", Toast.LENGTH_LONG).show()
                        this.setResult(-1)
                        this.finish()
                    }

                    Log.wtf("SDK", this.tmn_code)
                } else {
                    Toast.makeText(this, "Thiếu tham số", Toast.LENGTH_LONG).show()
                    this.setResult(-1)
                    this.finish()
                }

                if (bundle.containsKey("is_sandbox")) {
                    this.is_sandbox = bundle.getBoolean("is_sandbox")
                    Log.wtf("SDK is_sandbox", if (this.is_sandbox) "true" else "false")
                }

                this.wvContent = this.findViewById(id.wview1) as WebView?
                wvContent!!.settings.javaScriptEnabled = true
                wvContent!!.settings.layoutAlgorithm = LayoutAlgorithm.SINGLE_COLUMN
                wvContent!!.scrollBarStyle = View.SCROLLBARS_OUTSIDE_OVERLAY
                wvContent!!.setInitialScale(1)
                wvContent!!.settings.defaultZoom = ZoomDensity.FAR
                wvContent!!.settings.loadWithOverviewMode = true
                wvContent!!.settings.useWideViewPort = true
                wvContent!!.settings.setSupportZoom(true)
                wvContent!!.settings.domStorageEnabled = true
                wvContent!!.settings.builtInZoomControls = true
                if (Build.VERSION.SDK_INT >= 11) {
                    Runnable {
                        wvContent!!.settings.displayZoomControls =
                            false
                    }.run()
                } else {
                    try {
                        val zoom_controll =
                            wvContent!!.javaClass.getMethod("getZoomButtonsController")
                                .invoke(this.wvContent) as ZoomButtonsController
                        zoom_controll.container.visibility = View.GONE
                    } catch (var5: IllegalAccessException) {
                        val e = var5
                        e.printStackTrace()
                    } catch (var6: InvocationTargetException) {
                        val e = var6
                        e.printStackTrace()
                    } catch (var7: NoSuchMethodException) {
                        val e = var7
                        e.printStackTrace()
                    }
                }

                wvContent!!.webViewClient = myWebClient()
                wvContent!!.loadUrl(url!!)
            }

            try {
                if (this.dialog == null || !dialog!!.isShowing) {
                    this.dialog = ProgressDialog(this)
                }

                dialog!!.setMessage(this.getString(string.vnp_lable_XinDoiTrongGiayLat))
                dialog!!.show()
            } catch (var8: Exception) {
                val e = var8
                Log.wtf("SDK", e.message)
            }

            Thread {
                try {
                    val client =
                        OkHttpClient().newBuilder().connectTimeout(30L, TimeUnit.SECONDS)
                            .writeTimeout(80L, TimeUnit.SECONDS)
                            .readTimeout(80L, TimeUnit.SECONDS).build()
                    val mediaType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
                    val body = RequestBody.create(
                        mediaType,
                        "tmn_code=" + this@VNP_AuthenticationActivity.tmn_code + "&os_type=ANDROID"
                    )
                    val request: Request =
                        Request.Builder().url(if (this@VNP_AuthenticationActivity.is_sandbox) Url_sandbox else Url)
                            .post(body).addHeader("Content-Type", "application/x-www-form-urlencoded")
                            .addHeader("cache-control", "no-cache,no-cache").addHeader("Accept", "*/*").addHeader(
                                "Host",
                                if (this@VNP_AuthenticationActivity.is_sandbox) Host_sandbox else Host
                            ).addHeader("accept-encoding", "gzip, deflate").addHeader("content-length", "33")
                            .addHeader("Connection", "keep-alive").build()
                    val response = client.newCall(request).execute()
                    val gson = GsonBuilder().disableHtmlEscaping().create()
                    val stringReader = StringReader(response.body!!.string())
                    val reader = JsonReader(stringReader)
                    this@VNP_AuthenticationActivity.entity_Response = gson.fromJson<Any>(
                        reader,
                        Array<VNP_BankEntity>::class.java
                    ) as Array<VNP_BankEntity>
                    Log.wtf("Tag", "Success")
                    if (this@VNP_AuthenticationActivity.dialog != null) {
                        if (dialog!!.isShowing) {
                            dialog!!.dismiss()
                        }

                        this@VNP_AuthenticationActivity.dialog = null
                    }
                } catch (var9: Exception) {
                    val e = var9
                    e.printStackTrace()
                    Log.wtf("SDK", e.message)
                    if (this@VNP_AuthenticationActivity.dialog != null) {
                        if (dialog!!.isShowing) {
                            dialog!!.dismiss()
                        }

                        this@VNP_AuthenticationActivity.dialog = null
                    }
                }
            }.start()
        } catch (var9: Exception) {
            val e = var9
            e.printStackTrace()
            Log.wtf("SDK", e.message)
            if (this.dialog != null) {
                if (dialog!!.isShowing) {
                    dialog!!.dismiss()
                }

                this.dialog = null
            }
        }
    }

    private fun Unzip(inputByteArr: ByteArray): String {
        try {
            val bais = ByteArrayInputStream(inputByteArr)
            val gzis = GZIPInputStream(bais)
            val reader = InputStreamReader(gzis)
            val `in` = BufferedReader(reader)
            val sb = StringBuilder()

            var line: String?
            while ((`in`.readLine().also { line = it }) != null) {
                sb.append(line)
            }

            return sb.toString()
        } catch (var8: Exception) {
            return String(inputByteArr)
        }
    }

    internal inner class myWebClient : WebViewClient() {
        private val PAGE_REDIRECTED = 2
        private val PAGE_STARTED = 1

        override fun onPageFinished(view: WebView, url: String) {
            llLoading!!.visibility = View.GONE
            super.onPageFinished(view, url)
        }

   /*     override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
            llLoading!!.visibility = View.VISIBLE
             super.onPageStarted(view, url, favicon)
        }*/

        @TargetApi(23)
        override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
            super.onReceivedError(view, request, error)
            llLoading!!.visibility = View.GONE
        }

        override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
            super.onReceivedError(view, errorCode, description, failingUrl)
            llLoading!!.visibility = View.GONE
        }

        override fun onReceivedHttpError(
            view: WebView,
            request: WebResourceRequest,
            errorResponse: WebResourceResponse
        ) {
            llLoading!!.visibility = View.GONE
            super.onReceivedHttpError(view, request, errorResponse)
        }

        override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
            llLoading!!.visibility = View.GONE
            super.onReceivedSslError(view, handler, error)
        }

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            llLoading!!.visibility = View.VISIBLE
            val intent: Intent?
            var uri: Uri
            if (!TextUtils.isEmpty(url) && url.startsWith("intent://")) {
                try {
                    uri = Uri.parse(url)
                    if (!TextUtils.isEmpty(this@VNP_AuthenticationActivity.scheme)) {
                        uri = this@VNP_AuthenticationActivity.scheme?.let {
                            VNP_AuthenticationActivity.Companion.addUriParameter(
                                uri, "newcallbackurl",
                                it
                            )
                        }!!
                    }

                    Intent()
                    intent = Intent.parseUri(uri.toString(), Intent.URI_INTENT_SCHEME)
                    if (intent != null) {
                        var authenticate = false

                        try {
                            if (this@VNP_AuthenticationActivity.entity_Response == null) {
                                authenticate = true
                            } else {
                                val var17 = this@VNP_AuthenticationActivity.entity_Response
                                val var7 = var17!!.size

                                for (var8 in 0 until var7) {
                                    val bankEntity = var17[var8]
                                    if (bankEntity.andr_scheme == intent.scheme) {
                                        authenticate = true
                                        break
                                    }
                                }
                            }
                        } catch (var12: Exception) {
                            val e = var12
                            e.printStackTrace()
                            Log.wtf("SDK", e.message)
                            authenticate = true
                        }

                        if (authenticate) {
                            view.stopLoading()

                            try {
                                this@VNP_AuthenticationActivity.startActivity(intent)
                                if (VNP_AuthenticationActivity.Companion.sdkCompletedCallback != null) {
                                    VNP_AuthenticationActivity.Companion.sdkCompletedCallback!!.sdkAction("CallMobileBankingApp")
                                }

                                this@VNP_AuthenticationActivity.finish()
                            } catch (var11: Exception) {
                                val appPackageName = intent.getPackage()

                                try {
                                    this@VNP_AuthenticationActivity.startActivity(
                                        Intent(
                                            "android.intent.action.VIEW", Uri.parse(
                                                "market://details?id=$appPackageName"
                                            )
                                        )
                                    )
                                } catch (var10: ActivityNotFoundException) {
                                    this@VNP_AuthenticationActivity.startActivity(
                                        Intent(
                                            "android.intent.action.VIEW", Uri.parse(
                                                "https://play.google.com/store/apps/details?id=$appPackageName"
                                            )
                                        )
                                    )
                                }
                            }
                        } else {
                            Toast.makeText(this@VNP_AuthenticationActivity, "This bank is not support", Toast.LENGTH_LONG).show()
                        }

                        return true
                    }
                } catch (var13: Exception) {
                    var13.printStackTrace()
                    Log.wtf("SDK", var13)
                }

                return true
            } else if (url.contains("cancel.sdk.merchantbackapp")) {
                if (VNP_AuthenticationActivity.Companion.sdkCompletedCallback != null) {
                    VNP_AuthenticationActivity.Companion.sdkCompletedCallback!!.sdkAction("WebBackAction")
                }

                this@VNP_AuthenticationActivity.finish()
                return true
            } else if (url.contains("fail.sdk.merchantbackapp")) {
                if (VNP_AuthenticationActivity.Companion.sdkCompletedCallback != null) {
                    VNP_AuthenticationActivity.Companion.sdkCompletedCallback!!.sdkAction("FaildBackAction")
                }

                this@VNP_AuthenticationActivity.finish()
                return true
            } else if (url.contains("success.sdk.merchantbackapp")) {
                if (VNP_AuthenticationActivity.Companion.sdkCompletedCallback != null) {
                    VNP_AuthenticationActivity.Companion.sdkCompletedCallback!!.sdkAction("SuccessBackAction")
                }

                this@VNP_AuthenticationActivity.finish()
                return true
            } else if (url.startsWith("tel:")) {
                intent = Intent("android.intent.action.DIAL", Uri.parse(url))
                this@VNP_AuthenticationActivity.startActivity(intent)
                return true
            } else if (url.startsWith("mailto:")) {
                val i = Intent("android.intent.action.SENDTO", Uri.parse(url))
                this@VNP_AuthenticationActivity.startActivity(i)
                return true
            } else if (!url.startsWith("http")) {
                uri = Uri.parse(url)
                val ix = Intent("android.intent.action.VIEW", uri)
                this@VNP_AuthenticationActivity.startActivity(ix)
                this@VNP_AuthenticationActivity.finish()
                return true
            } else {
                view.loadUrl(url)
                return true
            }
        }
    }

    companion object {
        private var sdkCompletedCallback: VNP_SdkCompletedCallback? = null
        fun setSdkCompletedCallback(sdkCompletedCallback: VNP_SdkCompletedCallback) {
            VNP_AuthenticationActivity.Companion.sdkCompletedCallback = sdkCompletedCallback
        }

        private fun replaceUriParameter(uri: Uri, key: String, newValue: String): Uri {
            val params = uri.queryParameterNames
            val newUri = uri.buildUpon().clearQuery()
            val var5: Iterator<*> = params.iterator()

            while (var5.hasNext()) {
                val param = var5.next() as String
                newUri.appendQueryParameter(param, if (param == key) newValue else uri.getQueryParameter(param))
            }

            return newUri.build()
        }

        private fun addUriParameter(uri: Uri, key: String, newValue: String): Uri {
            val params = uri.queryParameterNames
            val newUri = uri.buildUpon().clearQuery()
            val var5: Iterator<*> = params.iterator()

            while (var5.hasNext()) {
                val param = var5.next() as String
                newUri.appendQueryParameter(param, uri.getQueryParameter(param))
            }

            newUri.appendQueryParameter(key, newValue)
            return newUri.build()
        }
    }
}

fun setSdkCompletedCallback(sdkCompletedCallback: VNP_SdkCompletedCallback?) {

}

fun openSdk(context: Context) {
    val intent = Intent(context,VNP_AuthenticationActivity::class.java)
    intent.putExtra("url", "https://sandbox.vnpayment.vn/testsdk/") //bắt buộc, VNPAY cung cấp
    intent.putExtra("tmn_code", "FAHASA03") //bắt buộc, VNPAY cung cấp
    intent.putExtra(
        "scheme",
        "resultactivity"
    ) //bắt buộc, scheme để mở lại app khi có kết quả thanh toán từ mobile banking
    intent.putExtra("is_sandbox", false) //bắt buộc, true <=> môi trường test, true <=> môi trường live
    setSdkCompletedCallback(object : VNP_SdkCompletedCallback  {

        override fun sdkAction(var1: String?) {
            TODO("Not yet implemented")
            Log.wtf("SplashActivity", "action: $var1")
            // action == AppBackAction
            // Người dùng nhấn back từ sdk để quay lại

            // action == CallMobileBankingApp
            // Người dùng nhấn chọn thanh toán qua app thanh toán (Mobile Banking, Ví...)
            // lúc này app tích hợp sẽ cần lưu lại cái PNR, khi nào người dùng mở lại app tích hợp thì sẽ gọi kiểm tra trạng thái thanh toán của PNR Đó xem đã thanh toán hay chưa.

            // action == WebBackAction
            // Người dùng nhấn back từ trang thanh toán thành công khi thanh toán qua thẻ khi url có chứa: cancel.sdk.merchantbackapp

            // action == FaildBackAction
            // giao dịch thanh toán bị failed

            // action == SuccessBackAction
            // thanh toán thành công trên webview
        }
    })
    context.startActivity(intent)
}
package com.example.h5traveloto_booking.payment

import android.util.Log
import java.net.URLEncoder
import java.security.MessageDigest
import java.text.Collator
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class VNPayLibrary {
    private val requestData = TreeMap<String, String>()
    private val responseData = TreeMap<String, String>()

    fun addRequestData(key: String, value: String) {
        if (!value.isNullOrEmpty()) {
            requestData[key] = value
        }
    }

    fun addResponseData(key: String, value: String) {
        if (!value.isNullOrEmpty()) {
            responseData[key] = value
        }
    }

    fun getResponseData(key: String): String {
        return responseData[key] ?: ""
    }

    fun createRequestUrl( baseUrl: String, vnpHashSecret: String): String {
        val data = StringBuilder()
        for ((key, value) in requestData) {
            if (!value.isNullOrEmpty()) {
                data.append(URLEncoder.encode(key, "UTF-8"))
                data.append("=")
                data.append(URLEncoder.encode(value, "UTF-8"))
                data.append("&")
            }
        }
        var queryString = data.toString()
        var hehe = baseUrl
        Log.d("hehe12",hehe.toString())

        hehe += "?$queryString"
        Log.d("hehe13",hehe.toString())

        if (queryString.isNotEmpty()) {
            queryString = queryString.removeSuffix("&")
        }
        Log.d("hehe14",hehe.toString())
        Log.d("hehe14",queryString.toString())
        val vnpSecureHash = Utils.hmacSHA512(vnpHashSecret, queryString)
        hehe += "vnp_SecureHash=$vnpSecureHash"
        return hehe
    }

    fun validateSignature(inputHash: String, secretKey: String): Boolean {
        val rspRaw = getResponseData()
        val myChecksum = Utils.hmacSHA512(secretKey, rspRaw)
        return myChecksum.equals(inputHash, ignoreCase = true)
    }

    private fun getResponseData(): String {
        val data = StringBuilder()
        responseData.remove("vnp_SecureHashType")
        responseData.remove("vnp_SecureHash")
        for ((key, value) in responseData) {
            if (!value.isNullOrEmpty()) {
                data.append(URLEncoder.encode(key, "UTF-8"))
                data.append("=")
                data.append(URLEncoder.encode(value, "UTF-8"))
                data.append("&")
            }
        }
        if (data.isNotEmpty()) {
            data.setLength(data.length - 1)
        }
        return data.toString()
    }
}

object Utils {
    fun hmacSHA512(key: String, inputData: String): String {
        val mac = Mac.getInstance("HmacSHA512")
        val secretKeySpec = SecretKeySpec(key.toByteArray(), "HmacSHA512")
        mac.init(secretKeySpec)
        val hashBytes = mac.doFinal(inputData.toByteArray())
        return hashBytes.joinToString("") { "%02x".format(it) }
    }
}

class VnPayCompare : Comparator<String> {
    private val vnpCompare: Collator = Collator.getInstance(Locale("en", "US"))

    override fun compare(x: String?, y: String?): Int {
        if (x == y) return 0
        if (x == null) return -1
        if (y == null) return 1
        return vnpCompare.compare(x, y)
    }
}
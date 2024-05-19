package websocket

import android.util.Log
import com.example.h5traveloto_booking.account.personal_information.getRealPathFromUri
import com.example.h5traveloto_booking.chat.presentation.data.dto.SendMessageDTO
import com.example.h5traveloto_booking.util.SharedPrefManager
import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONObject
import java.net.URI
import java.net.URISyntaxException
import javax.inject.Singleton

class SocketHandler {
    private var token = ""
    fun setToken(token: String) {
        this.token = token
    }

    lateinit var mSocket: Socket

    @Synchronized
    fun setSocket() {
        try {

            mSocket = IO.socket(URI.create("wss://api.h5traveloto.site"), IO.Options().apply {
                reconnection = true
                reconnectionAttempts = 3
                reconnectionDelay = 1000
                reconnectionDelayMax = 5000
                timeout = 20000
                path = "/socket.io"
                transports = arrayOf("websocket")

            })

        } catch (e: URISyntaxException) {
            Log.d("Socket Error", e.message.toString())
        }
        mSocket.open()
    }

    @Synchronized
    fun getSocket(): Socket {
        return mSocket
    }

    @Synchronized
    fun ping(): Socket {
        mSocket.emit("ping")
        return mSocket
    }

    @Synchronized
    fun onPing(): Socket {
        mSocket.on(Socket.EVENT_PONG) {
            Log.d("Pong", "Pong")

        }
        return mSocket
    }

    @Synchronized
    fun onConnect(): Socket {
        mSocket.on(Socket.EVENT_CONNECT) {
            Log.d("Socket token", token )
            mSocket.emit("authenticate", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXlsb2FkIjp7InVzZXJfaWQiOjIwLCJyb2xlIjoib3duZXIiLCJFeHBpcnkiOjB9LCJleHAiOjE3MTg1MjI4MDEsImlhdCI6MTcxNTkzMDgwMX0.f6ucf-A9W5izbodJbAWe-aqdLbKY9LZi5w8bcGQ01Ug")
            mSocket.emit("user_joined", "66472bbdf70ec79d3c5d6709")

        }

        return mSocket
    }

    @Synchronized
    fun establishConnection() {

        mSocket.connect()
    }

    @Synchronized
    fun closeConnection() {
        mSocket.disconnect()
    }
    @Synchronized
    fun joinRoom(hotelId: String) {
        mSocket.emit("user_joined", hotelId)
    }
   @Synchronized
   fun sendMessage(sendMessageDTO: SendMessageDTO) {
       val jsonString = Gson().toJson(sendMessageDTO)
       Log.d("Send Message", sendMessageDTO.toString())
       Log.d("Send Message", jsonString)
       val jsonObject = JSONObject(jsonString)
       Log.d("Send Message", jsonObject.toString())
       mSocket.emit("message_sent", jsonObject)
   }

    @Synchronized
    fun onMessageSent() {
        mSocket.on("message_sent") {
            Log.d("Message Sent", it.toString())
        }
    }
    @Synchronized
    fun onCannotSendMessage() {
        mSocket.on("cannot_send_message") {
            Log.d("Cannot Send Message", it.toString())
        }
    }
    @Synchronized
    fun onNewMessage() {
        mSocket.on("new_message") {
            Log.d("New Message", it.toString())
        }
    }



    @Synchronized
    fun onAuthenticateFail() {
        mSocket.on("authentication_failed") {

            Log.d("authentication_failed", it.toString())
        }
    }
}

@Singleton
val socketHandler1 = SocketHandler()
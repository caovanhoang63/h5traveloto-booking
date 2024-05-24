package websocket

import android.util.Log
import com.example.h5traveloto_booking.chat.presentation.data.dto.SendMessageDTO
import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONObject
import java.net.URI
import java.net.URISyntaxException
import javax.inject.Singleton


class SocketHandler {
    private var token = ""
    fun setToken(token: String) {
        this.token = token
        Log.d("Socket token", token)
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
            mSocket.emit("authenticate", token)
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
    fun onJoinedRoom() {
        mSocket.on("user_joined") {
            Log.d("User Joined", it.toString())
        }
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


    /*class MessageListener : Emitter.Listener {
        override fun call(vararg args: Any?) {
            Log.d("New message",args[0].toString())
        }
    }

    @Synchronized
    fun onNewMessage() {
        val messageListener = MessageListener()
        mSocket.on("new_message", messageListener)
    }*/
    // Định nghĩa một callback để trả về giá trị của args[0]
    interface MessageCallback {
        fun onMessageReceived(message: Any?)
    }

    class MessageListener(private val callback: MessageCallback) : Emitter.Listener {
        override fun call(vararg args: Any?) {
            // Gọi callback khi nhận được tin nhắn mới
            callback.onMessageReceived(args[0])
        }
    }

    @Synchronized
    fun onNewMessage(callback: MessageCallback) {
        val messageListener = MessageListener(callback)
        mSocket.on("new_message", messageListener)
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
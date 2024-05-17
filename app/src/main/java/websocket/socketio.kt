package websocket

import android.util.Log
import com.example.h5traveloto_booking.account.personal_information.getRealPathFromUri
import com.example.h5traveloto_booking.util.SharedPrefManager
import io.socket.client.IO
import io.socket.client.Socket
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


}

@Singleton
val socketHandler1 = SocketHandler()
package websocket

import java.util.logging.SocketHandler

fun setupEvent(socketHandler: websocket.SocketHandler) {
    socketHandler.setSocket()
    socketHandler.establishConnection()
    socketHandler.ping()
    socketHandler.onPing()
    socketHandler.onConnect()
}
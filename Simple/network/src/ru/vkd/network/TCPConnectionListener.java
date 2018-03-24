package ru.vkd.network;

import java.io.IOException;

public interface TCPConnectionListener {

    void onConnectionReady(TCPConnection tcpConnection);// при готовности соединения

    void onReceiveString(TCPConnection tcpConnection, String value);// при пересылке строки

    void onDisconnect(TCPConnection tcpConnection);// при отсоединении пользователя

    void onException(TCPConnection tcpConnection, IOException e);// при выподении строки

}

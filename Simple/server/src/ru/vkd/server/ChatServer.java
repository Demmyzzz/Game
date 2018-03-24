package ru.vkd.server;
import ru.vkd.network.TCPConnection;
import ru.vkd.network.TCPConnectionListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class ChatServer implements TCPConnectionListener {
    public static void main(String[] args) {
        new ChatServer();
    }

    private final ArrayList<TCPConnection> connections = new ArrayList<>();

    private ChatServer() {
        System.out.println("Сервер запущен...");
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            while (true) {
                try {
                    new TCPConnection(this, serverSocket.accept());
                } catch (IOException e) {
                    System.out.println("Ошибка в TCPConnection: " + e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendToAllConnections(String value) {
        System.out.println(value);
        for (TCPConnection connection : connections){
            connection.sendString(value);
        }
    }

    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        connections.add(tcpConnection);
        sendToAllConnections("Клиент подключен: " + tcpConnection);
    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {
        sendToAllConnections(value);
    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        connections.remove(tcpConnection);
        sendToAllConnections("Клиент отсоединился: " + tcpConnection);
    }

    @Override
    public void onException(TCPConnection tcpConnection, IOException e) {
        System.out.println("Ошибка в TCPConnection: " + e);
    }
}

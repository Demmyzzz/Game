package ru.vkd.client;

import ru.vkd.network.TCPConnection;
import ru.vkd.network.TCPConnectionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientWindow extends JFrame implements ActionListener, TCPConnectionListener {
    private static final String IP_ADDR = "::1"; //Server ip address
    private static final int PORT = 8189; // Server work port
    private static final int WIDTH = 600; // Window size width (640)
    private static final int HEIGHT = 400; // Window size height (480)

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientWindow();
            }
        });
    }

    private final JTextField fieldNickname = new JTextField("Demmy");
    private final JTextArea log = new JTextArea();
    private final JTextField fieldInput = new JTextField();

    private TCPConnection connection;

    private ClientWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //закрыввать стандартными средствами
        setSize(WIDTH, HEIGHT); // Размеры окна
        setLocationRelativeTo(null); // Расположение окна (по умолчанию)
        setAlwaysOnTop(true); //Данное окно поверх остальных окон

        add(fieldNickname, BorderLayout.NORTH);

        log.setEditable(false);
        log.setLineWrap(true);
        add(log, BorderLayout.CENTER);
        fieldInput.addActionListener(this);
        add(fieldInput, BorderLayout.SOUTH);

        setVisible(true);
        try {
            connection = new TCPConnection(this, IP_ADDR, PORT);
        } catch (IOException e) {
            printMessage("Ошибка соединения: " + e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = fieldInput.getText();
        if (msg.equals("")) return;
        fieldInput.setText(null);
        connection.sendString(fieldNickname.getText() + ": " + msg);
    }

    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        printMessage("Соединение готово...");
    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {
        printMessage(value);
    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        printMessage("Соединение разорвано");
    }

    @Override
    public void onException(TCPConnection tcpConnection, IOException e) {
        printMessage("Ошибка соединения: " + e);
    }

    private synchronized void printMessage(String message) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                log.append(message + "\n");
                log.setCaretPosition(log.getDocument().getLength());
            }
        });
    }
}

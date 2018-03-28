package Socket.str;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8082);
        System.out.println("server is wait......");
        try (Socket clietSocket = serverSocket.accept();
             InputStream inputStream = clietSocket.getInputStream();
             OutputStream outputStream = clietSocket.getOutputStream()){

            System.out.println("New link" + clietSocket.getInetAddress().toString());

            DataInputStream in = new DataInputStream(inputStream);
            DataOutputStream out = new DataOutputStream(outputStream);

            String say;

            while (true){
                say = in.readUTF();
                System.out.println("клиент прислал - " + say);
                out.writeUTF(say);
                out.flush();
                Thread.sleep(2000);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("клиент офф..");

    }
}

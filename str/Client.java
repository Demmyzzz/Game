package Socket.str;

import java.io.*;
import java.net.Socket;


public class Client {

    public static void main(String[] args) throws InterruptedException {
        try(Socket socket = new Socket("localhost", 8082 );InputStream inputStream = socket.getInputStream();OutputStream outputStream = socket.getOutputStream()){

            DataInputStream in = new DataInputStream(inputStream);
            DataOutputStream out = new DataOutputStream(outputStream);

            BufferedReader userSay = new BufferedReader(new InputStreamReader(System.in));
            String say = null;

            while(true){
                say = userSay.readLine();
                System.out.println("Sending this line to the server...");
                out.writeUTF(say);
                say = in.readUTF();
                System.out.println(say+" прислал сервер");
                out.flush();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


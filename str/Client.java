package Socket.str;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws InterruptedException {
        try(Socket socket = new Socket("localhost", 8082 );InputStream inputStream = socket.getInputStream();OutputStream outputStream = socket.getOutputStream()){

            DataInputStream in = new DataInputStream(inputStream);
            DataOutputStream out = new DataOutputStream(outputStream);

            BufferedReader userSay = new BufferedReader(new InputStreamReader(System.in));
            String say ;

            String keyWord = "/exit";

            while(true){
                say = userSay.readLine();
                out.writeUTF(say);
                if(keyWord.equals(say)) {break;}
                say = in.readUTF();
                System.out.println("сервер прислал - " + say);
                out.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


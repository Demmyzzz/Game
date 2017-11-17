package ru.vkd.manythread;

import java.io.*;

public class Govnokod extends Thread{
    public void run(){
        String s;
        try (BufferedReader br = new BufferedReader(new FileReader("src\\text2"))) {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src\\test2.txt"));
            while ((s=br.readLine())!=null) {
                bw.write(s+"\n");
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

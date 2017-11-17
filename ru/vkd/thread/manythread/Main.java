package ru.vkd.manythread;

import java.io.*;

public class Main extends Thread{

    public void run(){
        String s;
        try (BufferedReader br = new BufferedReader(new FileReader("src\\text.txt"))) {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src\\test.txt"));
            while ((s=br.readLine())!=null) {
                bw.write(s+"\n");
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread first = new Main();
        Thread second = new Govnokod();
        first.start();
        second.start();
    }
}

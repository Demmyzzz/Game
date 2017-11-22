package ru.vkd.manythread;

import java.io.*;
import java.util.ArrayList;

public class Main extends Thread{
    static ArrayList<String> list = new ArrayList<String>();
    public void run() {
        String s;
        String s2;
        int i=0;
        try (BufferedReader br = new BufferedReader(new FileReader("src\\text.txt"))) {
            BufferedReader br2 = new BufferedReader(new FileReader("src\\text2"));
            while (((s = br.readLine()) != null)|((s2 = br2.readLine()) != null)) {
                if (s!=null){list.add(s);}
                if (s2!=null){list.add(s2);}
            }
            System.out.println(list);
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

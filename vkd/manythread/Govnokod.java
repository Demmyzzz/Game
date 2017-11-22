package ru.vkd.manythread;

import java.io.*;
import java.util.ArrayList;

public class Govnokod extends Thread{
    int counter = 0;
    public void run(){
        Main.list.get(1);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("src\\test.txt"))){
            for (int i=0 ; i<Main.list.size();i++ ){
                bw.write(Main.list.get(i)+"\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }
}

package ru.vkd.threadcopy;

import java.io.*;

public class Main extends Thread{

    private String inputFile;
    private String outputFile;

    public Main(String input, String output) {
        this.inputFile = input;
        this.outputFile = output;
    }
    public void run() {
        String s;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(inputFile))) {
            BufferedReader br = new BufferedReader(new FileReader(outputFile));
            while ((s = br.readLine()) != null) {
                bw.write(s + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() + " Millis ");
    }
    public static void main(String[] args) {
        Main mainF = new Main("src\\text.txt" , "src\\test.txt");
        Main mainS = new Main("src\\text2", "src\\test2.txt");
        mainF.start();
        mainS.start();
    }
}
        /*
        SecondMain First = new SecondMain("src\\text.txt", "src\\test.txt");
        SecondMain Second =  new SecondMain("src\\text2", "src\\test2.txt");
        */


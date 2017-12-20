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
        long time = System.currentTimeMillis();
        String s;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(inputFile))) {
            BufferedReader br = new BufferedReader(new FileReader(outputFile));
            while ((s = br.readLine()) != null) {
                bw.write(s + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println( System.currentTimeMillis()-time+" millis");//выводит сколько миллисекунд работал поток
        System.out.println(System.currentTimeMillis()+" Millis");//выводи текущее время в миллисекундах
    }
    public static void main(String[] args) {

            Main mainF = new Main("src\\test.txt", "src\\text.txt");
            Main mainS = new Main("src\\test2.txt", "src\\text2");
            mainF.start();
            mainS.start();
            //запистили 2 потока
            try{
                // с помощью метода .join мы сделаем так что бы потоки выполнялись послежовательно тоесть программма
                // сначало запустит и выполнит все операции с первым потоком а потом уже тольк оприступит к следующему потоку
                mainF.join();
                mainS.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
}

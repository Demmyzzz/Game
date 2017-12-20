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
        System.out.println("start processing... "+getName()+" "+currentThread());

        String s;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(inputFile))) {
            BufferedReader br = new BufferedReader(new FileReader(outputFile));
            while ((s = br.readLine()) != null) {
                bw.write(s + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("end process "+getName()+ " "+ currentThread());
        //выводит сколько миллисекунд работал поток
      //  System.out.println(System.currentTimeMillis()+" Millis");выводи текущее время в миллисекундах
    }
    public static void main(String[] args) {

            Main mainF = new Main("src\\test.txt", "src\\text.txt");
            Main mainS = new Main("src\\test2.txt", "src\\text2");
            try{
                mainF.setName("firstThread");
                mainS.setName("secondThread");
                long time = System.currentTimeMillis();
                mainF.start();
               mainF.join();
                System.out.println(System.currentTimeMillis() - time+" millis");
                long time1 = System.currentTimeMillis();
               mainS.start();
               mainS.join();
                System.out.println(System.currentTimeMillis() - time1+" millis");




            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }

}
//запистили 2 потока


// с помощью метода .join мы сделаем так что бы потоки выполнялись последовательно тоесть программма
// сначала запустит и выполнит все операции с первым потоком а потом уже только приступит к следующему потоку

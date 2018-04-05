package ru.vkd.textinfile;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


/**
 * Дополнительно :
 * Ищем только txt-файлы для
 */

public class ReadDirectory {

    private static String DIRECTORY_NAME = "D:\\work\\";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File directory;
        String[] listDir;

        String keyWord;

        int numLine = 0;

        Scanner enter = new Scanner(System.in);

        System.out.print("Введите слово которое вы ищете: ");
        keyWord = enter.nextLine();
        System.out.println("Вы ищите слово - " + keyWord);

        directory = new File(DIRECTORY_NAME);

        System.out.println("Enter your directory");

        listDir = directory.list();
        System.out.println(Arrays.toString(listDir));

        File[] files = directory.listFiles();
        // listFiles() - массив объектов File

        String line;

        assert files != null;
        for (File file: files) {
            if (file.isFile()||file.isDirectory()) {
                System.out.println(" ---- ");
                System.out.println(file.getName());
                System.out.println(" ---- ");

                try(BufferedReader br = new BufferedReader(new FileReader(file))){
                    while((line = br.readLine()) != null ){

                        numLine++;

                        String[] words = line.split(" ");
                        int j = 1;
                        for (String word : words) {
                            if (word.equalsIgnoreCase(keyWord)) {
                            }
                            j++;
                        }

                        if (line.contains(keyWord)) {
                            System.out.println(" ");
                            System.out.println("это слово находится в строке - " + numLine + ", " + j + "-е слово");
                            System.out.println(" ------------------ ");
                        }

                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }

            }
            System.out.println("Путь к файлу - " + file);
        }
    }
}

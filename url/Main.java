package ru.vkd.url;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main{

    //private static final String OUT_FILE_TXT = "src\\outFile.txt";
    //private static final String URL_SRC = "(?<=src=\\\")[^\\\"]*([jJ][pP][gG])(?=\\\")";

    private static final String IN_FILE_TXT = "D:\\inFile.txt";
    private static final String PATH_TO_MUSIC = "D:\\";
    public static final String DATA_URL = "\\s*(?<=data-url\\s?=\\s?\")[^>]*\\/*(?=\")";

    private static final ArrayList<URL> list = new ArrayList<>();

    public static void main(String[] args) {
        
        String Url;

        String htmlCode;

        try (BufferedReader inFile = new BufferedReader(new FileReader(IN_FILE_TXT))) {
            while ((Url = inFile.readLine()) != null) {
                URL url = new URL(Url);

                /*
                 * PageFlow(поток страницы)
                 * этот метд предназначен для для счиывания из общего потока страницы строчку кода
                 * и сохранения этой строки в переменную result для последующего использования в других методах
                 * */

                htmlCode = PageFlow(url);

                /*
                * метод CheckPattern ищет ссылки на скачивание музыки и записывает их
                * в ArrayList<URL> list
                * */
                
                CheckPattern(htmlCode);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
         * метод Transfer передаёт в класс Download
         * URL url
         * String strUrl
         * String file
         */

        Transfer();

        /*
        * вывел чтобы посмотреть есть ли там url или нет
        * */

        System.out.println(list.get(0)+" first url");
        System.out.println(list.get(1)+" second url");
    }


    public static String PageFlow(URL url) {
        String result = "";
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            result = bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void CheckPattern(String htmlCode) {
        try {
            Pattern email_pattern = Pattern.compile(DATA_URL);
            Matcher matcher = email_pattern.matcher(htmlCode);
            int i = 0;
            while (matcher.find() && i < 2) {
                list.add(new URL(matcher.group()));
                i++;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void Transfer() {
        URL music;
        int count = 0;
        try {
            for (URL url : list) {
                music = list.get(count);
                downloadUsingNIO(music, PATH_TO_MUSIC + String.valueOf(count) + ".mp3");
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void downloadUsingNIO(URL strUrl, String file) throws IOException {
        URL url = strUrl;
        Download threadNumOne = new Download(url,file);
        threadNumOne.start();
    }
}
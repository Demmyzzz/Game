package ru.vkd.url;

import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main{

    private static final String IN_FILE_TXT = "src\\inFile.txt";
    private static final String OUT_FILE_TXT = "src\\outFile.txt";
    private static final String PATH_TO_MUSIC = "src\\ru";
    public static final String DATA_URL = "\\s*(?<=data-url\\s?=\\s?\")[^>]*\\/*(?=\")";

    public static void main(String[] args) {
        String Url;
        try (BufferedReader inFile = new BufferedReader(new FileReader(IN_FILE_TXT))) {
            while ((Url = inFile.readLine()) != null) {
                URL url = new URL(Url);
                String result ="";
                
                /*
                * PageFlow(поток страницы)
                * этот метд предназначен для для счиывания из общего потока страницы строчку кода
                * и сохранения этой строки в переменную result для последующего использования в других методах 
                * */

                PageFlow(url, result);
                /*
                * метод CheckPattern ищет ссылки на скачивание музыки и записывает их
                * в outFile.txt (OUT_FILE_TXT)
                * */
                
                CheckPattern(url, result);

                /*
                * метод Transfer передаёт в класс Download
                * URL url
                * String strUrl
                * String file
                * */

                Transfer();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*

 */
    private static String PageFlow(URL url, String result) {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            result = bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
/*
*
* */
    private static void CheckPattern(URL url, String result) {
        try (BufferedWriter outFile = new BufferedWriter(new FileWriter(OUT_FILE_TXT))) {
            Pattern email_pattern = Pattern.compile(DATA_URL);
            Matcher matcher = email_pattern.matcher(PageFlow(url, result));
            int i = 0;

            while (matcher.find() && i < 2) {

                outFile.write(matcher.group() + "\r\n");
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void Transfer() {
        String music;
        int count = 0;
        try (BufferedReader musicFile = new BufferedReader(new FileReader(OUT_FILE_TXT))){
            while ((music = musicFile.readLine()) != null) {

                downloadUsingNIO(music, PATH_TO_MUSIC + String.valueOf(count) + ".mp3");
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void downloadUsingNIO(String strUrl, String file) throws IOException {
        URL url = new URL(strUrl);
        Download d = new Download(url, strUrl, file);
        Download threadNumOne = new Download(url,strUrl,file);
        Download threadNumTwo = new Download(url,strUrl,file);

        threadNumOne.setName("first");
        threadNumTwo.setName("second");

    }
}


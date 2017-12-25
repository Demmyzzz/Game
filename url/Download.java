package ru.vkd.url;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Download extends Thread{

    private URL url;
    private String file;

    public Download(URL url, String file) {
        this.url = url;
        this.file = file;
    }

    public void run(){
        try {
            System.out.println("processed... "+ getName());
            ReadableByteChannel byteChannel = Channels.newChannel(url.openStream());
            FileOutputStream stream = new FileOutputStream(file);
            stream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
            stream.close();
            byteChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package ru.vkd.thread;

public class Second extends Thread{
    int counter = 0;
    public void run(){
        while (counter < 100) {
            if (counter++ == 20) {
                Thread.currentThread().setPriority(MAX_PRIORITY);
            }
        }
    }
}

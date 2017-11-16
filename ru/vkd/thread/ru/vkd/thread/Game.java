package ru.vkd.thread;

public class Game extends Thread {
    int counter = 0;
    public void run (){
        while( counter < 100){
            if (counter++ == 20){
                if (Thread.currentThread().getPriority() == MIN_PRIORITY ) {
                    Thread.currentThread().setPriority(MAX_PRIORITY);
                } else {
                    Thread.currentThread().setPriority(MIN_PRIORITY);
                }
            }
        }
    }

        public static void main(String[] args) {
            Thread First = new Game();
            Thread Second = new Game();
            First.setPriority(MAX_PRIORITY);
            Second.setPriority(MIN_PRIORITY);
            First.start();
            Second.start();
            System.out.println();
            First.setName("priority_max");
            Second.setName("priority_min");
        }
    }


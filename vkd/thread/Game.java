package ru.vkd.thread;

public class Game extends Thread {
    int counter = 0;
    int i =0;
    public void run (){

        while( counter < 100){
            if (counter++ == 20){
                if (getPriority() == MIN_PRIORITY ) {
                    setPriority(MAX_PRIORITY);
                } else {
                    setPriority(MIN_PRIORITY);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                i++;
            }
            System.out.println(i);
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


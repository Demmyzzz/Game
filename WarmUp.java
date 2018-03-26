package warmup;

import java.util.Arrays;
import java.util.Scanner;

public class WarmUp {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int c;
        System.out.print("Ведите количевсто заказов: ");
        c = sc.nextInt();

        int t;
        int array[] = new int[c];
        addArr(array);
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));

        System.out.println("Введите сколько заказов сложить");
        t = sc.nextInt();
        while(t > c){
            System.out.println("вы ввели время большее чем количевство заказов");
            t=sc.nextInt();
        }

        int sum = 0;

        for (int i = array.length - 1; i >= (t - 1); i--) {
            sum = sum + array[i];
        }
        System.out.println(sum+" summ");
    }

    private static void addArr(int[] array) {
        int i = 0;
        int num;
        while (array.length != i) {
            System.out.print("Введите количевсто денег за заказ: ");
            num = sc.nextInt();
            array[i] = num;
            i++;
        }
    }
}
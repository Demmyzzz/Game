package warmup;

import java.util.Arrays;
import java.util.Scanner;

public class WarmUp {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int t;
        int array[] = new int[5];
        addArr(array);
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
        int sum = 0;
        System.out.println("Введите сколько чисел сложить");
        t = sc.nextInt();
        for (int i = 0; i <= (t - 1); i++) {
            sum = array[i] + sum;
        }
        System.out.println(sum + " summ");
    }

    private static void addArr(int[] array) {
        int i = 0;
        int num;
        while (array.length != i) {
            System.out.print("Enter your num: ");
            num = sc.nextInt();
            array[i] = num;
            i++;
        }
    }
}
package warmup;

import java.util.Arrays;
import java.util.Scanner;

import static jdk.nashorn.internal.objects.NativeArray.reverse;

public class WarmUp {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int t;
        int array[] = new int[5];
        addArr(array);
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));

        System.out.println("Введите сколько чисел сложить");
        t = sc.nextInt();
        while(t > 5){
            System.out.println("вы ввели число больше 5");
            t=sc.nextInt();
        }
        
        int sum = 0;
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
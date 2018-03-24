package warm;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class WarmUp {

    public static void main(String[] args) {
        int t=2;
        int arr [] = new int[]{5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        int sum=0;
        for (int i = 0 ; i <=t; i++){
            sum=arr[i]+sum;
        }
        System.out.println(sum+" summ");
    }
}

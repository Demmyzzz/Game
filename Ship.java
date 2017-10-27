import java.util.Arrays;
import java.util.Scanner;

public class Ship {

    private int [] locationofship;

    public void setLocationofship(int[] locationofship) {
        this.locationofship = locationofship;
    }

    Scanner sc = new Scanner(System.in);
    public void shots(int x) {
        int sch = 0;
        int k = 0;
        while (true){
            int shot;
            shot = sc.nextInt();
            sch++;
            if (shot == x || shot == x+1 || shot == x+2){
                System.out.println("nice shot");
                if (k == 2){
                    break;
                }
                k++;
            }else{
                {
                    System.out.println("bad shot");
                }
            }
        }/*
        * g;ckn
         */
        System.out.println("was done "+sch+" attempts");
        }
    @Override
    public String toString() {
        return "Ship{" +
                "locationofship=" + Arrays.toString(locationofship) +
                '}';
    }
}

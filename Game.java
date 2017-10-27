import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        int i = 0;
        int shot;
        Scanner sc = new Scanner(System.in);
        int x = 0 + (int) (Math.random() * 7);
        Ship ship = new Ship();
        int[] arrofships = {x, x + 1, x + 2};
        ship.setLocationofship(arrofships);
        System.out.print(ship);
        ship.shots(x);
        System.out.println("awd");

/*
* adwfA
 */
    }
    }
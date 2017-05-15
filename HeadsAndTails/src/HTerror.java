
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author hjia
 */
public class HTerror {

    private static int heads = 5;
    private static int tails = 5;
    private static int total;
    private static ArrayList<String> headsAndTails;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int steps = 0;

        Scanner sc = new Scanner(System.in);
        System.out.print("Number of heads: ");
        heads = sc.nextInt();
        System.out.print("Number of tails: ");
        tails = sc.nextInt();
        total = heads + tails;
        // if it can't be balanced.
        if (heads - tails > 1 || heads - tails < -1) {
            System.out.println("impossible");
            return;
        }

        System.out.println("Steps: " + step(heads, tails));
    }

    /**
     * rearrange the coins.
     */
    public static int step(int heads, int tails) {
        
        return 1;
    }
}

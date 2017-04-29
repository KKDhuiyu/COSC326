
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author hjia
 */
public class HeadsAndTails {

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

        // create an arraylist depending on the sum of heads and tails
        initArrayList();
        if (total == 2) { // if it's HT
            System.out.println("Steps: " + steps);
            return;
        }
        while (!isInOrder(1,total)) {
            arrange(heads,tails);
            steps++;
        }
        System.out.println("Steps: " + steps);
    }

    /**
     * rearrange the coins.
     */
    public static void arrange(int heads, int tails) {
        if (heads >= tails) {
            for (int i = 1; i < total - 1; i++) {
                // if see "H" followed by a "T" in a "not in order" list. 
                //do a swap
                if (headsAndTails.get(i).equals("H")
                        && headsAndTails.get(i + 1).equals("T") 
                        && !isInOrder(0,i + 1)) {
                    headsAndTails.set(i, "T");
                    headsAndTails.set(i + 1, "H");
                    printArrayList();
                    return;
                }
            }
        }else{
            for (int i = total - 2; i >=1; i--) {
                // if see "H" followed by a "T" in a "not in order" list. 
                // do a swap
                if (headsAndTails.get(i).equals("T")
                        && headsAndTails.get(i - 1).equals("H") 
                        && !isInOrder(i-1,total)) {
                    headsAndTails.set(i, "H");
                    headsAndTails.set(i - 1, "T");
                    printArrayList();
                    return;
                }
            }
        }
    }

    /**
     * initialize and print the array list based on the sum of the heads and
     * tails.
     */
    public static void initArrayList() {
        headsAndTails = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            if (i < heads) {
                headsAndTails.add("H");
            } else {
                headsAndTails.add("T");
            }
        }
        printArrayList();
    }

    /**
     * check if the list before index n is in the required order.
     *
     * @param n the index of the list
     * @return a boolean that represents if its in order.
     */
    public static boolean isInOrder(int start,int end) {
        if(start==0){
            start =1;
        }
        for (int i = start; i < end; i++) {
            // can be faster here
            if (headsAndTails.get(i).equals(headsAndTails.get(i - 1))) {
                return false;
            }
        }
        return true;
    }

    public static void printArrayList() {
        String result = "";
        for (String s : headsAndTails) {
            result += s;
        }
        System.out.println(result);
    }
}

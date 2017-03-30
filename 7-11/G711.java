
/**
 * Find all amounts $x.yz between $1.0 and $9.99 such that there.
 * is a unique solution to a + b + c + d = $x.yz and a × b × c × d = $x.yz
 * and a ≤ b ≤ c ≤ d.
 */
import java.text.DecimalFormat;
import java.util.*;

/**
 * This class find all amounts $x.yz between $1.0 and $9.99 such that there is a
 * unique solution to a + b + c + d = $x.yz and a × b × c × d = $x.yz and a ≤ b
 * ≤ c ≤ d.
 *
 * @author Huiyu Jia
 */
public class G711 {

    private static int g711 = 711; // default value.
    private static int cents = g711 * 1000000;// $ to cents.
    private static int a, b, c, d; // four unknown integers(cause $ -> cents)
    private static int[] solutionCount = new int[900]; // 900 posiblities.

    /**
     * This is a ordinary main method.
     *
     * @param args The commend line inputs;
     */
    public static void main(String[] args) {
        int count = 0;
        //final long startTime = System.currentTimeMillis();
        for (int i = 100; i < 1000; i++) { // for each number between 100-999. 
            g711 = i;
            countSolutions(); // count the solutions. 
        }
        for (int i = 0; i < 900; i++) {
// for each index in the solutionCount list. 
            if (solutionCount[i] == 1) {
// if it only has the unique solution.
                g711 = i + 100;
// give its value to g711. 
                printSolution();
// call the print method to echo the result.
                count++;
            }
        }
        System.out.println(count + " solutions");

    }

    /**
     * This method counts how many solutions a certain number has.
     */
    public static void countSolutions() {
        //DecimalFormat df = new DecimalFormat("0.00"); // necessary fromating
        List<Integer> list = findDivs(); // find divisors.
        int size = list.size();
        for (int ai = 0; ai < size; ai++) { // try each divisor in the list
            a = list.get(ai);
            // try each divisor whose index is bigger than a.
            for (int bi = list.indexOf(list.get(ai)); bi < size; bi++) {
                b = list.get(bi);
                // try each divisor whose index is bigger than b.
                for (int ci = list.indexOf(list.get(bi)); ci < size; ci++) {
                    c = list.get(ci);
                    d = g711 - a - b - c; // a+b+c+d=g711
                    if (d >= c) {
                        if (a * b * c * d == cents && a + b + c + d == g711) {
                            solutionCount[g711 - 100]++;
//                             System.out.println("$" + 
//                                    df.format((double) g711 / 100) + " = $"
//                                    + df.format((double) a / 100) + " + $"
//                                    + df.format((double) b / 100) + " + $"
//                                    + df.format((double) c / 100) + " + $"
//                                    + df.format((double) d / 100));
                        }
                    }
                }
            }
        }
    }

    /**
     * This method prints out the unique solution of a certain number g711.
     */
    public static void printSolution() {
        DecimalFormat df = new DecimalFormat("0.00"); // necessary fromating
        List<Integer> list = findDivs();
        int size = list.size();
        for (int ai = 0; ai < size; ai++) {
            a = list.get(ai);
            for (int bi = list.indexOf(list.get(ai)); bi < size; bi++) {
                b = list.get(bi);
                for (int ci = list.indexOf(list.get(bi)); ci < size; ci++) {
                    c = list.get(ci);
                    d = g711 - a - b - c;
                    if (d >= c) {
                        if (a * b * c * d == cents && a + b + c + d == g711) {
                            //print results in a certain format
                            System.out.println("$"
                                    + df.format((double) g711 / 100) + " = $"
                                    + df.format((double) a / 100) + " + $"
                                    + df.format((double) b / 100) + " + $"
                                    + df.format((double) c / 100) + " + $"
                                    + df.format((double) d / 100));
                        }
                    }
                }
            }
        }
    }

    /**
     * This method finds all the divisors for a certain number. And store them
     * in a list to be returned.
     *
     * @return a list of divisors of g711.
     */
    public static List<Integer> findDivs() {
        List<Integer> list = new LinkedList<Integer>();
        for (int i = 1; i < g711 - 1; i++) {
            if (cents % i == 0) {
                list.add(i);
            }
        }
        return list;
    }

}

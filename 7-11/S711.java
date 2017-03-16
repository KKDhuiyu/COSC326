
/**
 * Let the prices be a ≤ b ≤ c ≤ d.
 * Find the values of a, b, c, d such that a + b + c + d = $7.11
 * and a × b × c × d = $7.11. There is a unique solution.
 */
import java.text.DecimalFormat;
import java.util.*;

/**
 * This class finds the unique solution. Let the prices be a ≤ b ≤ c ≤ d. Find
 * the values of a, b, c, d such that a + b + c + d = $7.11 and a × b × c × d =
 * $7.11. There is a unique solution.
 *
 * @author hjia
 */
public class S711 {

    private static int s711 = 711; // 711 cents
    private static int cents = s711 * 1000000; // 7.11* 10^8
    private static int a, b, c, d; // values need to be found

    /**
     * This is a ordinary main method.
     *
     * @param args The commend line inputs;
     */
    public static void main(String[] args) {
        // final long startTime = System.currentTimeMillis();
        findTheSolution();
        //final long endTime = System.currentTimeMillis();
        //System.out.println("Total execution time: " + (endTime - startTime) );
    }

    /**
     * This method prints out the unique solution of the number 711000000.
     */
    public static void findTheSolution() {
        DecimalFormat df = new DecimalFormat("0.00"); // necessary fromating
        List<Integer> list = findDivs(); // find the divisors of 711000000
        int size = list.size();
        for (int ai = 0; ai < size; ai++) {
            a = list.get(ai);
            for (int bi = list.indexOf(list.get(ai)); bi < size; bi++) {
                b = list.get(bi);
                for (int ci = list.indexOf(list.get(bi)); ci < size; ci++) {
                    c = list.get(ci);
                    d = 711 - a - b - c;// a+b+c+d=g711
                    if (a * b * c * d == cents && a + b + c + d == s711 && d >= c) {
                        //print results in a certain format
                        System.out.println("$"+df.format((double) a / 100) + " $"
                                + df.format((double) b / 100) + " $"
                                + df.format((double) c / 100) + " $"
                                + df.format((double) d / 100));
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
        for (int i = 1; i < s711 - 1; i++) {
            if (cents % i == 0) {
                list.add(i);
            }
        }
        return list;
    }

}

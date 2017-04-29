
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * The input is a list of numbers. The output would be the way of getting the
 * wanted result by using + and * operations and its order.
 *
 * @author hjia
 */
public class Arithmetic {

    private static ArrayList<Integer> results;

    /**
     * Read input from stdin. Perform functions then print the output.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            ArrayList<Integer> input = new ArrayList<>();
            String order;
            results = new ArrayList<>();
            int wantedResult;

            String line = sc.nextLine();
            for (String a : line.split(" ")) {
                input.add(Integer.valueOf(a));
            }
            String[] line2 = sc.nextLine().split(" ");
            wantedResult = Integer.parseInt(line2[0]);
            order = line2[1];
            if (input.size() == 1) {
                if (input.get(0) == wantedResult) {
                    System.out.println(order + " " + wantedResult);
                } else {
                    System.out.println(order + " impossible");
                }
            } else {
                LinkedList<String> operations = 
                        generateArithmetic(input.size() - 1);
                if (order.equals("L") || order.equals("N")) {
                    calculate(input, order, operations, wantedResult);
                    if (results.isEmpty()) {
                        System.out.println(order + " impossible");

                    }
                } else {
                    System.err.println("invalid order");
                }
            }
        }

    }

    /**
     * Produce all the possible operations.
     *
     * @param n the number of operations
     * @return a 2D array of operations
     */
    public static LinkedList<String> generateArithmetic(int n) {
        int possibilities = (int) (Math.pow(2.0, (double) n));
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < possibilities; i++) {
            String temp = Integer.toBinaryString(i);
            for (int j = temp.length(); j < n; j++) {
                temp = "0" + temp;
            }
            list.add(temp);
        }
        return list;
    }

    /**
     * Calculate all the possible results.
     *
     * @param input the input list
     * @param order the calculate order
     * @param operations the set of operations
     * @param wantedResult
     * @return a list of results.
     */
    public static ArrayList<Integer> calculate(ArrayList<Integer> input,
            String order, LinkedList<String> operations, int wantedResult) {

        if (order.equals("L")) {
            for (String a : operations) { //a one line of operations
                int result = input.get(0);
                int i = 1;
                String[] x = a.split("");
                for (String o : x) {//o the operation in the 
                    if (o.equals("0")) {
                        result += input.get(i);
                    } else {
                        result *= input.get(i);
                    }
                    i++;
                }
                if (result == wantedResult) {
                    results.add(result);
                    printOutput(order, wantedResult, input, a);
                    return results;
                }
            }
        } else {
            for (String x : operations) {
                String[] a = x.split("");
                int result = 0;
                ArrayList<Integer> copy = new ArrayList<>();
                for (int i : input) {
                    copy.add(i);
                }
                for (int i = 0; i < a.length; i++) {

                    if (a[i].equals("1")) {
                        copy.set(i + 1, copy.get(i) * copy.get(i + 1));
                        copy.set(i, 0);
                    }
                }
                for (int n : copy) {
                    result += n;
                }
                if (result == wantedResult) {
                    results.add(result);
                    printOutput(order, wantedResult, input, x);
                    return results;
                }
                copy.clear();
            }
        }
        return results;
    }

    /**
     * print out the results in a certain format.
     *
     * @param order the calculate order.
     * @param wantedResult the target result.
     * @param input the list of number for calculation.
     * @param operations a set of operations.
     */
    public static void printOutput(String order, int wantedResult,
            ArrayList<Integer> input, String operations) {
        String[] x = operations.split("");
        String result = order + " " + input.get(0);
        int i = 1;
        for (String o : x) {
            if (o.equals("0")) {
                result += " + " + input.get(i);
            } else {
                result += " * " + input.get(i);
            }
            i++;
        }
        System.out.println(result);

    }

}

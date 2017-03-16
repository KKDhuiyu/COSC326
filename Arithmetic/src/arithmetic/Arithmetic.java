
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hjia
 */
public class Arithmetic {

    /**
     * @param args the command line arguments
     */
    private static ArrayList<Integer> results;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
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
            String[][] operations = generateArithmetic(input.size() - 1);
            if (order.equals("L") || order.equals("N")) {
                calculate(input, order, operations);
                printOutput(order, wantedResult, input, operations);
            } else {
                System.err.println("invalid order");
            }
//                System.out.println(input+"     \n"+wantedResult+" \n "+order);
//               System.out.println(results);
//            String[][] a= generateArithmetic(input.size()-1);
//            for(String[] x:a){
//                for(String s:x){
//                    System.out.print(s+" ");
//                }
//                System.out.println();
//            }

            //           System.out.println("reached");
        }
    }

    public static String[][] generateArithmetic(int n) {
        int possibilities = (int) (Math.pow(2.0, (double) n));
        String[][] list = new String[possibilities][n];
        int numOfnForEachGroup;
        int groups;
        int flag = 0;
        for (int j = 0; j < n; j++) {
            int rowCount = 0;
            // first row has 2^1 groups, second row has 2^2=4 groups
            groups = (int) Math.pow(2.0, (double) j + 1);
            // e.g if possibilities=8,then first row has 4"+" and 4"*"
            // the second row has 4 gourps, ++ ** ++ **.
            numOfnForEachGroup = possibilities / groups;
            for (int i = 0; i < possibilities; i++) {
                if (rowCount != 0 && rowCount % numOfnForEachGroup == 0) {
                    flag++;
                }
                if (flag % 2 == 0) {//this group is not finished yet
                    list[i][j] = "+";
                    rowCount++;
                } else {
                    list[i][j] = "*";
                    rowCount++;
                }
            }
        }
        return list;
    }

    public static ArrayList<Integer> calculate(ArrayList<Integer> input,
            String order, String[][] operations) {

        if (order.equals("L")) {
            for (String[] a : operations) { //a one line of operations
                int result = input.get(0);
                int i = 1;
                for (String o : a) {//o the operation in the 
                    if (o.equals("+")) {
                        result += input.get(i);
                    } else {
                        result *= input.get(i);
                    }
                    i++;
                }
                results.add(result);
            }
        } else {
            for (String[] a : operations) { //a one line of operations
                int result = 0;

                ArrayList<Integer> copy = (ArrayList<Integer>) input.clone();
                for (int i = 0; i < a.length; i++) {

                    if (a[i].equals("*")) {
                        copy.set(i + 1, copy.get(i) * copy.get(i + 1));
                        copy.set(i, 0);
                    }
                }
                for (int n : copy) {
                    result += n;
                }
                results.add(result);
                copy.clear();
            }
        }
        return results;
    }

    public static void printOutput(String order, int wantedResult,
            ArrayList<Integer> input, String[][] operations) {
        if (results.isEmpty()) {
            return;
        }
        int indexOfWantedResult = results.indexOf(wantedResult);
        if (indexOfWantedResult == -1) {
            System.out.println(order + " impossible");
        } else {
            String result = order + " " + input.get(0);
            int i = 1;
            for (String o : operations[indexOfWantedResult]) {
                result += " " + o + " " + input.get(i);
                i++;
            }
            System.out.println(result);
        }
    }

}

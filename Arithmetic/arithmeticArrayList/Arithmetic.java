
import java.util.ArrayList;
import java.util.Objects;
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
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            ArrayList<Integer> input = new ArrayList<>();
            String order;
            int wantedResult;
            String line = sc.nextLine();
            for (String a : line.split(" ")) {
                input.add(Integer.parseInt(a));
            }
            wantedResult = sc.nextInt();
            order = sc.next();
            if (order.equals("L") || order.equals("N")) {
                find(input, order);
            } else {
                System.err.println("invalid order");
            }
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

    public static ArrayList<String> find(ArrayList<Integer> input,
            String order) {
        ArrayList<String> output = new ArrayList<>();
        ArrayList<Integer> results = new ArrayList<>();
        
        return output;
    }

}

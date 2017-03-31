/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harmoniousnum;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author hjia
 */
public class HarmoniousNum {

    /**
     * @param args the command line arguments
     */
    private static final int LOWERBOUND = 2000000;
    private static List<Integer> output = new LinkedList<>();

    public static void main(String[] args) {

        calculate();
//      print(output);

    }

    public static void calculate() {
        for (int i = 4; i < LOWERBOUND; i++) {
            if (output.indexOf(i) == -1) {
                List<Integer> list = findDivs(i);
                int sum = sum(list);

                if (sum < i); else if (sum(findDivs(sum)) == i) {
                    output.add(i);
                    output.add(sum);
                    System.out.println(i + " " + sum);

                }
            }
        }

    }

    public static int sum(List<Integer> list) {
        int sum = 0;
        for (int a : list) {
            sum += a;
        }
        return sum;
    }

    public static List<Integer> findDivs(int n) {
        List<Integer> list = new LinkedList<>();
        for (int i = 2; i < n - 1; i++) {
            if (n % i == 0) {
                if (list.indexOf(i) == -1) {
                    list.add(i);
                    if (i != n / i) {
                        list.add(n / i);
                    } else {// i^2=n
                        return list; 
                    }
                } else {// 
                    return list; 
                }
            }
        }
        return list;
    }
//     public static void print(List<Integer> list){
//         for(int i=0;i<list.size();i+=2){
//             if(i==list.size()-2){
//                 System.out.print(list.get(i)+" "+ list.get(i+1));
//             }else{
//             System.out.println(list.get(i)+" "+ list.get(i+1));
//             }
//         }
//     }

}

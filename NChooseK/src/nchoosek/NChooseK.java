/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nchoosek;

/**
 *
 * @author hjia
 */
public class NChooseK {

    /**
     * @param args the command line arguments
     */
    private static int n;
    private static int k;

    public static void main(String[] args) {
        n = 17;
        k = 10;
        if (handleExceptions() == -1) {
           System.out.println( nCk(k,n));
        } else {
            System.out.println(handleExceptions());
        }

    }
/**
 * This will always be an integer cause every recursion is a breakdown nCk.
 * e.g. C(33,66) can be break into C(65,32)*66/33
 * and c65,32 can be break into c64,31. 
 * lastly, C35,2 can be break into c34,1.
 * So that if every nCk problem has a integer result. 
 * Then the (( nCk(k - 1, n - 1)*n) would always be dividable by k!
 * @param k
 * @param n
 * @return 
 */
    public static long nCk(int k, int n) {
        if (k == 0) {
            return 1;
        } else {
            return nCk(k - 1, n - 1)*n/ k;
        }
    }

    public static int handleExceptions() {
        if (n == k) {
            return 1;
        } else if (k == 1) {
            return n;
        } else if (n <= 0 || k <= 0 || k > n) {
            return 0;
        } else {
            return -1;
        }
    }
}

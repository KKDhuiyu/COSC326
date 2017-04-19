
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        n = 66;
        k = 33;
       if (handleExceptions() == -1) {
            BigInteger bigK = BigInteger.valueOf(""+k);
            BigInteger bigN = BigInteger.valueOf(""+n);
            System.out.println(nCk(k, n));
        } else {
            System.out.println(handleExceptions());
        }
//       test();
    }

    /**
     * This will always be an integer cause every recursion is a breakdown nCk.
     * e.g. C(33,66) can be break into C(65,32)*66/33 and c65,32 can be break
     * into c64,31. lastly, C35,2 can be break into c34,1. So that if every nCk
     * problem has a integer result. Then the (( nCk(k - 1, n - 1)*n) would
     * always be dividable by k!
     *
     * @param k
     * @param n
     * @return
     */
//    public static long nCk(long k, long n) {
//        if (k == 0) {
//            return 1L;
//        } else {
//            return nCk(k - 1, n - 1) * n / k;
//        }
//    }

    public static BigInteger factorial(n) {
    
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
//        public static void test() {
//        BigInteger big1 = BigInteger.valueOf("1");
//        BigInteger big2 = BigInteger.valueOf("9999");
//        big1.plus(big2.getDigits());
//        System.out.println(big1.toString());
//        System.out.println(big1.toDecimalString());
//        
//        BigInteger big3 = BigInteger.valueOf("100100001");
//        BigInteger big4 = BigInteger.valueOf("8");
//        System.out.println(big3.toString());
//        System.out.println(big4.toDecimalString());
//        big3.multiply(big4.getDigits());
//        System.out.println(big3.toString());
//        System.out.println(big3.toDecimalString());
//        BigInteger big5 = BigInteger.valueOf("123123123123");
//        big5.divide(2);
//        System.out.println(big5.toString());
//        System.out.println(big5.toDecimalString());
//    }
}

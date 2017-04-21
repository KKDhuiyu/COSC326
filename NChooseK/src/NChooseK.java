
/**
 * The main class that perform the nCk calculation by using the BigInteger.
 * as a support class.
 *
 * @author hjia
 */
public class NChooseK {

    /**
     * @param args args[0] =n args[1] =k
     */
    private static int n;
    private static int k;

    public static void main(String[] args) {
        n = Integer.valueOf(args[0]);
        k = Integer.valueOf(args[1]);
        if (handleExceptions() == -1) {
            if (2 * k < n) { // was going to do the trick but no...
                k = n - k;
            }
            // n!
            BigInteger member = factorial(n);
            //n!/k!
            for (int i = 1; i <= k; i++) {
                member.divide(i);
            }
            //n!/k!/(n-k)!
            for (int i = 1; i <= n - k; i++) {
                member.divide(i);
            }
            //print
            System.out.println(member.toDecimalString());
        } else {
            System.out.println(handleExceptions());
        }

//        System.out.println("7219428434016265740");
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
    /**
     * return a BigInteger object that has the value of n!
     *
     * @param n
     * @return
     */
    public static BigInteger factorial(int n) {
        BigInteger bigResult = new BigInteger();
        bigResult = BigInteger.valueOf("" + 1);
        for (int i = 1; i <= n; i++) {
            int[] tempArray = {i};
            bigResult.multiply(tempArray);
        }
        return bigResult;
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
//        BigInteger big5 = BigInteger.valueOf("1814400");
//         big5.divide(3);
//         System.out.println("right: "+ 604800);
//        System.out.println(big5.toDecimalString());
//         big5.divide(4);
//         System.out.println("right: "+ 151200);
//        System.out.println(big5.toDecimalString());
//         big5.divide(5);
//         System.out.println("right: "+ 30240);
//        System.out.println(big5.toDecimalString());
//        
//        BigInteger big6 = BigInteger.valueOf("100801200");
//         big6.divide(3);
//         System.out.println("right: "+ 33600400);
//        System.out.println(big6.toDecimalString());
//    }
}


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
        n = 65;
        k = 4;
//       if (handleExceptions() == -1) {
//            BigInteger bigK = BigInteger.valueOf(""+k);
//            BigInteger bigN = BigInteger.valueOf(""+n);
//            System.out.println(nCk(k, n));
//        } else {
//            System.out.println(handleExceptions());
//        }
//test();
//   // System.out.println(factorial(33).toDecimalString());
if(2*k<n){
    k=n-k;
}
BigInteger member = factorial(n);
//BigInteger kFactorial= factorial(k);
//BigInteger nMinusKFactorial= factorial(n-k);
//kFactorial.multiply(nMinusKFactorial.getDigits());
//BigInteger denominator= kFactorial;
//int memberLen = member.toDecimalString().length();
//int denomiatorLen = denominator.toDecimalString().length();
//int gap=memberLen-denomiatorLen;
//System.out.println(member.toDecimalString());
//System.out.println(denominator.toDecimalString());
//System.out.println(gap);
//BigInteger result = new BigInteger();
//result = BigInteger.valueOf("1");
//String temp="1";
//if(gap>2){
//    
//    for(int i=0; i< gap-2;i++){
//        temp+="0";
//    }
//}
for(int i=1;i<=k;i++){
    member.divide(i);
}
for(int i=1;i<=n-k;i++){
    member.divide(i);
}


System.out.println(member.toDecimalString());

 System.out.println("7219428434016265740");


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

    public static BigInteger factorial(int n) {
        BigInteger bigResult = new BigInteger();
        bigResult = BigInteger.valueOf(""+1);
        for(int i=1 ;i<=n;i++){
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
        public static void test() {
        BigInteger big1 = BigInteger.valueOf("1");
        BigInteger big2 = BigInteger.valueOf("9999");
        big1.plus(big2.getDigits());
        System.out.println(big1.toString());
        System.out.println(big1.toDecimalString());
        
        BigInteger big3 = BigInteger.valueOf("100100001");
        BigInteger big4 = BigInteger.valueOf("8");
        System.out.println(big3.toString());
        System.out.println(big4.toDecimalString());
        big3.multiply(big4.getDigits());
        System.out.println(big3.toString());
        System.out.println(big3.toDecimalString());
        BigInteger big5 = BigInteger.valueOf("1814400");
         big5.divide(3);
         System.out.println("right: "+ 604800);
        System.out.println(big5.toDecimalString());
         big5.divide(4);
         System.out.println("right: "+ 151200);
        System.out.println(big5.toDecimalString());
         big5.divide(5);
         System.out.println("right: "+ 30240);
        System.out.println(big5.toDecimalString());
        
        BigInteger big6 = BigInteger.valueOf("100801200");
         big6.divide(3);
         System.out.println("right: "+ 33600400);
        System.out.println(big6.toDecimalString());
    }
}

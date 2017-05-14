/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author hjia
 */
public class NChooseK {

    /**
     * @param args the command line arguments
     */

static ArrayList<Long> ns= new ArrayList<>();
  static  ArrayList<Long> ks= new ArrayList<>();
    public static void main(String[] args) {
        long n = 66L;
        long k = 33L;
        
        n = (long)Integer.valueOf(args[0]);
        k = (long)Integer.valueOf(args[1]);
        // init two array list
        if(k<n-k){
        k=n-k;
    }
        for(int i= (int) (k+1);i<=n;i++){
        ns.add((long)i);
    }
    for(int i=2;i<=n-k;i++){
        ks.add((long)i);
    }
    
        if (handleExceptions(n,k) == -1) {
             while(!ks.isEmpty()&& ns.size()>1){
             boolean dividable = nCk(k,n);
             if(!dividable ){ 
// when no elements in ns can be divided by elements in ks
                 boolean refillable =refillArray();
                 if(!refillable){
                     // when elements in ks is not breakable.
                ns.set(0, ns.get(0)*ns.get(1));
                ns.remove(1);
                 }
             }
//              System.out.println(Arrays.toString(ns.toArray()));
//        System.out.println(Arrays.toString(ks.toArray()));
        }
      
        
        long result=1L;
        for(int i=0;i<ns.size();i++){
            if(ns.get(i)!=0){
            result*=ns.get(i);
            }
        }
        System.out.println(result);
        ns.clear();
        ks.clear();
 //test();

        } else {
            System.out.println(handleExceptions(n,k));
        }
//          System.out.println(Arrays.toString(ns.toArray()));
//        System.out.println(Arrays.toString(ks.toArray()));
//          
          
       
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
//    public static long nCk(long k, long n) {
//        if (k == 0) {
//            return 1;
//        } else {
//            return nCk(k - 1, n - 1)*n/ k;
//        }
//    }
    /**
     * use this method to test.
     */
public static void test(){
        for(int i=20;i<=66;i++){
            long n=i;
            for(int j=3;j<=n/2;j++){
                long k=j;
                ns.clear();
        ks.clear();
                for(int c= (int) (k+1);c<=n;c++){
        ns.add((long)c);
    }
    for(int y=2;y<=n-k;y++){
        ks.add((long)y);
    }
                while(!ks.isEmpty()&& ns.size()>1){
             boolean dividable = nCk(k,n);
             if(!dividable ){
                 boolean refillable =refillArray();
                 if(!refillable){
                ns.set(0, ns.get(0)*ns.get(1));
                ns.remove(1);
                 }
             }
//              System.out.println(Arrays.toString(ns.toArray()));
//        System.out.println(Arrays.toString(ks.toArray()));
        }
      
        
        long result=1L;
        for(int x=0;x<ns.size();x++){
            if(ns.get(x)!=0){
            result*=ns.get(x);
            }
        }
        System.out.println(result);
        ns.clear();
        ks.clear();
            }
        }
    }
/**
 * do the division to k to N elements if it can be divided by any 
 * number from 1 to (n-k)
 * @param k
 * @param n
 * @return 
 */
public static boolean nCk(long k, long n){
    long result=0;
if(ks.isEmpty()){
    return false;
}
    for(int i=ks.size()-1; i>=0;i--){
        for(int j=ns.size()-1; j>=0;j--){
            if(ns.get(j)%ks.get(i)==0){
                ns.set(j, ns.get(j)/ks.get(i));
                ks.remove(i);
                return true;
            }
        }
    }
    return false;
}
/**
 * break down the k array into k's divider and the result after dividing.
 * @return can be break down.
 */
public static boolean refillArray(){
    boolean flag = false;
    for(int i=ks.size()-1; i>=0;i--){
        for(int j=2;j<ks.get(i);j++){
            if(ks.get(i)%j==0){
                long temp=ks.get(i);
                ks.remove(i);
                ks.add((long)j);
                
                ks.add(temp/j);
                return true;
            }
        }
        
    }
    return flag;
}
    public static long handleExceptions(long n, long k) {
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

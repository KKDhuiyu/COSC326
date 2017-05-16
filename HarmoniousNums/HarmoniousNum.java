
public class HarmoniousNum {

    /**
     * @param args the command line arguments
     */
    private static final int LOWERBOUND = 2000000;
    private static int upperBound=6855551;

    public static void main(String[] args) {
//        upperBound=findUpperBound();
       // System.out.println(upperBound);
        int [] sums = new int[upperBound+1]; //exception without +1 ???
        for (int i = 2; i < upperBound/2; i++){
      for (int j = 2*i; j < upperBound; j+=i){
        sums[j] += i;
      }
    }
         for(int i=0; i<LOWERBOUND;i++){
            if(i==sums[sums[i]] && i<sums[i]){
                System.out.print(i+" "+sums[i]+"\n");
            }
        }

    }


//    public static int findUpperBound(){
//        int [] sums = new int[LOWERBOUND];
//        for (int i = 2; i < LOWERBOUND/2; i++){
//      for (int j = 2*i; j < LOWERBOUND; j+=i){
//        sums[j] += i;
//      }
//    }
//        int upperBound=0;
//        for(int i=0; i<LOWERBOUND;i++){
//            if(sums[i]>upperBound){
//                upperBound=sums[i];
//            }
//        }
//        return upperBound;
//    }

}

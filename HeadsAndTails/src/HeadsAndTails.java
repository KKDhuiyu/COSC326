
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author hjia
 */
public class HeadsAndTails {

    private static int heads = 4;
    private static int tails = 4;
    private static int total;
    private static ArrayList<String> headsAndTails;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int steps = 0;

//        Scanner sc = new Scanner(System.in);
//        System.out.print("Number of heads: ");
//        heads = sc.nextInt();
//        System.out.print("Number of tails: ");
//        tails = sc.nextInt();
        total = heads + tails;
        // if it can't be balanced.
        if (heads - tails > 1 || heads - tails < -1) {
            System.out.println("impossible");
            return;
        }

        // create an arraylist depending on the sum of heads and tails
        initArrayList();
        if (total == 2) { // if it's HT
            System.out.println("Steps: " + steps);
            return;
        }
        //while (!isInOrder(1,total)) {
            arrange(heads,tails);
            steps++;
            arrange(heads,tails);
            printArrayList();
            steps++;
            arrange(heads,tails);
            printArrayList();
            arrange(heads,tails);
            printArrayList();
            steps++;
        //}
        System.out.println("Steps: " + steps);
    }

    /**
     * rearrange the coins.
     */
    public static void arrange(int heads, int tails) {
       
            if(headsAndTails.get(1).equals("H")&&headsAndTails.get(2).equals("H")){
                headsAndTails.add("H");headsAndTails.add("H");
                headsAndTails.set(1, "_");headsAndTails.set(2, "_");
                 printArrayList();
            } else {
                int startIndex = total / 2 - 1;
                int index = indexOfThreeTs(startIndex);
                int space = headsAndTails.indexOf("_");
                if(space==total-1){
                     headsAndTails.set(space,headsAndTails.get(0) );
                        headsAndTails.set(space+1, headsAndTails.get(1));
                        headsAndTails.set(0,"_");
                         headsAndTails.set(1,"_");
                }
                if (index == -1) {
                    String temp;
                    
                    if (space % 2 == 0) {
                        temp = "TH";
                        for (int i = space;i<total;i++) {
                            if (headsAndTails.get(i).equals("T") && (i+1) % 2 == 0
                                    && headsAndTails.get(i+1).equals("H")&& (i)% 2 != 0) {
                                headsAndTails.set(space, "T");
                                headsAndTails.set(space+1, "H");
                                headsAndTails.set(i, "_");
                                headsAndTails.set(i+1, "_");
                            }
                        }
                    } else {
                        temp = "HT";
                        for (int i = space;i<total;i++) {
                            if (headsAndTails.get(i).equals("H") && (i) % 2 == 0
                                    && headsAndTails.get(i+1).equals("T")&& (i+1)% 2 != 0) {
                                headsAndTails.set(space, "H");
                                headsAndTails.set(space+1, "T");
                                headsAndTails.set(i, "_");
                                headsAndTails.set(i+1, "_");
                            }
                        }
                    }

               }else{
                    if(space<total/2){
                        headsAndTails.set(space, "T");
                        headsAndTails.set(space+1, "T");
                        headsAndTails.set(index, "_");
                        headsAndTails.set(index+1, "_");
                    }
                }
            }
        
    }
public static int indexOfThreeTs(int startIndex){
     for(int i=startIndex;i<total;i++){
         if(headsAndTails.get(i).equals("T")&&headsAndTails.get(i+1).equals("T")&&
                 headsAndTails.get(i+2).equals("T")){
             return i;
         }
     }
     return -1;
}
public static int indexOfReversedHT(){
     for(int i=total+2;i>=1;i--){
         if(headsAndTails.get(i).equals("T")&&i%2==0&&
                 headsAndTails.get(i+2).equals("T")){
             return i;
         }
     }
     return -1;
}
    /**
     * initialize and print the array list based on the sum of the heads and
     * tails.
     */
    public static void initArrayList() {
        headsAndTails = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            if (i < heads) {
                headsAndTails.add("H");
            } else {
                headsAndTails.add("T");
            }
        }
        printArrayList();
    }

    /**
     * check if the list before index n is in the required order.
     *
     * @param n the index of the list
     * @return a boolean that represents if its in order.
     */
    public static boolean isInOrder(int start,int end) {
        if(start==0){
            start =1;
        }
        for (int i = start; i < end; i++) {
            // can be faster here
            if (headsAndTails.get(i).equals(headsAndTails.get(i - 1))) {
                return false;
            }
        }
        return true;
    }

    public static void printArrayList() {
        String result = "";
        for (String s : headsAndTails) {
            result += s+" ";
        }
        System.out.println(result);
    }
}

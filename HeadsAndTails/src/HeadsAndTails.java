
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Huiyu Jia, Jason Zhao.
 */
public class HeadsAndTails {

    private static ArrayList<String> headsAndTails;
    private static int heads = 4;
    private static int tails = 4;
    private static int total = 0;
    public static ArrayList<ArrayList<String>> tree = new ArrayList<>();
    public static String solutionString = "";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            ArrayList<String> coins = new ArrayList<>();

            int numLetters;
            String coinSide;
            ArrayList<String> coinS = new ArrayList<>();

            System.out.print("Number of heads: ");
            heads = sc.nextInt();
            System.out.print("Number of tails: ");
            tails = sc.nextInt();
            total = heads + tails;
            // if it can't be balanced.
            if (heads - tails > 1 || heads - tails < -1) {
                System.out.println("impossible");
                return;
            }
            initArrayList();
            defineSolution();
            for (int i = 0; i < heads; i++) {
                coins.add("H");
            }
            for (int i = 0; i < tails; i++) {
                coins.add("T");
            }
            coins.add(0, "_");
            coins.add(1, "_");
            coins.add("_");
            coins.add("_");
            int steps = 0;
            int count = 0;
            boolean isInOrder = false;
//        while (isInOrder == false) {
//             ArrayList<String> para2 = new ArrayList<>();
//            if (count == total - 1) {
//                count = 0;
//                steps++;
//            }
//            if (sorting(coins, para2, "", 0, total, total) == 1) {
//                if (steps == 0) {
//                    steps++;
//                }
//                isInOrder = true;
//                return;
//            }
//
//            String treeN = tree.get(steps).get(count);
//            for (int i = 0; i < coins.size(); i++) {
//                coins.set(i, treeN.substring(i, i + 1));
//            }
//            count++;
//        }
//        if (steps == 0) {
//            steps = 1;
//        }

//steps+=2;
//steps+=heads/7;
            System.out.println(calculate());
        }
    }

//    public static int sorting(ArrayList<String> coins, ArrayList<String> combs, String c, int i, int T, int total) {
//        if (i == 0) {
//            for (int k = 0; k < coins.size(); k++) {
//                c += coins.get(k);
//            }
//        }
//        int gapI = coins.indexOf("_");
//        if (i == gapI) {
//            i += 2;
//        } else if (i == gapI + 1) {
//            i += 3;
//        }
//        if (total > 5) {
//            if (i == gapI + total + 2) {
//                i += 4;
//            } else if (i == gapI + total + 3) {
//                i += 5;
//            }
//        }
//        if (!combs.isEmpty()) {
//
//            if (T == 1) {
//                tree.add(new ArrayList<>(combs));
//                return 0;
//            }
//        }
//        if (i < gapI - 1) {
//            coins.set(gapI, coins.get(i));
//            coins.set(i, "_");
//            coins.set(gapI + 1, coins.get(i + 1));
//            coins.set(i + 1, "_");
//        } else if (i > gapI + 1) {
//            if (i < coins.size() - 1) {
//                coins.set(gapI, coins.get(i));
//                coins.set(i, "_");
//                coins.set(gapI + 1, coins.get(i + 1));
//                coins.set(i + 1, "_");
//            }
//        }
//
//        String temp = "";
//
//        for (int j = 0; j < coins.size(); j++) {
//            temp += coins.get(j);
//        }
//        combs.add(temp);
//        if (inOrder(temp, combs.size())) {
//            tree.add(new ArrayList<>(combs));
//            printSteps(tree.size(), total);
//
//            return 1;
//        }
//
//        for (int t = 0; t < c.length(); t++) {
//
//            coins.set(t, c.substring(t, t + 1));
//        }
//
//        for (int t = 0; t < c.length(); t++) {
//
//            coins.set(t, c.substring(t, t + 1));
//        }
//
//        return sorting(coins, combs, c, i + 1, T - 1, total);
//    }
//    
//    
//    public static boolean inOrder(String potSol, int cSize) {
//        int gap = potSol.indexOf("_");
//
//        if (gap == 0 || gap == potSol.length() - 2) {
//
//            String finalSolution = potSol.replaceAll("_", "");
//
//            if (finalSolution.equals(solutionString)) {
//                return true;
//            }
//        }
//        return false;
//    }
    public static int calculate() {

        int s = 0;
        if (heads == tails) {

            if (heads == 3) {
                s = 3;
            } else {
                s = ((heads / 2) - 1) * 2;
                if (heads % 2 != 0) { //odd
                    s += 3;
                } else { //even
                    s += 2;
                }
            }
        }else{
            int max= Math.max(heads, tails);
            s = ((max / 2) - 1) * 2;
            
            if (max % 2 != 0) { //odd
                    s += 3;
                } else { //even
                    s += 2;
                }
        }
        return s;
    }

    public static void printSteps(int treeSize, int total) {
        int count = 1;
        while (treeSize / (total - 1) != 0) {

            treeSize = treeSize / (total - 1);
            count++;
        }

        System.out.println("Steps: " + count);
    }

    /**
     * initialize the array list based on the sum of the heads and tails.
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
    }

    public static void defineSolution() {
        int h = heads, t = tails;
        while (t > 0 || h > 0) {
            if (h < t) {
                solutionString += "T";
                t--;
                if (h > 0) {
                    solutionString += "H";
                    h--;
                }
            } else {
                if (h > 0) {
                    solutionString += "H";
                    h--;
                }
                if (t > 0) {
                    solutionString += "T";
                    t--;
                }
            }
        }
    }

}

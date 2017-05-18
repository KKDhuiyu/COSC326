
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 *
 * @author Huiyu Jia, Jason Zhao, Celine Ahkit, Sam Grant.
 */
public class ColoringMaps {

    /**
     * @param args the command line arguments
     */
    private static ArrayList<int[]> adjacentList;
    private static ArrayList<Integer> colorList;
    private static Map<Integer, Integer> contriesAndItsColor;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String adjacencyCountries = sc.nextLine();//read contries
            adjacentList = new ArrayList<>();
            while (!adjacencyCountries.equals("")) {
                String[] stringArray = adjacencyCountries.split(" ");
                int[] adjacents = new int[stringArray.length];
                for (int i = 0; i < stringArray.length; i++) {
                    adjacents[i] = Integer.parseInt(stringArray[i]);
                }
                adjacentList.add(adjacents);
                if (sc.hasNextLine()) {
                    adjacencyCountries = sc.nextLine();
                } else {
                    adjacencyCountries = "";
// contries list followed by a empty line
                }
            }
            String colorLine = sc.nextLine();// read color
            String[] stringArray = colorLine.split(" ");

            colorList = new ArrayList<>();
            for (String s : stringArray) {
                colorList.add(Integer.parseInt(s));
            }
            if (colorList.size() == 0) {//if no color to assign
                System.out.println("Impossible");
                break;
            } else {
                contriesAndItsColor = new HashMap<>();
                for (int[] x : adjacentList) { // init map<Country,Color>
                    contriesAndItsColor.put(x[0], colorList.get(0));
                }
                for (int[] x : adjacentList) { // init map<Country,Color>
                    boolean hasClashes = assignColor();
                    while (hasClashes) {
                        hasClashes = assignColor();
                    }

                }
                if(hasClash()){ // still have clash after allocating.
                   System.out.println("Impossible"); 
                }else{
                System.out.println(contriesAndItsColor.toString());
                }
            }
        }
    }

    public static boolean hasClash() {
        
        for (int[] x : adjacentList) {
           for(int i:x){
               if(i!=x[0]){
                   if(contriesAndItsColor.get(x[0])==contriesAndItsColor.get(i)){ 
                       return true;
                   }
               }
           }
        }
        return false;
    }

//    public static boolean areAdjacency(int a, int b) {
//        boolean r = false;
//        boolean t = false;
//        for (int[] x : adjacentList) {
//            if (x[0] == a) {
//                for (int i : x) {
//                    if (i == b) {
//                        r = true;
//                    }
//                }
//            } else if (x[0] == b) {
//                for (int i : x) {
//                    if (i == a) {
//                        t = true;
//                    }
//                }
//            }
//        }
//        return r && t;
//    }
    /**
     * breadth first search 
     * @return 
     */
    public static boolean assignColor() {
        boolean hasClashes = false;
        for (int[] x : adjacentList) {
            int colorsIndex = 0;
            for (int i = 0; i < x.length; i++) {
                int tempIndex = 
                        colorList.indexOf(contriesAndItsColor.get(x[i]));
                colorsIndex = Math.max(colorsIndex, tempIndex);
            }

            for (int i = 1; i < x.length; i++) {
                if (contriesAndItsColor.get(x[0])
                        == contriesAndItsColor.get(x[i])
                        && colorsIndex + 1 < colorList.size()) {
                    contriesAndItsColor.put(x[i],
                            colorList.get(colorsIndex + 1));
                    hasClashes = true;
                }
            }
            if (hasClashes) {
                return hasClashes;
            }
        }
        return hasClashes;
    }

}

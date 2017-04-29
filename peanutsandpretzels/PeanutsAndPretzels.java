
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Huiyu Jia, Declan Bartlett, Jason Zhao, Celine Ahkit.
 */
public class PeanutsAndPretzels {

    /**
     * @param args the command line arguments
     */
    static int count=0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            ArrayList<String> inputs = new ArrayList<>();
            while(sc.hasNextLine()){
                String rule = sc.nextLine();
                if(rule.equals("")) break;
                inputs.add(rule);
            }
            if(inputs.size()==0)break;
            int peanuts = Integer.parseInt(inputs.get(0).split(" ")[0]);    
            int pretzels= Integer.parseInt(inputs.get(0).split(" ")[1]); 
            if(peanuts<0||pretzels<0||
                    (peanuts==0 && pretzels==0)|| 
                    peanuts>1000||pretzels>1000){
                System.err.println("the bowl can't be empty and "
                        + "there can't be more than 1000 peanutes or 1000"
                        + " pretzels in the bowl");
            }
            inputs.remove(0);
            ArrayList<String> rules = validRules(peanuts,pretzels,inputs);
            //System.out.println(peanuts + " " + pretzels + " " + rules);
            boolean hasFreeDrink=false;
            for (String s : rules) {
                if(isFirmWin(peanuts, pretzels, rules, s,true)){
                    hasFreeDrink= true;
                    System.out.println(s);
                }
                //System.out.println(s+ ".......");
            }
            if(!hasFreeDrink){
                System.out.println("0 0");
            }
        }
    }
    /**
     *  This is a recursive method that use && to confirm there are no lose
     * moves under a certain start point. 
     * @param peanuts
     * @param pretzels
     * @param rules all valid start moves 
     * @param firstMove the first move and the next move 
     * @param isMyTurn keep tracking if it's my turn to take a move
     * @return 
     */
     public static boolean isFirmWin(int peanuts, int pretzels,
             ArrayList<String> rules, String firstMove,boolean isMyTurn){
         int pean = peanuts;
         int pret = pretzels;
         String move = firstMove;
         pean -= Integer.parseInt(move.split(" ")[0]);
         pret -= Integer.parseInt(move.split(" ")[1]);
         boolean nextTurn= !isMyTurn; 
         boolean win=true;
         ArrayList<String> validRules = new ArrayList<>();
         for(String s: rules){
             if(Integer.parseInt(s.split(" ")[0])<=pean &&Integer.parseInt(s.split(" ")[1])<=pret){
                 validRules.add(s);
             }
         }
         // 5 conditions to handle exceptions 
         // so that the program will not waste time on rules 0,1 and 1,0. 
         if(validRules.size()==2 && validRules.contains("0 1")&& 
                 validRules.contains("1 0")&& !isMyTurn && (pean+pret)%2==0){
             return false;
         }else if(validRules.size()==1 && (validRules.contains("0 1")|| 
                 validRules.contains("1 0"))&& !isMyTurn && (pean+pret)%2==0){
             return false;
         }else if(validRules.size()==2 && validRules.contains("0 1")&& 
                 validRules.contains("1 0")&& isMyTurn && (pean+pret)%2!=0){
             return false;
         }else if(validRules.size()==1 && (validRules.contains("0 1")|| 
                 validRules.contains("1 0"))&& isMyTurn && (pean+pret)%2!=0){
             return false;
         }else if((validRules.size()==2 && validRules.contains("0 1")&& 
                 validRules.contains("1 0"))||(validRules.size()==1 && (validRules.contains("0 1")|| 
                 validRules.contains("1 0")))){
             return true;
         }
        
        // if the bowl is empty and the next turn is my turn
         if(pean==0 && pret==0 && !isMyTurn){
             return false;
         }
         
         for(String s: rules){
             // if it's a valid move
             if(Integer.parseInt(s.split(" ")[0])<=pean &&
                     Integer.parseInt(s.split(" ")[1])<=pret){  
                 // use AND to find anwser
                win=win&& isFirmWin(pean,pret,rules,s,nextTurn);
                
             }
         }
          
         return win;
     }

 /**
  * Find all possible moves.
  * @param peanuts the initial number of peanuts
  * @param pretzels the initial number of pretzels
  * @param rules the array list of string of valid moves 
  * @return the string list
  */
    public static ArrayList<String> validRules(int peanuts, int pretzels,
            ArrayList<String> rules) {
        ArrayList<String> validRules = (ArrayList<String>) rules.clone();
        for(int i=rules.size()-1; i>=0;i--){ // eliminate invalid rules
            String rule= validRules.get(i);
            String[] tempRules = rule.split(" ");
            int pean=Integer.parseInt(tempRules[0].substring(1));
            int pret=Integer.parseInt(tempRules[1].substring(1));
            String peanRule = tempRules[0].substring(0,1);
            String pretRule = tempRules[1].substring(0,1);
            if(peanRule.equals(">") && pean >= peanuts){
               validRules.remove(i);
            }else if(peanRule.equals("=") && pean > peanuts){
                validRules.remove(i);
            }else if(pretRule.equals(">") && pret >= pretzels){
               validRules.remove(i);
            }else if(pretRule.equals("=") && pret > pretzels){
              validRules.remove(i);
            }
        }
        ArrayList<String> result = new ArrayList<>();
        // nice conditions to find valied moves
        for(int i=0; i< validRules.size();i++){ 
            String rule= validRules.get(i);
            String[] tempRules = rule.split(" ");
            int pean=Integer.parseInt(tempRules[0].substring(1));
            int pret=Integer.parseInt(tempRules[1].substring(1));
            String peanRule = tempRules[0].substring(0,1);
            String pretRule = tempRules[1].substring(0,1);
            if(peanRule.equals("=")&&pretRule.equals("=")){
                String temp = pean+" "+pret;
                result.add(temp);
            }else if(peanRule.equals("=")&&pretRule.equals(">")){
                String temp = pean+" ";
                for(int j =pret+1; j <pretzels; j++){
                    result.add(temp+j);
                }
            }else if(peanRule.equals("=")&&pretRule.equals("<")){
                String temp = pean+" ";
                for(int j =pret-1; j >=0; j--){
                    result.add(temp+j);
                }
            }else if(peanRule.equals(">")&&pretRule.equals("=")){
                String temp = " "+pret;
                for(int j =pean+1; j <peanuts; j++){
                    result.add(j+temp);
                }
            }else if(peanRule.equals(">")&&pretRule.equals("<")){
               
                for (int j = pean + 1; j < peanuts; j++) {
                    String temp = j + " ";
                    for (int k = pret - 1; k >= 0; k--) {
                        result.add(temp + k);
                    }
                }
            }else if(peanRule.equals(">")&&pretRule.equals(">")){
               
                for (int j = pean + 1; j < peanuts; j++) {
                    String temp = j + " ";
                    for (int k = pret + 1; k <pretzels; k++) {
                        result.add(temp + k);
                    }
                }
            }else if(peanRule.equals("<")&&pretRule.equals("=")){
                String temp = " "+pret;
                for(int j =pean-1; j >=0; j--){
                    result.add(j+temp);
                }
            }else if(peanRule.equals("<")&&pretRule.equals(">")){
               
                for (int j =pean-1; j >=0; j--) {
                    String temp = j + " ";
                    for (int k = pret + 1; k <pretzels; k++) {
                        result.add(temp + k);
                    }
                }
            }else if(peanRule.equals("<")&&pretRule.equals("<")){
               
                for (int j =pean-1; j >=0; j--) {
                    String temp = j + " ";
                    for (int k = pret - 1; k >= 0; k--) {
                        result.add(temp + k);
                    }
                }
            }
        }
        result.add("0 1");
        result.add("1 0");
        Set<String> hs = new HashSet<>(); 
// hashset helps us to remove duplicates 
        hs.addAll(result);
        result.clear();
        result.addAll(hs);
        if(result.contains("0 0")){
            result.remove(result.indexOf("0 0"));
        }
         
        return result; // moves are in format  "n n"
    }
}

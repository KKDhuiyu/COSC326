/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peanutsandpretzels;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author hjia
 */
public class PeanutsAndPretzels {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            ArrayList<String> inputs = new ArrayList<>();
            while(sc.hasNextLine()){
                String rule = sc.nextLine();
                if(rule.equals("")) break;
                inputs.add(rule);
            }
            if(inputs.size()==0)break;
            int peanutes = Integer.parseInt(inputs.get(0).split(" ")[0]);    
            int pretzels= Integer.parseInt(inputs.get(0).split(" ")[1]); 
            if((peanutes==0 && pretzels==0)|| peanutes>1000||pretzels>1000){
                System.err.println("the bowl can't be empty and "
                        + "there can't be more than 1000 peanutes or 1000"
                        + " pretzels in the bowl");
            }
            inputs.remove(0);
            ArrayList<String> rules = inputs;
            System.out.println(peanutes+" "+pretzels+" "+ rules);
        }
    }

    public static int turn(int peanuts, int pretzels, ArrayList<String> rules,
            boolean myStep) {
        int nuts = peanuts;
        int prets = pretzels;
        rules = validRules(nuts, prets, rules); // filt out the valid rules
        if (rules.isEmpty() && !myStep) {
            return 0; // free drink!
        } else if (rules.isEmpty() && myStep) {
            return -1; // pay for the drink :( 
        } else {
            for (String rule : rules) {
                // use the rule to decrease the number of snacks
                // this function will find all possible moves according to the rule
                 ArrayList<Integer> numsOfSnacks = applyRule(rule,peanuts,pretzels);
                for (int i = 0; i < (numsOfSnacks.size()) / 2; i += 2) {
                    nuts = numsOfSnacks.get(i);
                    prets = numsOfSnacks.get(i+1);

                    int won = turn(nuts, prets, rules, !myStep); // got free drink!
                    if (won == 0) {
                        System.out.println((peanuts - nuts) + " " + (pretzels - prets));
                    }
                }
            }
        }
        return 1; // this should not happen? 
    }

    public static ArrayList<String> validRules(int peanuts, int pretzels,
            ArrayList<String> rules) {
        for(int i=0; i<rules.size ();i++){
            String rule= rules.get(i);
            if(rule.substring(0,1).equals(">") && 
                    Integer.parseInt(rule.substring(1,2)) >= peanuts){
                rules.remove(i);
            }else if(rule.substring(0,1).equals("=") && 
                    Integer.parseInt(rule.substring(1,2)) > peanuts){
                rules.remove(i);
            }else if(rule.substring(3,4).equals(">") && 
                    Integer.parseInt(rule.substring(4,5)) >= pretzels){
                rules.remove(i);
            }else if(rule.substring(3,4).equals("=") && 
                    Integer.parseInt(rule.substring(4,5)) > pretzels){
                rules.remove(i);
            }
        }
        return rules;
    }

    public static ArrayList<Integer> applyRule(String rule, int peanuts, int pretzels) {
        ArrayList<Integer> rules = new ArrayList<>();
        rules.add(1);
        rules.add(0);
        rules.add(0);
        rules.add(1); //4 default rules
        if (rule.substring(0, 1).equals("=")) {
            Integer.parseInt(rule.substring(1, 2));

        }
        return rules;
    }
}

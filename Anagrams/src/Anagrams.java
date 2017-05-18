
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Huiyu Jia. Jason Zhao.
 */
public class Anagrams {

    private static ArrayList<String> combination = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HashMap<Integer, ArrayList<String>> dictionary;

        Scanner sc = new Scanner(System.in);
        int maxWords;
        int inputStringLength;
        String sortedInputString;
        String input = args[0];
        String s1 = args[0].toLowerCase().replaceAll("[^a-z]", "");
        inputStringLength = s1.length();
        maxWords = Integer.valueOf(args[1]);
        sortedInputString = sortCharInString(s1);
        dictionary = new HashMap<>();

        while (sc.hasNextLine()) { // read input and store valid word in map
            String word = sc.nextLine().toLowerCase().replaceAll("[^a-z]", "");
            boolean flag = true; // the word has no char not in inputString
            for (int i = 0; i < word.length(); i++) {
                if (sortedInputString.indexOf(word.charAt(i)) == -1) {
                    flag = false;
                }
            }
            int len = word.length();
            if (flag) {
                if (len != 0 && len <= inputStringLength) {
                    //length of the word shorter than inputStringLength 
                    //nor empty
                    if (dictionary.containsKey(len)) {
                        if (!dictionary.get(len).contains(word)) {
                            dictionary.get(len).add(word);
                        }
                    } else {
                        dictionary.put(len, new ArrayList<>());
                        dictionary.get(len).add(word);
                    }
                }
            }
        }

        for (int i = 1; i <= inputStringLength; i++) { //sort list in map          
            if (dictionary.containsKey(i)) {
                ArrayList<String> temp2 = dictionary.get(i);
                Collections.sort(temp2);
                dictionary.put(i, temp2);
                //  System.out.println(Arrays.toString(temp2.toArray()));
            }
        }

        for (int i = inputStringLength; i > 0; i--) { //for each word in dic         
            if (dictionary.containsKey(i)) {
                ArrayList<String> temp2 = dictionary.get(i);
                for (String s : temp2) {
                    String word = s;
                    findAllCombination(maxWords - 1, 
                            inputStringLength, dictionary, word, input);
                }
            }
        }

        ArrayList<String[]> result = new ArrayList<>();
        ArrayList<String> result2 = new ArrayList<>();
       
        for (String s : combination) {//remove duplicates 
            if (isAnagrams(s1, s)
                    && !s.equals(input.toLowerCase().
                            replaceAll("[^a-z\\s]", ""))) {
                boolean flag = true;
                for (String[] sa : result) {
                    if (compareArrays(sa, s.split(" ")) ||
                            compareArrays(input.toLowerCase().
                            replaceAll("[^a-z\\s]", "").split(" "),
                                    s.split(" "))) {
                        flag = false;
                    }
                }
                if (flag) {
                   result.add(s.split(" "));
                    result2.add(s);
                }

            }
        }
        boolean inOrder= sortResult(result2,maxWords);
        while(!inOrder){//sort 
            inOrder= sortResult(result2,maxWords);
        }
        for (int i = 1; i <=maxWords; i++) {//print in order
            for (String ss : result2) {
                if (ss.split(" ").length == i) {
                    System.out.println(ss);

                }
            }
        }

    }
    /**
     * sort the result array
     * @param result
     * @param maxwords
     * @return if its in order
     */
    public static boolean sortResult(ArrayList<String> result, int maxwords){
        boolean flag=true;
         ArrayList<String[]> resultArray = new ArrayList<>();
         for(String s: result){
             resultArray.add(s.split(" "));
         }
        for(int i=1;i<=maxwords;i++){
            for(int j=0;j<resultArray.size()-1;j++){
                if(resultArray.get(j).length-1>i){
                    if(resultArray.get(j)[i-1].length()==
                            resultArray.get(j+1)[i-1].length() 
                            &&resultArray.get(j)[i].length()<
                            resultArray.get(j+1)[i].length()){
                        String[] temp= resultArray.get(j);
                        resultArray.set(j, resultArray.get(j+1));
                        resultArray.set(j+1, temp);//swap;
                        String tempS= result.get(j);
                        result.set(j, result.get(j+1));
                        result.set(j+1, tempS);
                        flag=false;
                    }
                }
            }
        }
        return flag;
    }
    
   
/**
 * return true if a equals b. 
 * @param a
 * @param b
 * @return 
 */
    public static boolean compareArrays(String[] a, String[] b) {
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            int x = Arrays.asList(b).indexOf(a[i]);
            if (x == -1) {
                return false;
            }
        }
        return true;
    }

    public static String sortCharInString(String s) {
        char[] charArray = s.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    public static boolean isAnagrams(String input, String wordsToTry) {
        HashMap<String, Integer> inputMap = new HashMap<>();
        HashMap<String, Integer> anotherMap = new HashMap<>();
        wordsToTry = wordsToTry.replaceAll("[^a-z]", "");
        // init inputMap
        for (int i = 0; i < input.length(); i++) {
            String chara = input.charAt(i) + "";
            if (inputMap.containsKey(chara)) {
                inputMap.put(chara, inputMap.get(chara) + 1);
            } else {
                inputMap.put(chara, 1);
            }
        }
        // init anotherMap
        for (int i = 0; i < wordsToTry.length(); i++) {
            String chara = wordsToTry.charAt(i) + "";
            if (anotherMap.containsKey(chara)) {
                anotherMap.put(chara, anotherMap.get(chara) + 1);
            } else {
                anotherMap.put(chara, 1);
            }
        }
        return inputMap.equals(anotherMap);
    }

    public static void findAllCombination(int maxWords,
            int len, HashMap<Integer, ArrayList<String>> map,
            String startWords, String input) {
        int startWordsLength = startWords.replaceAll("[^a-z]", "").length();
        if (startWordsLength == len) {
            if (isAnagrams(input, startWords)
                    && !startWords.equals(
                            input.toLowerCase().replaceAll("[^a-z\\s]", ""))) {
                //System.out.println(startWords);
            }
            combination.add(startWords);

        }
        if (startWordsLength >= len) {
            return;
        }

        if (maxWords == 0) {
            return;
        }
        int lastWordLength
                = startWords.split(" ")[startWords.split(" ").length - 1].
                        length();
        for (int i = lastWordLength; i > 0; i--) {
            if (map.containsKey(i)) {
                ArrayList<String> temp2 = (ArrayList<String>) map.get(i);
                for (String s : temp2) {
                    String words = startWords + " " + s;
                    // System.out.println(words);
                    findAllCombination(maxWords - 1, len, map, words, input);
                }
            }
        }

    }

    public class MyComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            if (o1.length() > o2.length()) {
                return 1;
            } else if (o1.length() < o2.length()) {
                return -1;
            }
            return o1.compareTo(o2);
        }
    }

}

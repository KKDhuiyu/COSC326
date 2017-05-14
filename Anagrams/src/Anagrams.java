
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hjia
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
                    findAllCombination(maxWords - 1, inputStringLength, dictionary, word, input);
                }
            }
        }

        ArrayList<String[]> result = new ArrayList<>();
        ArrayList<String> result2 = new ArrayList<>();
        for (String s : combination) {
            if (isAnagrams(s1, s)
                    && !s.equals(input.toLowerCase().replaceAll("[^a-z\\s]", ""))) {
                result.add(s.split(" "));
                result2.add(s);

            }
        }
        for (int i = 1; i <= maxWords; i++) {

            for (int j = 0; j < result.size() - 2; j++) {
                if (result.get(j).length == i) {
                    for (int k = j + 1; k < result.size() - 1; k++) {
                        if (result.get(k).length == i) {
                            if (compareArrays(result.get(j), result.get(j + 1))) {
                                result.remove(k);
                                result2.remove(k);
                                j -= 1;
                            }
                        }
                    }

                }
            }
        }
        for (int i = 1; i <= maxWords; i++) {
            for (String ss : result2) {
                if (ss.split(" ").length == i) {
                    System.out.println(ss);

                }
            }
        }

    }

    public static boolean compareArrays(String[] arr1, String[] arr2) {
        HashSet<String> set1 = new HashSet<>(Arrays.asList(arr1));
        HashSet<String> set2 = new HashSet<>(Arrays.asList(arr2));
        return set1.equals(set2);
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
            int len, HashMap<Integer, ArrayList<String>> map, String startWords, String input) {
        int startWordsLength = startWords.replaceAll("[^a-z]", "").length();
        if (startWordsLength == len) {
            if (isAnagrams(input, startWords)
                    && !startWords.equals(input.toLowerCase().replaceAll("[^a-z\\s]", ""))) {
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
                = startWords.split(" ")[startWords.split(" ").length - 1].length();
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

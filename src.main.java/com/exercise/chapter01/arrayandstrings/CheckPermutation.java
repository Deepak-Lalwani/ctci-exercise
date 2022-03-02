package com.exercise.chapter01.arrayandstrings;

import java.util.Arrays;

public class CheckPermutation {

    /* Solution 1
    /* Brute force solution, traverse through the big string and whenever we encounter a character from small string, we check
    /* for the permutation
    * */
    public static int checkPermutation_solution1(String smallStr, String bigStr) {

        // No of permutations
        int permutationCount = 0;


        String smallStrSorted = performMergeSort(smallStr);
        for(int i=0; i<= bigStr.length()-smallStr.length(); i++){
            //found any character of small string in big string check for the permutation
            if(smallStr.contains(String.valueOf(bigStr.charAt(i)))) {
                // Notice the last index of substring, its exclusive !!
                String substring = bigStr.substring(i, i+smallStr.length());
                String substringSorted = performMergeSort(substring);

                if(substringSorted.equals(smallStrSorted)) permutationCount++;
            }
        }
        return permutationCount;
    }

    // Sort a string
    public static String performMergeSort(String str) {
        //Sorting small string using merge sort
        char[] smallStrArr = str.toCharArray();
        IsUniqueChar.performMergeSort(smallStrArr, 0, str.length()-1);
        return Arrays.toString(smallStrArr);
    }

    /* Solution 1
    */

    /* Solution 2
    /* anagram solution, assuming both the string should be of equal length and permutation of each other
    * */
    public static boolean checkPermutation_solution2(String str1, String str2) {

        if(str1.length() != str2.length()) return false;

        int[] charArray= new int[128]; ///Assuming ASCII code
        for(int i=0; i<str1.length(); i++) {
            charArray[str1.charAt(i)]++;
        }

        for(int j=0; j<str2.length(); j++) {
            charArray[str2.charAt(j)]--;
            if(charArray[str2.charAt(j)] < 0) return false;
        }
        return true;

    }

    public static void main(String[] args) {
        //checkPermutation_solution1
//        String testSmallString1 = "abbc";
//        String testBigString1 = "cbabadcbbabbcbabaabccbabc";
//
//        int permutationCount = checkPermutation_solution1(testSmallString1, testBigString1);
//        System.out.println("permutationCount is  " + permutationCount);

        // checkPermutation_solution2
        String[][] pairs = {{"apple", "papel"}, {"carrot", "tarroc"}, {"hello", "llloh"}};
        for (String[] pair : pairs) {
            String word1 = pair[0];
            String word2 = pair[1];
            boolean anagram = checkPermutation_solution2(word1, word2);
            System.out.println(word1 + ", " + word2 + ": " + anagram);
        }
    }
}

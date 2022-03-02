package com.exercise.chapter01.arrayandstrings;

import java.util.HashMap;
import java.util.Map;
/*  The idea behind palindrome is that all the characters should appear at even number time,
    at most one character can appear odd times

    What does it take to be able to write a set of characters the same way forwards and backwards?
    We need to have an even number of almost all characters, so that half can be on one side and half can be on the other side.
    At most one character (the middle character) can have an odd count.
 */
public class PalindromePermutation {

    public static int getCharacterIndex(Character c){
        int initialIndex = Character.getNumericValue('a');
        int lastIndex = Character.getNumericValue('z');
        int characterIndex = Character.getNumericValue(c);

        if(characterIndex >= initialIndex && characterIndex <= lastIndex){
            return characterIndex - initialIndex;
        } else return -1;
    }

    public static String toBinary(int n, int len)
    {
        String binary = "";
        for (long i = (1L << len - 1); i > 0; i = i / 2) {
            binary += (n & i) != 0 ? "1" : "0";
        }
        return binary;
    }


    /*  Using a bit vector
    */
    public static boolean palindromePermutation_solution3(String str) {
        int checker = 0;
        for(int i=0; i<str.length(); i++) {
            int characterIndex = getCharacterIndex(str.charAt(i));
            if(characterIndex >=0 ) {
                int bitRepresentationOfChar = 1 << characterIndex;
                System.out.println("checker is " + toBinary(checker,32));
                System.out.println("bitRepresentationOfChar is " +  toBinary(bitRepresentationOfChar,32));
                System.out.println("checker |= bitRepresentationOfChar; is " + toBinary((checker | bitRepresentationOfChar), 32));
                System.out.println("checker &= ~bitRepresentationOfChar; is " + toBinary((checker & ~bitRepresentationOfChar), 32));
                if ((checker & bitRepresentationOfChar) == 0) {
                    System.out.println("Inside if");
                    checker |= bitRepresentationOfChar;
                }
                else {
                    System.out.println("Inside else");
                    checker &= ~bitRepresentationOfChar;
                }

            }
        }

        /*
            1. We can easily check that no bits in the integer are 1: just compare the integer to 0.

            2. There is actually a very elegant way to check that an integer has exactly one bit set to 1.
               Picture an integer like 00010000. We could of course shift the integer repeatedly to check that there's only a single 1.
               Alternatively, if we subtract 1 from the number, we'll get 00001111.
               What's notable about this is that there is no overlap between the numbers (as opposed to say 00101000, which, when we subtract 1 from, we get 00100111.)
               So, we can check to see that a number has exactly one 1 because if we subtract 1 from it and then AND it with the new number,
               we should get 0.
               00010000 - 1 = 00001111 00010000 & 00001111 = 0
        */
        return checker == 0 || (checker & (checker - 1)) == 0;
    }

    /*  Using an int array with size of 26 letters a to z and incrementing the counter whenever we encounter a character
        If there are more than one place in array where counter is odd then the string is not palindrome
    */
    public static boolean palindromePermutation_solution2(String str) {

        int[] array = new int[Character.getNumericValue('z')-Character.getNumericValue('a') + 1];
        int oddcount = 0;

        for(int i=0; i<str.length(); i++){
            int index = getCharacterIndex(str.charAt(i));
            if(index != -1) array[index]++;
        }

        for(int i=0; i<array.length; i++) {
            if(array[i]%2 != 0) oddcount++;
            if(oddcount > 1) return false;
        }
        return true;
    }

    public static boolean palindromePermutation_solution1(String str) {
        HashMap<Character, Integer> hmap = new HashMap<>();

        for(int i=0, count; i<str.length(); i++) {
            if(str.charAt(i) != ' ') {
                count = hmap.get(str.charAt(i)) == null ? 1 : hmap.get(str.charAt(i)) + 1;
                hmap.put(str.charAt(i), count);
            }
        }
        // for a string to be palindrome only one character could be of odd number
        int oddCount = 0;
        for(Map.Entry e: hmap.entrySet()){
            System.out.println("key is " + e.getKey() + " and value is " + e.getValue());
            if( (int)e.getValue()%2 != 0) oddCount++;
            if(oddCount > 1) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String testString1 = "Tact Coa";
        boolean result = palindromePermutation_solution3(testString1.toLowerCase());
        System.out.println("Is the string palindrome ?? ans is " + result);
    }
}

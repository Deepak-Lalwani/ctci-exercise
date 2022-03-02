package com.exercise.chapter01.arrayandstrings;

import java.util.Arrays;

public class Q3_URLify {

    public static String URLify_solution1(String str, int len) {
        return str.substring(0, len).replaceAll(" ", "%20");

    }

    // Without using native functions and string manipulation (since string is immutable we use char array
    public static String URLify_solution2(String str, int trueLength) {
        char[] array = str.toCharArray();

        int spaceCount = 0, index;

        for(int i=0; i<trueLength; i++) {
            if (array[i] == ' ') spaceCount++;
        }
        index = trueLength + spaceCount * 2;
        if(trueLength > array.length) array[trueLength] = '\0';
        for(int i=trueLength -1; i>= 0; i--) {
            if(array[i] == ' ') {
                array[index-1] = '0';
                array[index-2] = '2';
                array[index-3] = '%';
                index = index-3;
            } else {
                array[index-1] = array[index];
                index--;
            }
        }
        return Arrays.toString(array);
    }

    public static int getTrueLength(String str) {
        int i= str.length()-1;
        while(str.charAt(i) == ' '){
            i--;
        }
        return i+1;
    }

    public static void main(String[] args) {

        String testStr1 = "Mr John Smith      ";
        String testStr2 = "Mr Jo hn f  Smith";

        int trueLength =getTrueLength(testStr2);

        String url = URLify_solution1(testStr2, trueLength);
        System.out.println("URL is " + url);
    }
}

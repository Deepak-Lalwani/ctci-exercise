package com.exercise.chapter01.arrayandstrings;

import java.util.HashMap;
import java.util.Map;

public class StringCompression {
    public static String compressString(String str) {
        boolean isSameLength = true;
        for(int i=0, count=1; i<str.length(); i++) {
            if(i+1 >= str.length() || str.charAt(i) != str.charAt(i)) {
                if(count > 2 ) { isSameLength = false; break;}
                count = 1;
            }
            else count++;
        }
        if(isSameLength) return str;
        StringBuilder resultString = new StringBuilder();
        for(int i=0, count=1; i<str.length(); i++) {
            if(i+1 == str.length()) {
                resultString.append(str.charAt(i));
                resultString.append(count);
            } else {
                if (str.charAt(i) == str.charAt(i + 1)) {
                    count += 1;
                } else {
                    resultString.append(str.charAt(i));
                    resultString.append(count);
                    count = 1;
                }
            }
        }
        return resultString.toString();
    }
    public static void main(String[] args) {

        String testStr1 = "aabcccccaaa";
        String testStr2 = "aabcccccaaad";
        String testStr3 = "aaaaabbbbaaaabbddc";

        String testStr4 = "aabbccd";

        String result = compressString(testStr4);
        System.out.println("result is " + result);
    }
}

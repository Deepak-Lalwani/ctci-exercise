package com.exercise.chapter01.arrayandstrings;

public class OneAway {

    public static boolean checkOneAway(String bigString, String smallString) {
        boolean difference = false;
        for(int i=0,j=0; i< smallString.length(); i++,j++) {
            if(bigString.charAt(j) != smallString.charAt(i)){
                if(difference) return false;
                difference = true;
                if(bigString.length() != smallString.length()) i--;
            }
        }
        return true;
//        if(bigString.length()== smallString.length()) return true;
//        else return !difference;
    }

    public static boolean oneAway_solution1(String str1, String str2) {
        if(Math.abs(str1.length() - str2.length()) > 1) return false;
        if(str1.length() > str2.length()) return checkOneAway(str1,str2);
        else return checkOneAway(str2,str1);
    }

    public static void main(String[] args) {

        //Test string 1 should return true
        String testStr1 = "pale"; String testStr2 = "ple";

        //Test string 3 should return true
        String testStr3 = "pales"; String testStr4 = "pale";

        //Test string 3 should return true
        String testStr5 = "pale"; String testStr6 = "bale";

        //Test string 3 should return true
        String testStr7 = "pale"; String testStr8 = "bae";

        String testStr9 = "pxe"; String testStr10 = "paxe";

        String testStr11 = "a"; String testStr12 = "ab";

        String[] testArrays = new String[]{testStr1, testStr2, testStr3, testStr4, testStr5, testStr6, testStr7, testStr8, testStr9, testStr10, testStr11, testStr12};

        for(int i=0, j=0; i<testArrays.length/2; i++) {
            //boolean result = isUniqueChar_Solution1(testArrays[i]);
            boolean result = oneAway_solution1(testArrays[j], testArrays[j+1]);
            System.out.println("For String " + testArrays[j] + " & " + testArrays[j+1] + " result is  " + result);
            j=j+2;
        }

    }
}

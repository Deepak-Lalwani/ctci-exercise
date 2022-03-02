package com.exercise.chapter01.arrayandstrings;

import java.util.Arrays;
import java.util.HashMap;

/*
    Page:   101
    Que:    Implement an algorithm to determine if a string has all unique characters.
            What if you cannot use additional data structures?
\*/
public class IsUniqueChar {

    // Merge sort with time complexity o(N logN)

    // Merge the two sorted halves into one
    // First halve should be arr[l...m]
    // Second halve should be arr[m+1...r]
    public static void merge(char[] arr, int left, int mid, int right){
        int leftArrSize = mid- left + 1;
        int rightArrSize = right - mid;
        char[] tempLeftArr = new char[leftArrSize];
        char[] tempRightArr = new char[rightArrSize];

        for(int i=0; i<leftArrSize; i++) {
            tempLeftArr[i] = arr[left + i];
        }
        for(int j=0; j<rightArrSize; j++) {
            tempRightArr[j] = arr[mid + 1 + j];
        }

        int i=0, j=0, k=left;
        while (i<leftArrSize && j<rightArrSize) {
            if(tempLeftArr[i] <= tempRightArr[j]){
                arr[k] = tempLeftArr[i];
                i++;
            } else {
                arr[k] = tempRightArr[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of LeftArr[] if any */
        while (i<leftArrSize){
            arr[k] = tempLeftArr[i];
            i++;
            k++;
        }
        /* Copy remaining elements of RightArr[] if any */
        while (j<rightArrSize){
            arr[k] = tempRightArr[j];
            j++;
            k++;
        }

    }

    public static void performMergeSort(char[] arr, int left, int right){
        if(left<right){
            int mid = left + (right-left)/2;

            // Sort first and second halves
            performMergeSort(arr, left, mid);
            performMergeSort(arr, mid+1, right);

            // compare and merge the sorted halves
            merge(arr, left, mid, right);
        }

    }

    public static boolean isUniqueChar_Solution1(String str) {

        if(str.isEmpty() || str.length() == 1) return true;
        // Sort the array with a complexity of O(N logN)
        char [] arr = str.toCharArray();
        performMergeSort(arr, 0, arr.length-1);
        System.out.println("arr sorted is " + Arrays.toString(arr));

        // Compare nearby characters in a sorted string and break if any matches are found O(N)
        for(int i=0; i<arr.length-1; i++) {
            if(arr[i] == arr[i+1]) {
                System.out.println("matched sorted is " + arr[i] + " and " + arr[i+1]);
                return false;
            }
        }
        //Total complexity should be O(N logN) + O(N) => O(N logN)
        return true;
    }

    public static boolean isUniqueChar_Solution2(String str) {

        if(str.isEmpty() || str.length() == 1) return true;

        // Throw everything in hashmap with key as char and value as 0
        HashMap<Character, Integer> hmap = new HashMap<>();
        char [] arr = str.toCharArray();
        for(int i=0; i< arr.length; i++){
            if(hmap.get(arr[i]) != null) return false;
            hmap.put(arr[i], 0);
        }
        //Total complexity should be (N)
        return true;
    }

    /*
    * Create an array of boolean values, where the flag at index i indicates whether character i in the alphabet is contained
    * in the string. The second time you see this character you can immediately return false. We can also immediately return false
    * if the string length exceeds the number of unique characters in the alphabet.
    * After all, you can't form a string of 280 unique characters out of a 128-character alphabet.
    * Time complexity for this code is O(n) actually it will never exceed 128 times so O(1) or O(min (c, n)) where c is size of character set
    * The space complexity isO(1)
    * */

    public static boolean isUniqueChar_Solution3(String str){

        if(str.length() > 128) return false;

        boolean[] booleanArray = new boolean[128];
        for(int i=0; i<str.length(); i++){
            int val = str.charAt(i);
            if(booleanArray[val]) return false;
            booleanArray[val] = true;
        }
        return true;
    }

    public static void main(String[] args) {

        //Test string 1 should return true
        String testStr1 = "abcEfsd";

        //Test string 2 should return false
        String testStr2 = "abcEfsda";

        //Test string 3 should return true
        String testStr3 = "";

        //Test string 4 should return true
        String testStr4 = "a";

        String[] testArrays = new String[]{testStr1, testStr2, testStr3, testStr4};


        for(int i=0; i<testArrays.length; i++) {
            //boolean result = isUniqueChar_Solution1(testArrays[i]);
            boolean result = isUniqueChar_Solution3(testArrays[i]);
            System.out.println("For String " + testArrays[i] + " result is  " + result);
        }


    }
}

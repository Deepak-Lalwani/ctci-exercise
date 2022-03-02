package com.exercise.chapter01.arrayandstrings.Q1_09_String_Rotation;

import com.exercise.chapter01.arrayandstrings.IsUniqueChar;
import com.exercise.chapter01.arrayandstrings.PalindromePermutation;

import java.util.Arrays;

public class StringRotation {

    public static void merge(char[] array, int left, int mid, int right) {
        int leftArrayLength = mid - left + 1;
        int rightArrayLength = right - mid;
        char[] leftArray = new char[leftArrayLength];
        char[] rightArray = new char[rightArrayLength];

        for(int i=0; i<leftArrayLength; i++) {
            leftArray[i] = array[i+left];
        }
        for(int j=0; j<rightArrayLength; j++) {
            rightArray[j] = array[j+mid + 1];
        }
        int i=0, j=0, k=left;
        while(i<leftArrayLength && j<rightArrayLength) {
            if(leftArray[i] <= rightArray[j]){
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while(i<leftArrayLength) {
            array[k] = leftArray[i];
            i++;
            k++;
        }
        while (j<rightArrayLength) {
            array[k] = rightArray[j];
            j++;
            k++;
        }

    }

    public static void performMergeSort(char[] array, int left, int right) {
        if(left<right) {
            int mid = left + (right-left)/2;

            performMergeSort(array, left, mid);
            performMergeSort(array, mid+1, right);
            merge(array, left, mid, right);
        }
    }

    public static boolean isSubString(String s1, String s2) {
        boolean sequence = false;
        for(int i=0; i<s1.length(); i++){
            if(s1.charAt(i) == s2.charAt(0)){
                sequence = true;
                for(int j=0; j<s2.length(); j++){
                    if(s1.charAt(i+j) != s2.charAt(j)){
                        sequence = false;
                        break;
                    }
                }
            }
            if (sequence) break;
        }
        return sequence;
    }

    /*
        Solution from the book Assume S2 is the rotation of S1 and
        Suppose S1 = XY then S2 would be S2 = YX, so we need the rotation point between X and Y
        Now YX would be a subset of XYXY i.e. S2 would be a subString of S1S1
        So we can simply append S1 with itself (S1) and check if S2 is a substring
    */
    public static boolean isRotation(String str1, String str2){
        if(str1.length() != str2.length()) return false;
        String appendedString = str1 + str1;
        return isSubString(appendedString, str2);
    }
    /*
        Both first and second solution are actually a solution for permutation instead of rotation
        This solution is correct
    */
    public static boolean isSubString_Solution3(String str1, String str2) {
        StringBuilder sb = new StringBuilder(str1.length());
        boolean sequence = false;
        int rotationIndex = 0;
        for(int i=0; i<str2.length(); i++) {
            if(str2.charAt(i) == str1.charAt(0)){
                int j=1;
                sequence = true;
                rotationIndex = i;
                sb.append(str2.charAt(i));
                for(int k=i+1; k<str2.length(); k++) {
                    if(str1.charAt(j) != str2.charAt(k)){
                        sequence = false;
                        sb.delete(0,sb.length());
                        break;
                    }
                    sb.append(str2.charAt(k));
                    j++;
                }
                if(sequence){
                    for(int x=0; x<rotationIndex; x++, j++) {
                        if(str2.charAt(x) != str1.charAt(j)){
                            sequence = false;
                            sb.delete(0, sb.length());
                            break;
                        }
                        sb.append(str2.charAt(x));
                    }
                }
            }
        }
        System.out.println("sb is " + sb);
        return str1.equals(sb.toString());
    }
    /*
        To improve on first solution we can use a checker array,
        we would initialize the array with default i.e. zero
        then we would traverse through the first array, index would be ASCII value of character and we will increase the count at the index
        Now we would traverse through the second array and decrease the count for each character
        Now we would check the whole array and see if any value is non zero then the strings are not equal
        Time complexity
    */
    public static boolean isSub5tring_solution2(String str1, String str2) {
        if(str1.length() != str2.length()) return false;
        int[] checker_array = new int[128];

        for(int i=0; i<str1.length(); i++) {
            checker_array[str1.charAt(i)]++;
        }
        for(int i=0; i<str2.length(); i++) {
            checker_array[str2.charAt(i)]--;
        }
        for(int i=0; i<checker_array.length; i++){
            if(checker_array[i] != 0) return false;
        }
        return true;
    }

    /*
        one solution would be to sort both the strings and then compare both strings using equals
        Time complexity should be O(N log N) which is equal to sort the string
    */
    public static boolean isSub5tring_solution1(String str1, String str2) {
        if(str1.length() != str2.length()) return false;
        char[] string1_array = str1.toCharArray();
        char[] string2_array = str2.toCharArray();

        performMergeSort(string1_array, 0, string1_array.length-1);
        performMergeSort(string2_array, 0, string1_array.length-1);

        String sortedString1 = Arrays.toString(string1_array);
        String sortedString2 = Arrays.toString(string2_array);
        return sortedString1.equals(sortedString2);
    }

    public static void main(String[] args){
        String testStringA_1 = "waterbottle";
        String testStringB_1 = "erbottlewat";

        String testStringA_2 = "DeepakL";
        String testStringB_2 = "pakLDee";
        boolean result = isRotation(testStringA_2, testStringB_2);


        System.out.println("result is " + result);
    }
}

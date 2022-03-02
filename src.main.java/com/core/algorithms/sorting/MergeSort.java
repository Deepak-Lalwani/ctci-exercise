package com.core.algorithms.sorting;

import java.util.Arrays;

// Merge sort with time complexity o(N logN)
public class MergeSort {

    // Merge the two sorted halves into one
    // First halve should be arr[l...m]
    // Second halve should be arr[m+1...r]
    public static void merge(int[] arr, int left, int mid, int right){
        int leftArrSize = mid- left + 1;
        int rightArrSize = right - mid;
        int[] tempLeftArr = new int[leftArrSize];
        int[] tempRightArr = new int[rightArrSize];

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

    public static void performMergeSort(int[] arr, int left, int right){
        if(left<right){
            int mid = left + (right-left)/2;

            // Sort first and second halves
            performMergeSort(arr, left, mid);
            performMergeSort(arr, mid+1, right);

            // compare and merge the sorted halves
            merge(arr, left, mid, right);
        }

    }

    public static void main(String[] args) {

        //Test string 1 should return false
        int [] testArr1 = new int[]{5, 3, 6, 0, 13, 10};


        System.out.println("testArr1  is "+ Arrays.toString(testArr1));
        performMergeSort(testArr1, 0, testArr1.length-1);
        System.out.println("after sort testArr1 is "+ Arrays.toString(testArr1));


    }
}

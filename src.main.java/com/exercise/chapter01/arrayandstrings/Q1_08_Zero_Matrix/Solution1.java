package com.exercise.chapter01.arrayandstrings.Q1_08_Zero_Matrix;

/*
    Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to 0.
*/
public class Solution1 {

    public static void zeroMatrix(int[][] matrix) {
        int[] zeroRows = new int[matrix.length];
        int[] zeroColumns = new int[matrix[0].length];

        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[0].length; j++) {
                if(matrix[i][j] == 0){
                    zeroRows[i] = 1;
                    zeroColumns[j] = 1;
                    break;
                }
            }
        }

        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[0].length; j++) {
                if(zeroRows[i] != 0 || zeroColumns[j] != 0){
                    matrix[i][j] = 0;
                }
            }
        }
    }
    public static void main(String[] args) {
        int[][] matrix1 = new int[][] { {1,2,3,4}, {5,0,7,8}, {0,10,11,12}, {13,14,15,16} };
        com.exercise.chapter01.arrayandstrings.Q1_07_Rotate_Matrix.Solution1.printMatrix(matrix1);
        zeroMatrix(matrix1);
        com.exercise.chapter01.arrayandstrings.Q1_07_Rotate_Matrix.Solution1.printMatrix(matrix1);
    }
}

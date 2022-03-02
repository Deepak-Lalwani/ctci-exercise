package com.exercise.chapter01.arrayandstrings.Q1_07_Rotate_Matrix;

public class Solution1 {

    /* Solution from the github repo for reference

    */
    public static boolean rotate(int[][] matrix) {
        if (matrix.length == 0 || matrix.length != matrix[0].length) return false; // Not a square
        int n = matrix.length;

        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;
            for(int i = first; i < last; i++) {
                int offset = i - first;
                int top = matrix[first][i]; // save top

                // left -> top
                matrix[first][i] = matrix[last-offset][first];

                // bottom -> left
                matrix[last-offset][first] = matrix[last][last - offset];

                // right -> bottom
                matrix[last][last - offset] = matrix[i][last];

                // top -> right
                matrix[i][last] = top; // right <- saved top
            }
        }
        return true;
    }

    /*
        The idea behind 90-degree rotation is similar to transpose the matrix, with first row as last column and last row as first column
        This solution uses works on o(n^2) time complexity which is BCR cause we need to touch all the nodes in array atleast once
        This solution uses a extra matrix of same size which increase it's space complexity
    */
    public static int[][] rotateMatrix_soulution1(int[][] matrix) {
        int[][] Tmatrix = new int[matrix.length][matrix[0].length];
        for(int i=0;  i<matrix.length; i++) {
            for(int j=0, x=matrix.length-1; j<matrix[0].length; j++, x--){
                Tmatrix[i][j] = matrix[x][i];
            }
        }
        return Tmatrix;
    }

    //Helper function to print matrix
    public static void printMatrix(int[][] matrix) {
        System.out.println("Printed Matrix : ");
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "   ");
            }
            System.out.println("");
        }
    }

    public static void main(String []args){

        int[][] matrix =  {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        printMatrix(matrix);
        int[][] Tmatrix = rotateMatrix_soulution1(matrix);
        System.out.println("T matrix is ");
        printMatrix(Tmatrix);

//        printMatrix(matrix);
//        rotate(matrix);
//        printMatrix(matrix);

    }
}

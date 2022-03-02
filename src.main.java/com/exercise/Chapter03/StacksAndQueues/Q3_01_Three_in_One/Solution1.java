package com.exercise.Chapter03.StacksAndQueues.Q3_01_Three_in_One;

public class Solution1 {
    static int noOfArray = 3;
    static int stackCapacity;
    static int[] stackArray;
    static int[] sizes;

    public static void initialize(int stackSize) {
        stackCapacity = stackSize;
        stackArray = new int[noOfArray *stackCapacity];
        sizes = new int[noOfArray];
    }

    public static void push(int data, int stackNo) throws Exception{
        if(isFull(stackNo)) throw new Exception("Stack is full");
        int top = sizes[stackNo-1] + ((stackNo-1) * stackCapacity);
        stackArray[top] = data;
        sizes[stackNo-1]++;
    }

    public static int pop(int stackNo) throws Exception{
        if(isEmpty(stackNo)) throw new Exception("stack is empty");
        int top = sizes[stackNo-1] + ((stackNo-1) * stackCapacity);
        int data = stackArray[top-1];
        sizes[stackNo-1]--;
        return data;
    }

    public static int peek(int stackNo) throws Exception{
        if(isEmpty(stackNo)) throw new Exception("stack is empty");
        int top = sizes[stackNo-1] + ((stackNo-1) * stackCapacity);
        return stackArray[top-1];
    }

    public static boolean isEmpty(int stackNo) {
        return sizes[stackNo-1] == 0;
    }

    public static boolean isFull(int stackNo) {
        return sizes[stackNo-1] == stackCapacity;
    }

    public static void printArray() {
        System.out.println("stack is :   ");
        for(int i=0, j=0; i<stackArray.length; i++){
            if(i == stackCapacity * j){
                j++;
                System.out.println();
                System.out.print("Stack " + j + " is");
            }
            System.out.print("   ->   " + stackArray[i]);
        }
        System.out.println();
        System.out.println("sizes are :   ");
        for(int i=0; i<sizes.length; i++){
            System.out.print(sizes[i] + "   " );
        }
    }
    public static void main(String[] args) {
        try {
            initialize(5);
            printArray();
            push(10, 1);
            push(20, 2);
            push(30, 3);

            push(11, 1);
            push(12, 1);
            push(13, 1);

            System.out.println("peek operation " + peek(1));
            System.out.println("pop operation " + pop(1));
            System.out.println("peek operation " + peek(1));

            push(14, 1);
            push(15, 1);

            pop(2);


            printArray();
        } catch (Exception e) {
            System.out.println("Exception is " + e.getMessage());
        }
    }
}



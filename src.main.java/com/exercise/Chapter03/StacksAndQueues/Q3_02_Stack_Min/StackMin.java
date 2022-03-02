package com.exercise.Chapter03.StacksAndQueues.Q3_02_Stack_Min;

public class StackMin {

    static class StackNode {
        int data;
        int min = -1;
        StackNode next = null;
        StackNode(int data, int min) {
            this.data = data;
            this.min = min;
            this.next = null;
        }
    }
    static StackNode top = null;

    public void push(int data) {
        int newMin;

        if(top == null) {
            newMin = data;

        } else {
            newMin =  Math.min(top.min, data);
        }
        StackNode newNode = new StackNode(data, newMin);
        newNode.next = top;
        top = newNode;
    }

    public int pop() {
        int data = top.data;
        top = top.next;
        return data;
    }

    public int min() {
        return top.min;
    }

    public String toString() {
        StackNode node = top;
        StringBuilder sb = new StringBuilder();
        while(node != null) {
            sb.append("  ->  ").append(node.data);
            node = node.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        StackMin stack = new StackMin();

        stack.push(4);
        System.out.println("Stack Min is " + stack.min());
        stack.push(3);
        System.out.println("Stack Min is " + stack.min());
        stack.push(2);
        System.out.println("Stack Min is " + stack.min());
        stack.push(12);
        System.out.println("Stack Min is " + stack.min());
        stack.push(1);
        System.out.println("Stack Min is " + stack.min());
        System.out.println("Stack is " + stack.toString());


        stack.pop();
        System.out.println("Stack Min is " + stack.min());
        stack.pop();
        System.out.println("Stack Min is " + stack.min());
        stack.pop();
        System.out.println("Stack Min is " + stack.min());
        System.out.println("Stack is " + stack.toString());

    }
}

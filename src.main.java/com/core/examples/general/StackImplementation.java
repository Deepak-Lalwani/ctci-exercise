package com.core.examples.general;

public class StackImplementation {

    static class Node {
        int data;
        Node next = null;
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    public Node top = null;

    public void push(int data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
    }

    public int pop() throws Exception {
        if(top == null) throw new Exception("Stack is empty");
        int data = top.data;
        top = top.next;
        return data;
    }

    public int peek() throws Exception {

        if(top == null) throw new Exception("Stack is empty");
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public static void main(String[] args) {

    }
}

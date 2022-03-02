package com.exercise.Chapter03.StacksAndQueues.Q3_03_Stack_of_Plates;

import java.util.ArrayList;

class Stack {

    class StackNode {
        int data;
        StackNode next = null;
        StackNode(int data) {
            this.data = data;
        }
    }
    Stack(int capacity) {
        this.capacity = capacity;
    }
    StackNode top = null;
    int counter = 0;
    int capacity;

    public void push(int data) {
        StackNode newNode = new StackNode(data);
        newNode.next = top;
        top = newNode;
        counter++;
    }
    public int pop() {
        int data = top.data;
        top = top.next;
        counter--;
        return data;
    }
    public int peek() {
        return top.data;
    }
    public boolean isEmpty() {
        return top == null;
    }
    public boolean isFull() {
        return counter == capacity;
    }
    public String toString() {
        StackNode node = top;
        StringBuilder sb = new StringBuilder();
        while (node != null){
            sb.append("  ->  ").append(node.data);
            node = node.next;
        }
        return sb.toString();
    }
}

class SetOfStacks {
    final int capacity = 3;
    ArrayList<Stack> stacks = new ArrayList<>();

    public void push(int data) {
        if(stacks.isEmpty()){
            Stack stack = new Stack(capacity);
            stack.push(data);
            stacks.add(stack);
        } else {
            Stack currentStack = stacks.get(stacks.size() - 1);
            if(currentStack.isFull()){
                Stack stack = new Stack(capacity);
                stack.push(data);
                stacks.add(stack);
            } else {
                currentStack.push(data);
            }
        }
    }

    public int pop() {
        Stack stack = stacks.get(stacks.size() -1);
        int data = stack.pop();
        if(stack.isEmpty()){
            stacks.remove(stacks.size() - 1);
        }
        return data;
    }

    public int peek() {
        return stacks.get(stacks.size() - 1).peek();
    }

    public boolean isEmpty() {
        return stacks.isEmpty();
    }

    public String toString() {
        if(isEmpty()) return "Stack is empty";
        StringBuilder sb = new StringBuilder();
        System.out.println("Printing stack : ");
        int count = 1;
        for(Stack stack : stacks) {
            sb.append("\n").append(count).append(" stack is ").append(stack.toString());
            count++;
        }
        return sb.toString();
    }

    public int popAt(int index) throws Exception{
        if(stacks.isEmpty()) throw new Exception("Empty Stack");
        if(index > stacks.size()) throw new Exception("Stack index out of bound exception");
        // If the pop is from last stack then there is no need to shift elements, its a normal pop
        if(index == stacks.size()) return pop();
        else {
            Stack stack = stacks.get(index - 1);
            int data = stack.pop();

            for(int i = index; i<stacks.size(); i++) {
                stack = stacks.get(i - 1);
                Stack nextStack = stacks.get(i);
                if(nextStack.top.next == null) {
                    stack.push(nextStack.top.data);
                    stacks.remove(i);
                    return data;
                }
                Stack.StackNode node = nextStack.top;
                Stack.StackNode prevNode = null;
                while(node.next != null) {
                    prevNode = node;
                    node = node.next;
                }
                prevNode.next = null;
                stack.push(node.data);
            }
            return data;
        }
    }
}

public class SolutionA_2 {
    public static void main(String[] args) {
        try {
            SetOfStacks setOfStacks = new SetOfStacks();

            System.out.println(setOfStacks.toString());

            setOfStacks.push(10);
            setOfStacks.push(20);
            setOfStacks.push(30);
            System.out.println(setOfStacks.toString());

            setOfStacks.push(40);
            setOfStacks.push(50);
            System.out.println(setOfStacks.toString());

            setOfStacks.push(60);
            setOfStacks.push(70);
            setOfStacks.push(80);
            System.out.println(setOfStacks.toString());

            setOfStacks.pop();
            setOfStacks.pop();
            System.out.println(setOfStacks.toString());

            setOfStacks.pop();
            System.out.println(setOfStacks.toString());

            setOfStacks.pop();
            System.out.println(setOfStacks.toString());

            System.out.println(setOfStacks.peek());

            setOfStacks.push(11);
            setOfStacks.push(22);
            setOfStacks.push(33);
            setOfStacks.push(44);
            setOfStacks.push(55);
            setOfStacks.push(66);
            setOfStacks.push(77);
            System.out.println(setOfStacks.toString());

            System.out.println(setOfStacks.peek());

            setOfStacks.popAt(2);
            System.out.println(setOfStacks.toString());

            setOfStacks.popAt(2);
            System.out.println(setOfStacks.toString());

            setOfStacks.popAt(2);
            System.out.println(setOfStacks.toString());
        } catch (Exception ex) {
            System.out.println("Exception is " + ex.getMessage());
        }

    }
}

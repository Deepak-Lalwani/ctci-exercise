package com.exercise.chapter02.linkedlist.Q02_04_Partition;

import com.core.examples.general.LinkedListImplementation;

public class Partition {

    public static void pointerTesting(LinkedListImplementation list){
        LinkedListImplementation.Node head = list.getHead();
        LinkedListImplementation.Node node = head;
        LinkedListImplementation.Node testPointer = node;
        System.out.println("before moving node, data is " + node.data);
        System.out.println("before moving pointer, data is " + testPointer.data);
        node = node.next;
        System.out.println("after moving node, data is " + node.data);
        System.out.println("after moving pointer, data is " + testPointer.data);

    }

    public static void partition_solution1(LinkedListImplementation list, int element){
        LinkedListImplementation.Node head = list.getHead();
        LinkedListImplementation.Node node = head;
        LinkedListImplementation.Node left = null, leftHead = null;
        LinkedListImplementation.Node right = null, rightHead = null;

        while (node != null) {

            if(node.data < element){
                if(left == null) {
                    left = node;
                    leftHead = node;
                }
                else {
                    left.next = node;
                    left = left.next;
                }

            } else {
                if(right == null) {
                    right = node;
                    rightHead = node;
                }
                else {
                    right.next = node;
                    right = right.next;
                }
            }
            node = node.next;
        }
        head = leftHead;
        left.next = rightHead;
        right.next = null;
    }

    public static void main(String[] args) {
        LinkedListImplementation linkedList = new LinkedListImplementation();

        System.out.println(linkedList);
        linkedList.add(3);
        linkedList.add(5);
        linkedList.add(8);
        linkedList.add(5);
        linkedList.add(10);
        linkedList.add(2);
        linkedList.add(1);

        System.out.println(linkedList);
        //pointerTesting(linkedList);
        partition_solution1(linkedList, 5);
        System.out.println(linkedList);
    }
}

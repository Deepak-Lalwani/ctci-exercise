package com.exercise.chapter02.linkedlist.Q2_01_Remove_Dups;

import com.core.examples.general.LinkedListImplementation;

import java.util.HashSet;

public class RemoveDups {

    /*
    * Follow up on solution 1 : No Buffer Allowed
    * Implementing using runner method
    * */
    public static void removeDuplicate_solution2(LinkedListImplementation list) throws Exception {

        LinkedListImplementation.Node head = list.getHead();
        if(head == null) throw new Exception("Empty List");

        LinkedListImplementation.Node node = head;
        LinkedListImplementation.Node prevNode = null;

        while (node != null) {
            LinkedListImplementation.Node current = node.next;
            LinkedListImplementation.Node prev = node;
            while (current != null ){
                if(node.data == current.data){
                    prev.next = current.next;
                }
                prev = current;
                current = current.next;
            }
            prevNode = node;
            node = node.next;
        }
    }

    /*
    * The above solution takes O(N) time, where N is the number of elements in the linked list.
    */
    public static void removeDuplicate_solution1(LinkedListImplementation list) throws Exception {
        LinkedListImplementation.Node prevNode = null;
        LinkedListImplementation.Node head = list.getHead();
        LinkedListImplementation.Node node = head;

        HashSet<Integer> set = new HashSet<Integer>();
        if(head == null ) throw new Exception("Empty Linked List");
        while (node != null ) {
            if(set.contains(node.data)){
                prevNode.next = node.next;
            } else {
                set.add(node.data);
                prevNode = node;
            }
            node = node.next;
        }
    }

    public static void main(String[] args) throws Exception {
        LinkedListImplementation linkedList = new LinkedListImplementation();
        System.out.println(linkedList);
        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);
        linkedList.add(40);
        linkedList.add(30);
        linkedList.add(50);
        linkedList.add(10);

        System.out.println(linkedList);

        //removeDuplicate_solution1(linkedList);

        removeDuplicate_solution2(linkedList);

        System.out.println(linkedList);
    }
}

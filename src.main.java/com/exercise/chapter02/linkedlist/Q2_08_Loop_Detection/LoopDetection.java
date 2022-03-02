package com.exercise.chapter02.linkedlist.Q2_08_Loop_Detection;

import com.core.examples.general.LinkedListImplementation;

import java.util.HashMap;
import java.util.HashSet;

public class LoopDetection {

    /*
    * Solution 1: Using hashset
    */
    public static LinkedListImplementation.Node detectLoop_solution1(LinkedListImplementation list) {
        LinkedListImplementation.Node node = list.head;
        HashSet<LinkedListImplementation.Node> hset = new HashSet<>();
        while (node != null){
            if(hset.contains(node)){
                return node;
            }
            hset.add(node);
            node = node.next;
        }
        return null;
    }

    /*
    * Two pointer technique
    */

    public static LinkedListImplementation.Node detectLoop_solution2(LinkedListImplementation list) {
        LinkedListImplementation.Node slowRunner = list.head;
        LinkedListImplementation.Node fastRunner = list.head;;

        do{
            slowRunner = slowRunner.next;
            fastRunner = fastRunner.next.next;
        } while(slowRunner != fastRunner && fastRunner != null && slowRunner != null);
        if(fastRunner == slowRunner) {
            LinkedListImplementation.Node node = list.head;
            while (slowRunner != node) {
                slowRunner = slowRunner.next;
                node = node.next;
            }
            return node;
        } else return null;
    }

    public static void main(String[] args){

        int[] array1 = {-1, -2, 0, 1, 2, 3, 4, 5, 6, 7, 8};
        LinkedListImplementation list1 = LinkedListImplementation.createLinkedListFromArray(array1);
        System.out.println(list1.toString());

        LinkedListImplementation.Node tail = list1.getTail();
        tail.next = list1.head.next.next.next;


        //LinkedListImplementation.Node loopNode = detectLoop_solution1(list1);
        LinkedListImplementation.Node loopNode = detectLoop_solution2(list1);
        if(loopNode == null ) System.out.println("No Circular Node is ");
        else System.out.println("Circular Node is " + loopNode.data);
    }
}

package com.exercise.chapter02.linkedlist.Q2_07_Intersection;

import com.core.examples.general.LinkedListImplementation;

public class InterSection {


    /*
    *   Solution 2 :
    *   This algorithm takes O(A + B) time, where A and Bare the lengths of the two linked lists. It takes O( 1) additional space.
    */
    static class LastNodeWrapper {
        LinkedListImplementation.Node lastNode;
        int length = 0;
        LastNodeWrapper(LinkedListImplementation.Node node, int length){
            this.lastNode = node;
            this.length = length;
        }
    }

    public static LastNodeWrapper getLengthAndLastNode(LinkedListImplementation list) {
        LinkedListImplementation.Node node = list.head;
        int length = 0;
        while (node.next != null){
            length++;
            node = node.next;
        }
        return new LastNodeWrapper(node, ++length);
    }

    public static LinkedListImplementation.Node getStartNode(LinkedListImplementation.Node node, int offset) {
        for(int i=0; i<offset; i++){
            node = node.next;
        }
        return node;
    }
    public static LinkedListImplementation.Node findInterSectionNode(LinkedListImplementation list1, LinkedListImplementation list2) {

        // Find the last node and the length of linkedlist
        LastNodeWrapper lastNodeWrapper1 = getLengthAndLastNode(list1);
        LastNodeWrapper lastNodeWrapper2 = getLengthAndLastNode(list2);

        //IF last node are not same that means the two list does not intersect
        if(lastNodeWrapper1.lastNode != lastNodeWrapper2.lastNode){
            return null;
        }

        LinkedListImplementation.Node start1 = list1.head;
        LinkedListImplementation.Node start2 = list2.head;
        //If the length are not same then fill the smaller linkedlist with zero
        if(lastNodeWrapper1.length != lastNodeWrapper2.length) {
            if(lastNodeWrapper1.length > lastNodeWrapper2.length) {
                start1 = getStartNode(start1, lastNodeWrapper1.length - lastNodeWrapper2.length);
            } else {
                start2 = getStartNode(start2, lastNodeWrapper2.length - lastNodeWrapper1.length);
            }
        }

        while (start1 != null || start2 != null){
            if(start1 == start2){
                return start1;
            }
            start1 = start1.next;
            start2 = start2.next;
        }
        return null;
    }



    public static void main(String[] args){

        int[] array1 = {-1, -2, 0, 1, 2, 3, 4, 5, 6, 7, 8};
        LinkedListImplementation list1 = LinkedListImplementation.createLinkedListFromArray(array1);
        System.out.println("List 1 is :   " + list1.toString());

        int[] array2 = {12, 14, 15};
        LinkedListImplementation list2 = LinkedListImplementation.createLinkedListFromArray(array2);
        System.out.println("List 2 is :   " + list2.toString());

        list2.head.next.next = list1.head.next.next.next.next;

        System.out.println("After intersection List 2 is :   " + list2.toString());

        LinkedListImplementation.Node interSectionNode = findInterSectionNode(list1, list2);
        System.out.println("Intersection Node is " + interSectionNode.data);
    }
}

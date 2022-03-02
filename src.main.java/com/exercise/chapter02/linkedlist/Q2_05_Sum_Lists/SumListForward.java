package com.exercise.chapter02.linkedlist.Q2_05_Sum_Lists;

class LinkedList2 {
    static class Node {
        int data;
        Node next = null;
        public Node(int data){
            this.data = data;
            this.next = null;
        }
    }

    Node head = null;

    public void insert(int data){
        Node newNode = new Node(data);
        if(head == null) head = newNode;
        else {
            Node node = head;
            while(node.next != null) {
                node = node.next;
            }
            node.next = newNode;
        }
    }

    public String toString(){
        if(head == null) return "Empty List";
        Node node = head;
        StringBuilder sb = new StringBuilder();
        sb.append("Linked list is: ");
        while(node != null) {
            sb.append(node.data).append("   -->   ");
            node = node.next;
        }
        sb.append("null");
        return sb.toString();
    }
}


public class SumListForward {

    static class PartialSum {
        public LinkedList2.Node sum = null;
        public int carry = 0;
    }

    public static int getLength(LinkedList2.Node head) {
        int counter=0;
        LinkedList2.Node node = head;
        while (node != null) {
            counter++;
            node = node.next;
        }
        return counter;
    }

    public static LinkedList2.Node insertBefore(LinkedList2.Node node, int data) {
        LinkedList2.Node newNode = new LinkedList2.Node(data);
        if(node != null) {
            newNode.next = node;
        }
        return newNode;
    }

    public static void addLeftPadding(LinkedList2 list, int length) {
        for(int i=0; i<length; i++){
            insertBefore(list, 0);
        }
    }

    public static PartialSum sumListHelper(LinkedList2.Node node1, LinkedList2.Node node2) {
        if(node1 == null && node2 == null){
            PartialSum sum = new PartialSum();
            return sum;
        }
        PartialSum sum = sumListHelper(node1.next, node2.next);

        int tempSum = sum.carry + node1.data + node2.data;

        sum.sum = insertBefore(sum.sum, tempSum%10);
        sum.carry = tempSum / 10;
        return sum;

    }

    public static LinkedList2.Node sumList_forward_recursion(LinkedList2 list1, LinkedList2 list2) {

        //Find length of both linked list
        int length1= getLength(list1.head);
        int length2= getLength(list2.head);

        //If length is unequal add zero padding in shorter list
        if(length1 > length2) addLeftPadding(list2, length1-length2);
        else addLeftPadding(list1, length2-length1);

        //Call the recursive helper method with two initial nodes
        PartialSum sum = sumListHelper(list1.head, list2.head);

        if(sum.carry != 0 ) sum.sum = insertBefore(sum.sum, sum.carry);
        return sum.sum;

    }

    public static LinkedList2 sumList_forward(LinkedList2 list1, LinkedList2 list2){
        int counter1=0, counter2=0;
        LinkedList2.Node node1 = list1.head, node2=list2.head;
        while(node1 != null) {
            counter1++;
            node1 = node1.next;
        }
        while(node2 != null) {
            counter2++;
            node2 = node2.next;
        }
        // Easy way, get sum for individual lists and then add them and then insert into new list using mode operation
        int  sum1=0, sum2=0;
        node1 = list1.head; node2 = list2.head;
        for(int i=counter1-1; i>=0; i--){
            sum1 += node1.data * Math.pow(10, i);
            node1 = node1.next;
        }
        for(int j=counter2-1; j>=0; j--){
            sum2 += node2.data * Math.pow(10, j);
            node2 = node2.next;
        }
        int sum = sum1 + sum2;
        System.out.println("sum is " + sum);
        LinkedList2 list3 = new LinkedList2();

        /*
        *   Approach 1: reversing the number
        */
//        int reverseSum = 0;
//        while(sum > 0){
//            int mode = sum %10;
//            reverseSum = reverseSum * 10 + mode;
//            sum = sum /10;
//
//        }
//        // Inserting that reversed number into result linked list
//
//        LinkedList2.Node node3 = list3.head;
//        while(reverseSum > 0){
//            list3.insert(reverseSum%10);
//            reverseSum = reverseSum/10;
//        }

        /*
        **   Approach 2- Using insertBefore  function
        */
        while(sum > 0){
            insertBefore(list3, sum % 10);
            sum = sum/10;
        }
        return list3;
    }

    public static void insertBefore(LinkedList2 list, int data){
        LinkedList2.Node newNode = new LinkedList2.Node(data);
        newNode.next = list.head;
        list.head = newNode;
    }

    public static void main(String []args){

        LinkedList2 list1 = new LinkedList2();
        list1.insert(1);
        list1.insert(6);
        list1.insert(1);
        list1.insert(7);
        System.out.println(list1.toString());

        LinkedList2 list2 = new LinkedList2();
        list2.insert(2);
        list2.insert(9);
        list2.insert(5);
        System.out.println(list2.toString());

        //LinkedList2 list3 = sumList_forward(list1, list2);
        //System.out.println(list3.toString());

        LinkedList2 list3 = new LinkedList2();
        LinkedList2.Node head3 = sumList_forward_recursion(list1, list2);
        list3.head = head3;
        System.out.println(list3.toString());
    }
}

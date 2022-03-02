package com.exercise.chapter02.linkedlist.Q2_05_Sum_Lists;

class LinkedList {
    public class Node {
        int data;
        Node next = null;
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    Node head = null;

    public void insert(int data) {
        Node newNode = new Node(data);
        if(head==null) head = newNode;
        else {
            Node node = head;
            while(node.next != null) {
                node = node.next;
            }
            node.next = newNode;
        }
    }

    public String toString() {
        Node node = head;
        StringBuilder sb = new StringBuilder();
        sb.append("Linked list is:");
        while(node != null) {
            sb.append("   ->   ").append(node.data);
            node = node.next;
        }
        sb.append("   ->   null");
        return sb.toString();
    }
}

public class SumLists {

    public static LinkedList sumList_forward_solution1(LinkedList list1, LinkedList list2) {
        LinkedList.Node head1 = list1.head;
        LinkedList.Node head2 = list2.head;

        LinkedList.Node node1 = head1;
        LinkedList.Node node2 = head2;

        int counter1 = 0;
        int counter2 = 0;

        while(node1 != null) {
            counter1++;
            node1 = node1.next;
        }
        while(node2 != null) {
            counter2++;
            node2 = node2.next;
        }

        int length = Math.max(counter1, counter2);
        int sum = 0;
        node1 = head1;
        node2 = head2;

        for(int i=0; i<length; i++) {
            sum += (node1.data + node2.data) * Math.pow(10, i);
            node1 = node1.next;
            node2 = node2.next;
        }

        LinkedList list3 = new LinkedList();
        while(sum > 0) {
            int mode = sum % 10;
            sum = sum / 10;
            list3.insert(mode);
        }
        return list3;
    }

    public static LinkedList sumList_forward_solution2(LinkedList list1, LinkedList list2) {
        LinkedList.Node node1 = list1.head;
        LinkedList.Node node2 = list2.head;
        LinkedList list3 = new LinkedList();
        LinkedList.Node node3 = list3.head;
        int carry = 0, data1 = 0, data2 = 0;
        while (node1 != null || node2 != null) {
            if(node1 != null){
                data1 = node1.data;
                node1 = node1.next;
            } else data1 = 0;

            if(node2 != null){
                data2 = node2.data;
                node2 = node2.next;
            } else data2 = 0;


            int tempSum = carry + data1 + data2;
            int mode = tempSum % 10;
            list3.insert(mode);
            carry = tempSum / 10;
        }
        return list3;
    }

    public static void main(String [] args) {
        LinkedList list1 = new LinkedList();
        list1.insert(7);
        list1.insert(1);
        list1.insert(6);
        System.out.println("list 1 is " + list1.toString());

        LinkedList list2 = new LinkedList();
        list2.insert(5);
        list2.insert(9);
        list2.insert(2);
        list2.insert(5);
        System.out.println("list 2 is " + list2.toString());

        //LinkedList sumList = sumList_forward_solution1(list1, list2);
        LinkedList sumList = sumList_forward_solution2(list1, list2);
        System.out.println("sumList is " + sumList.toString());
    }
}
package com.core.examples.general;

public class LinkedListImplementation {
    public static class Node {
        public int data;
        public Node next = null;
        public Node(int data){
            this.data = data;
            this.next = null;
        }
    }
    public Node head = null;

    public void add(int data) {
        Node node = head;
        Node newNode = new Node(data);
        if(head == null) head = newNode;
        else {
            while (node.next != null) {
                node = node.next;
            }
            node.next = newNode;
        }
    }

    public void remove(int data) throws Exception {
        if(head == null) throw new Exception("Empty Linked List");
        Node node = head;
        Node prevNode = null;
        while( node != null && node.data != data ){
            prevNode = node;
            node = node.next;
        }
        if(node == null) throw new Exception("No data found");
        if(node == head) head = head.next;
        else {
            prevNode.next = node.next;
            node.next = null;
        }
    }

    public int get(int data) {
        Node node = head;
        while(node.data != data){
            node = node.next;
        }
        return data == node.data ? node.data : null;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        Node node = head;
        while (node.next != null) {
            node = node.next;
        }
        return node;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node node = head;
        sb.append("linked list is -> ");
        while (node != null) {
            sb.append(node.data).append(" -> ");
            node = node.next;
        }
        sb.append("null");
        return sb.toString();
    }

    public static LinkedListImplementation createLinkedListFromArray(int[] arr) {
        LinkedListImplementation list = new LinkedListImplementation();
        for(int i=0; i<arr.length; i++) {
            list.add(arr[i]);
        }
        return list;
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

        System.out.println(linkedList);

        int value = linkedList.get(10);
        System.out.println(value);
        value = linkedList.get(30);
        System.out.println(value);

        linkedList.remove(10);
        System.out.println(linkedList);

        linkedList.remove(30);
        System.out.println(linkedList);

        linkedList.remove(10);
        System.out.println(linkedList);
    }

}

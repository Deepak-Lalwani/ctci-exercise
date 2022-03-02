package com.exercise.chapter02.linkedlist.Q2_02_Return_Kth_To_Last;

import com.core.examples.general.LinkedListImplementation;

public class ReturnKthToLast {

    /*
    * A more optimal, but less straightforward, solution is to implement this iteratively.
    * We can use two pointers, pl and p2. We place them k nodes apart in the linked list by putting p2 at the beginning
    * and moving pl k nodes into the list.
    * Then, when we move them at the same pace, pl will hit the end of the linked list after LENGTH - k steps
    * At that point, p2 will be LENGTH - k nodes into the list, or k nodes from the end.
    * */

    public static int returnKthToLast_solution2(LinkedListImplementation list, int kthIndex) throws Exception{
        if(list.getHead() == null) throw new Exception("Empty List");
        LinkedListImplementation.Node head = list.getHead();
        LinkedListImplementation.Node node = head;
        LinkedListImplementation.Node p1 = head;
        LinkedListImplementation.Node p2 = null;
        for(int i=0; i<kthIndex; i++) {
            node = node.next;
        }
        p2 = node;
        while (p2.next != null) {
            p2 = p2.next;
            p1 = p1.next;
        }
        return p1.data;
    }

    public static int returnKthToLast_solution1(LinkedListImplementation list, int kthIndex) throws Exception{
        LinkedListImplementation.Node head = list.getHead();
        if(head == null) throw new Exception("EMpty List");
        LinkedListImplementation.Node node = head;
        int counter = 0;
        while (node != null ){
            counter++;
            node = node.next;
        }

        int index = counter - kthIndex;
        node = head;
        for(int i=1; i<index; i++) {
            node = node.next;
        }
        return node.data;
    }

    public static void main(String[] args) {
        try {
            LinkedListImplementation linkedList = new LinkedListImplementation();
            int kthIndex = 3;
            System.out.println(linkedList);
            linkedList.add(10);
            linkedList.add(20);
            linkedList.add(30);
            linkedList.add(40);
            linkedList.add(30);
            linkedList.add(50);
            linkedList.add(10);

            System.out.println(linkedList);
            //int kthElement = returnKthToLast_solution1(linkedList, kthIndex);
            int kthElement = returnKthToLast_solution2(linkedList, kthIndex);
            System.out.println("kth Element is " + kthElement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

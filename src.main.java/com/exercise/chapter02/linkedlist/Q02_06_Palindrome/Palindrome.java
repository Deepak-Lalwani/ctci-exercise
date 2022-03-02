package com.exercise.chapter02.linkedlist.Q02_06_Palindrome;

import com.core.examples.general.LinkedListImplementation;

import java.util.Stack;

public class Palindrome {

    /*
    * Solution 1 : Reverse the whole link list and clone it in new list then compare both
    * Note : We only have to compare till half length of list
    * */
    public static boolean isPalindrome_solution1(LinkedListImplementation list){

        //Reverse the linklist
        ListWrapper wrapper = reverseLinkList(list);
        LinkedListImplementation reverse = wrapper.list;
        System.out.println("reverse is " + reverse.toString());
        int length = wrapper.length;

        //compare both link list
        return compareLinkList(list, reverse, length/2);
    }

    static class ListWrapper {
        LinkedListImplementation list;
        int length = 0;
    }

    public static ListWrapper reverseLinkList(LinkedListImplementation list){
        ListWrapper wrapper = new ListWrapper();
        LinkedListImplementation reverse = new LinkedListImplementation();
        LinkedListImplementation.Node node = list.head;
        int count = 0;
        while (node != null ){
            count++;
            LinkedListImplementation.Node nweHead = insertBefore(reverse.head, node.data);
            reverse.head = nweHead;
            node = node.next;
        }
        wrapper.list = reverse;
        wrapper.length = count;
        return wrapper;
    }

    public static LinkedListImplementation.Node insertBefore(LinkedListImplementation.Node node, int data){
        LinkedListImplementation.Node newNode = new LinkedListImplementation.Node(data);
        if(node != null) {
            newNode.next = node;
        }
        return newNode;
    }

    public static boolean compareLinkList(LinkedListImplementation list1, LinkedListImplementation list2, int length) {
        LinkedListImplementation.Node head1 = list1.head;
        LinkedListImplementation.Node head2 = list2.head;
        for(int i=0; i<length; i++) {
            if(head1.data != head2.data) {
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return true;
    }

    /*
     * Solution 1 : ends here
     * */

    /*
     * Solution 2 : Using a stack, we will traverse through the list with a runner, and will keep the runner in such a way that
     * when the runner reaches at the end of linkedlist, our slow node would be at half of the list
     * and we will put all the first half elements in a stack
     * Once element are stored in stack we will pop and compare with the other half
     * */

    public static boolean isPalindrome_solution2(LinkedListImplementation list) {
        LinkedListImplementation.Node head = list.head;
        // Check if length is only one
        if(head == null) return false;
        if(head.next == null) return true;
        LinkedListImplementation.Node node = head;

        LinkedListImplementation.Node runner = head.next;
        Stack<Integer> stack = new Stack<>();
        boolean isEven = false;
        while (runner != null) {
            stack.push(node.data);
            node = node.next;
            if(runner.next == null){
                runner = runner.next;
                isEven = true;
            } else {
                runner = runner.next.next;
            }
        }

        // Special case when length of Linklist is odd
        if(!isEven) {
            node = node.next;
        }

        while (!stack.isEmpty() && node!= null){
            if(node.data != stack.pop()){
                return false;
            }
            node = node.next;
        }
        return true;
    }
    /*
     * Solution 2 : Ends here
     * */

    /*
     * Solution 3 : Using recursive strategy compare first and last elements
     * and then compare second and second last elements
     * */
    public static boolean isPalindrome_solution3(LinkedListImplementation list) {
        int length = getLength(list);
        LinkedListWrapper wrapper = recursiveSolution(list.head, length);
        return wrapper.result;
    }

    static class LinkedListWrapper {
        LinkedListImplementation.Node node;
        boolean result;
        LinkedListWrapper(LinkedListImplementation.Node node, boolean result){
            this.node = node;
            this.result = result;
        }
    }

    public static LinkedListWrapper recursiveSolution(LinkedListImplementation.Node node, int length) {
        if(length == 0){
            return new LinkedListWrapper(node, true);
        } else if(length <0 || length == 1){
            return new LinkedListWrapper(node.next, true);
        }
        LinkedListWrapper wrapper = recursiveSolution(node.next, length-2);
        if(!wrapper.result || wrapper.node == null){
            return wrapper;
        }
        wrapper.node = wrapper.node.next;
        wrapper.result = node.data != wrapper.node.data;
        return wrapper;
    }

    public static int getLength(LinkedListImplementation list) {
        LinkedListImplementation.Node node = list.head;
        int length = 0;
        while (node !=null) {
            length++;
            node = node.next;
        }
        return length;
    }

    public static void main(String[] args) {
        LinkedListImplementation list = new LinkedListImplementation();

        list.add(1);
        list.add(4);
        list.add(1);
//        list.add(4);
//        list.add(1);
        System.out.println(list.toString());

        //System.out.println("Is list palindrome :" + isPalindrome_solution1(list));
        //System.out.println("Is list palindrome :" + isPalindrome_solution2(list));
        System.out.println("Is list palindrome :" + isPalindrome_solution3(list));
    }
}

package chapter_2_wanhao;

import java.util.HashMap;
import java.util.Map;

public class Problem_09_CopyListWithRandom_wh {

    public static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 复制链表：这里先按照传统的方式复制一份
     * 遍历两个链表，每个链表保存两个MAP， 第一个 Key为Node，值为自增的index， 第二个 Key为自增的index，值为Node
     *
     * @param head
     * @return
     */
    public static Node copyListWithRand1(Node head) {
        if (head == null) {
            return null;
        }
        Map<Integer, Node> indexNode1 = new HashMap<>();
        Map<Node, Integer> nodeIndex1 = new HashMap<>();

        Map<Integer, Node> indexNode2 = new HashMap<>();
        Map<Node, Integer> nodeIndex2 = new HashMap<>();

        int index = 0;
        indexNode1.put(index, head);
        nodeIndex1.put(head, index);

        Node head2 = new Node(head.value);
        indexNode2.put(index, head2);
        nodeIndex2.put(head2, index);

        Node tempHead1 = head;
        Node tempHead2 = head2;
        while (tempHead1.next != null) {
            tempHead1 = tempHead1.next;
            tempHead2.next = new Node(tempHead1.value);
            tempHead2 = tempHead2.next;

            index++;
            indexNode1.put(index, tempHead1);
            nodeIndex1.put(tempHead1, index);
            indexNode2.put(index, tempHead2);
            nodeIndex2.put(tempHead2, index);
        }

        //复制random
        tempHead1 = head;
        tempHead2 = head2;
        while (tempHead1 != null) {
            if (tempHead1.rand != null) {
                int rand1 =  nodeIndex1.get(tempHead1.rand);
                tempHead2.rand = indexNode2.get(rand1);
            }
            tempHead1 = tempHead1.next;
            tempHead2 = tempHead2.next;
        }

        return head2;
    }


    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = null;
        Node res1 = null;
        Node res2 = null;
        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
//		res2 = copyListWithRand2(head);
//		printRandLinkedList(res2);
//		printRandLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
//		res2 = copyListWithRand2(head);
//		printRandLinkedList(res2);
//		printRandLinkedList(head);
//		System.out.println("=========================");

    }

}

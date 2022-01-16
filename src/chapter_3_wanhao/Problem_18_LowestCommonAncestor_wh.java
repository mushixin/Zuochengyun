package chapter_3_wanhao;

import java.util.*;

import utils.InputUtils.*;

import static utils.OutputUtils.printTree;

public class Problem_18_LowestCommonAncestor_wh {

    /**
     * 要拿到一个节点的路径结点。 得到两个父结点集合后，遍历其中一个结点，得到
     *
     * @param head
     * @param n1
     * @param n2
     * @return
     */
    public static Node lowestAncestor(Node head, Node n1, Node n2) {
        LinkedList<Node> n1Parents = getAncestors(head, n1, new LinkedList<>());
        LinkedList<Node> n2Parents = getAncestors(head, n2, new LinkedList<>());
        Set<Node> n1set = new HashSet<>(n1Parents);

        while (!n2Parents.isEmpty()) {
            Node last = n2Parents.removeLast();
            if (n1set.contains(last)) {
                return last;
            }
        }
        return null;
    }

    public static Node lowestAncestor2(Node head, Node n1, Node n2) {
        Map<Node, Node> map = getAncestors2(head, new HashMap<>());
        LinkedList<Node> n1Parents = getParents(map, n1);
        LinkedList<Node> n2Parents = getParents(map, n2);
        Set<Node> n1set = new HashSet<>(n1Parents);

        while (!n2Parents.isEmpty()) {
            Node last = n2Parents.removeLast();
            if (n1set.contains(last)) {
                return last;
            }
        }
        return null;
    }

    public static LinkedList<Node> getParents(Map<Node, Node> map, Node n) {
        LinkedList<Node> n1Parents = new LinkedList<>();
        n1Parents.addFirst(n);
        while (map.containsKey(n)) {
            n1Parents.addFirst(map.get(n));
            n = map.get(n);
        }
        return n1Parents;
    }

    /**
     * 返回每个点，对应的父节点，然后直接可以获得父节点链表，然后再去计算即可。
     *
     * @param head
     * @return
     */
    public static Map<Node, Node> getAncestors2(Node head, Map<Node, Node> map) {
        if (head == null) {
            return map;
        }
        if (head.left != null) {
            map.put(head.left, head);
            getAncestors2(head.left, map);
        }
        if (head.right != null) {
            map.put(head.right, head);
            getAncestors2(head.right, map);
        }
        return map;
    }


    /**
     * 得到一个结点的所有父节点列表，第一个元素为根节点，末尾的结点为n1
     * 回溯法即可，找到了目标结点后，直接返回。
     *
     * @param head
     * @param n1
     */
    public static LinkedList<Node> getAncestors(Node head, Node n1, LinkedList<Node> parentList) {
        parentList.addLast(head);
        /**
         * head == n1直接返回，否则去看head的左子树和右子树。看完了后，再删除左子树和右子树。
         */
        if (head == n1) {
            return parentList;
        }
        if (head.left != null) {
            LinkedList<Node> r = getAncestors(head.left, n1, parentList);
            if (r != null) {
                return r;
            } else {
                parentList.removeLast();
            }
        }
        if (head.right != null) {
            LinkedList<Node> r = getAncestors(head.right, n1, parentList);
            if (r != null) {
                return r;
            } else {
                parentList.removeLast();
            }
        }

        return null;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        head.right.right.left = new Node(8);
        printTree(head);
        System.out.println("===============");

        Node o1 = head.left.right;
        Node o2 = head.right.left;

        // ���β�ѯ--ԭ����
        System.out.println("o1 : " + o1.value);
        System.out.println("o2 : " + o2.value);
        System.out.println("ancestor : " + lowestAncestor(head, o1, o2).value);
		System.out.println("ancestor : " + lowestAncestor2(head, o1, o2).value);

		System.out.println("===============");

        o1 = head.left.right;
        o2 = head.right.right.left;

        // ���β�ѯ--ԭ����
        System.out.println("o1 : " + o1.value);
        System.out.println("o2 : " + o2.value);
        System.out.println("ancestor : " + lowestAncestor(head, o1, o2).value);
		System.out.println("ancestor : " + lowestAncestor2(head, o1, o2).value);
		System.out.println("===============");

        o1 = head.right.right;
        o2 = head.right.right.left;

        // ���β�ѯ--ԭ����
        System.out.println("o1 : " + o1.value);
        System.out.println("o2 : " + o2.value);
        System.out.println("ancestor : " + lowestAncestor(head, o1, o2).value);
		System.out.println("ancestor : " + lowestAncestor2(head, o1, o2).value);

		System.out.println("===============");


//		// ����map�󷽱��β�ѯ--��������
//		Record1 record1 = new Record1(head);
//		Record2 record2 = new Record2(head);
//
//		System.out.println("o1 : " + o1.value);
//		System.out.println("o2 : " + o2.value);
//		System.out.println("ancestor : " + record1.query(o1, o2).value);
//		System.out.println("ancestor : " + record2.query(o1, o2).value);
//
//		o1 = head.left.left;
//		o2 = head.left;
//		System.out.println("o1 : " + o1.value);
//		System.out.println("o2 : " + o2.value);
//		System.out.println("ancestor : " + record1.query(o1, o2).value);
//		System.out.println("ancestor : " + record2.query(o1, o2).value);
//
//		o1 = head.right.left;
//		o2 = head.right.right.left;
//		System.out.println("o1 : " + o1.value);
//		System.out.println("o2 : " + o2.value);
//		System.out.println("ancestor : " + record1.query(o1, o2).value);
//		System.out.println("ancestor : " + record2.query(o1, o2).value);

    }

}

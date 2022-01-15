package chapter_3_wanhao;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

import static utils.OutputUtils.printTree;

import utils.InputUtils.*;

public class Problem_10_RecoverBST_redo {

    /**
     * 中序遍历，找到nodes列表中，前面的结点。
     * @param head
     * @param nodes
     * @return
     */
    public static Node findBefNodes(Node head,Node nodes){
        Node bef = null;
        Set<Node> marked = new HashSet<>();
        LinkedList<Node> temp = new LinkedList<>();
        temp.addFirst(head);
        while (!temp.isEmpty()) {
            Node node = temp.removeFirst();
            if (marked.contains(node)) {
                if (node==nodes){
                    System.out.println(nodes.value+"前一个结点是"+ bef.value);
                    return bef;
                }
                bef = node;
                continue;
            }
            marked.add(node);
            if (node.right != null) {
                temp.addFirst(node.right);
            }
            temp.addFirst(node);
            if (node.left != null) {
                temp.addFirst(node.left);
            }
        }
        return null;
    }


    public static Node[] getTwoErrNodes(Node head) {
        Node[] errs = new Node[2];
        int errNum = 0;
        if (head == null) {
            return errs;
        }
        /**
         * 前一个结点，当前异常结点，后一个节点
         */
        Set<Node> marked = new HashSet<>();
        LinkedList<Node> temp = new LinkedList<>();
        temp.addFirst(head);
        int tempLastNum = Integer.MIN_VALUE;
        while (!temp.isEmpty()) {
            Node node = temp.removeFirst();
            if (marked.contains(node)) {
                if (tempLastNum>node.value){
                    errs[errNum++] = node;
                    tempLastNum = node.value;
                    continue;
                }
                tempLastNum = node.value;
                continue;
            }
            marked.add(node);
            if (node.right != null) {
                temp.addFirst(node.right);
            }
            temp.addFirst(node);
            if (node.left != null) {
                temp.addFirst(node.left);
            }
        }

        return errs;
    }

    public static Node recoverTree(Node head) {
        Node[] errs = getTwoErrNodes(head);
        Node beferrs = findBefNodes(head, errs[0]);

        //若是errs中有一个结点
        if (errs[1]==null){
            int temp = errs[0].value;
            errs[0].value = beferrs.value;
            beferrs.value = temp;
            return head;
        }
        //若是有两个结点
        int temp = beferrs.value;
        beferrs.value = errs[1].value;
        errs[1].value=temp;
        return head;
    }


    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    // for test
    public static boolean isBST(Node head) {
        if (head == null) {
            return false;
        }
        Stack<Node> stack = new Stack<Node>();
        Node pre = null;
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (pre != null && pre.value > head.value) {
                    return false;
                }
                pre = head;
                head = head.right;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(7);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.right.left = new Node(6);
        head.right.right = new Node(8);
        head.left.left.left = new Node(1);
        printTree(head);
        System.out.println(isBST(head));

        // ���1, 7 -> e1, 5 -> e2
        System.out.println("situation 1");
        Node head1 = new Node(7);
        head1.left = new Node(3);
        head1.right = new Node(5);
        head1.left.left = new Node(2);
        head1.left.right = new Node(4);
        head1.right.left = new Node(6);
        head1.right.right = new Node(8);
        head1.left.left.left = new Node(1);
        printTree(head1);
        System.out.println(isBST(head1));
        Node res1 = recoverTree(head1);
        printTree(res1);
        System.out.println(isBST(res1));

        // ���2, 6 -> e1, 5 -> e2
        System.out.println("situation 2");
        Node head2 = new Node(6);
        head2.left = new Node(3);
        head2.right = new Node(7);
        head2.left.left = new Node(2);
        head2.left.right = new Node(4);
        head2.right.left = new Node(5);
        head2.right.right = new Node(8);
        head2.left.left.left = new Node(1);
        printTree(head2);
        System.out.println(isBST(head2));
        Node res2 = recoverTree(head2);
        printTree(res2);
        System.out.println(isBST(res2));

        // ���3, 8 -> e1, 5 -> e2
        System.out.println("situation 3");
        Node head3 = new Node(8);
        head3.left = new Node(3);
        head3.right = new Node(7);
        head3.left.left = new Node(2);
        head3.left.right = new Node(4);
        head3.right.left = new Node(6);
        head3.right.right = new Node(5);
        head3.left.left.left = new Node(1);
        printTree(head3);
        System.out.println(isBST(head3));
        Node res3 = recoverTree(head3);
        printTree(res3);
        System.out.println(isBST(res3));

        // ���4, 5 -> e1, 3 -> e2
        System.out.println("situation 4");
        Node head4 = new Node(3);
        head4.left = new Node(5);
        head4.right = new Node(7);
        head4.left.left = new Node(2);
        head4.left.right = new Node(4);
        head4.right.left = new Node(6);
        head4.right.right = new Node(8);
        head4.left.left.left = new Node(1);
        printTree(head4);
        System.out.println(isBST(head4));
        Node res4 = recoverTree(head4);
        printTree(res4);
        System.out.println(isBST(res4));

        // ���5, 5 -> e1, 2 -> e2
        System.out.println("situation 5");
        Node head5 = new Node(2);
        head5.left = new Node(3);
        head5.right = new Node(7);
        head5.left.left = new Node(5);
        head5.left.right = new Node(4);
        head5.right.left = new Node(6);
        head5.right.right = new Node(8);
        head5.left.left.left = new Node(1);
        printTree(head5);
        System.out.println(isBST(head5));
        Node res5 = recoverTree(head5);
        printTree(res5);
        System.out.println(isBST(res5));

        // ���6, 5 -> e1, 4 -> e2
        System.out.println("situation 6");
        Node head6 = new Node(4);
        head6.left = new Node(3);
        head6.right = new Node(7);
        head6.left.left = new Node(2);
        head6.left.right = new Node(5);
        head6.right.left = new Node(6);
        head6.right.right = new Node(8);
        head6.left.left.left = new Node(1);
        printTree(head6);
        System.out.println(isBST(head6));
        Node res6 = recoverTree(head6);
        printTree(res6);
        System.out.println(isBST(res6));

        // ���7, 4 -> e1, 3 -> e2
        System.out.println("situation 7");
        Node head7 = new Node(5);
        head7.left = new Node(4);
        head7.right = new Node(7);
        head7.left.left = new Node(2);
        head7.left.right = new Node(3);
        head7.right.left = new Node(6);
        head7.right.right = new Node(8);
        head7.left.left.left = new Node(1);
        printTree(head7);
        System.out.println(isBST(head7));
        Node res7 = recoverTree(head7);
        printTree(res7);
        System.out.println(isBST(res7));

        // ���8, 8 -> e1, 7 -> e2
        System.out.println("situation 8");
        Node head8 = new Node(5);
        head8.left = new Node(3);
        head8.right = new Node(8);
        head8.left.left = new Node(2);
        head8.left.right = new Node(4);
        head8.right.left = new Node(6);
        head8.right.right = new Node(7);
        head8.left.left.left = new Node(1);
        printTree(head8);
        System.out.println(isBST(head8));
        Node res8 = recoverTree(head8);
        printTree(res8);
        System.out.println(isBST(res8));

        // ���9, 3 -> e1, 2 -> e2
        System.out.println("situation 9");
        Node head9 = new Node(5);
        head9.left = new Node(2);
        head9.right = new Node(7);
        head9.left.left = new Node(3);
        head9.left.right = new Node(4);
        head9.right.left = new Node(6);
        head9.right.right = new Node(8);
        head9.left.left.left = new Node(1);
        printTree(head9);
        System.out.println(isBST(head9));
        Node res9 = recoverTree(head9);
        printTree(res9);
        System.out.println(isBST(res9));

        // ���10, 7 -> e1, 6 -> e2
        System.out.println("situation 10");
        Node head10 = new Node(5);
        head10.left = new Node(3);
        head10.right = new Node(6);
        head10.left.left = new Node(2);
        head10.left.right = new Node(4);
        head10.right.left = new Node(7);
        head10.right.right = new Node(8);
        head10.left.left.left = new Node(1);
        printTree(head10);
        System.out.println(isBST(head10));
        Node res10 = recoverTree(head10);
        printTree(res10);
        System.out.println(isBST(res10));

        // ���11, 6 -> e1, 2 -> e2
        System.out.println("situation 11");
        Node head11 = new Node(5);
        head11.left = new Node(3);
        head11.right = new Node(7);
        head11.left.left = new Node(6);
        head11.left.right = new Node(4);
        head11.right.left = new Node(2);
        head11.right.right = new Node(8);
        head11.left.left.left = new Node(1);
        printTree(head11);
        System.out.println(isBST(head11));
        Node res11 = recoverTree(head11);
        printTree(res11);
        System.out.println(isBST(res11));

        // ���12, 8 -> e1, 2 -> e2
        System.out.println("situation 12");
        Node head12 = new Node(5);
        head12.left = new Node(3);
        head12.right = new Node(7);
        head12.left.left = new Node(8);
        head12.left.right = new Node(4);
        head12.right.left = new Node(6);
        head12.right.right = new Node(2);
        head12.left.left.left = new Node(1);
        printTree(head12);
        System.out.println(isBST(head12));
        Node res12 = recoverTree(head12);
        printTree(res12);
        System.out.println(isBST(res12));

        // ���13, 6 -> e1, 4 -> e2
        System.out.println("situation 13");
        Node head13 = new Node(5);
        head13.left = new Node(3);
        head13.right = new Node(7);
        head13.left.left = new Node(2);
        head13.left.right = new Node(6);
        head13.right.left = new Node(4);
        head13.right.right = new Node(8);
        head13.left.left.left = new Node(1);
        printTree(head13);
        System.out.println(isBST(head13));
        Node res13 = recoverTree(head13);
        printTree(res13);
        System.out.println(isBST(res13));

        // ���14, 8 -> e1, 4 -> e2
        System.out.println("situation 14");
        Node head14 = new Node(5);
        head14.left = new Node(3);
        head14.right = new Node(7);
        head14.left.left = new Node(2);
        head14.left.right = new Node(8);
        head14.right.left = new Node(6);
        head14.right.right = new Node(4);
        head14.left.left.left = new Node(1);
        printTree(head14);
        System.out.println(isBST(head14));
        Node res14 = recoverTree(head14);
        printTree(res14);
        System.out.println(isBST(res14));

    }

}

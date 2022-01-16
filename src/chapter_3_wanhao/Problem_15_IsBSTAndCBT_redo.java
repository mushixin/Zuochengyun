package chapter_3_wanhao;

import chapter_3_binarytreeproblem.Problem_15_IsBSTAndCBT;
import utils.InputUtils.*;

import java.util.LinkedList;

import static utils.OutputUtils.printTree;

public class Problem_15_IsBSTAndCBT_redo {

    /**
     * 搜索二叉树
     *
     * @param head
     * @return
     */
    public static boolean isBST(Node head) {
        return isBST(head, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean isBST(Node head, int min, int max) {
        if (head == null) {
            return true;
        }
        if (head.value <= min || head.value >= max) {
            return false;
        }
        return isBST(head.left, min, head.value) && isBST(head.right, head.value, max);
    }


    /**
     * 完全二叉树
     * 放到数组中，索引和满二叉树是一样的，则为完全二叉树。(要把不确定的概念确认好，再去做题)
     * 从最后一层开始，后续的所有结点，都不应该有子节点，下一层也不应该有子节点。
     *
     * @param head
     * @return
     */
    public static boolean isCBT(Node head) {
        LinkedList<Node> q1 = new LinkedList<>();
        q1.addLast(head);
        //某个节点后（这个结点最多只有一个左子节点），必须都没有子节点了。某个结点前，都有两个子节点。
        boolean lastHasSub = false;
        while (!q1.isEmpty()) {
            Node n = q1.removeFirst();
            if (lastHasSub) {
                if (n.right != null || n.left != null) {
                    return false;
                }
            } else {
                if (n.right == null || n.left == null) {
                    lastHasSub = true;
                }
            }
            if (n.left != null) {
                q1.addLast(n.left);
            }
            if (n.right != null) {
                q1.addLast(n.right);
            }

        }
        return true;
    }


    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);

        printTree(head);
        System.out.println(isBST(head));
        System.out.println(isCBT(head));
        System.out.println(Problem_15_IsBSTAndCBT.isCBT(head));
        head.right.left.left = new Node(5);
        printTree(head);
        System.out.println(isBST(head));
        System.out.println(isCBT(head));
        System.out.println(Problem_15_IsBSTAndCBT.isCBT(head));


    }
}
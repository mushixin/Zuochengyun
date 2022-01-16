package chapter_3_wanhao;

import chapter_3_binarytreeproblem.Problem_15_IsBSTAndCBT_redo;
import utils.InputUtils.*;

import java.util.LinkedList;

import static utils.OutputUtils.printTree;

public class Problem_15_IsBSTAndCBT_wh {

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
        LinkedList<Node> q2 = new LinkedList<>();
        q1.addLast(head);
        boolean lastLevel = false;//是否是最后一层最后一个结点
        boolean hasSubNode = false;//同一层有子节点。

        while (!q1.isEmpty()) {
            Node n = q1.removeFirst();
            if (n.left != null) {
                q2.addLast(n.left);
                hasSubNode = true;
            }
            if (n.right != null) {
                q2.addLast(n.right);
                hasSubNode = true;
            }
            if (n.left == null || n.right == null) {
                lastLevel = true;
            }
            if (lastLevel && hasSubNode) {
                return false;
            }

            if (q1.isEmpty()) {
                q1 = q2;
                q2 = new LinkedList<>();
                hasSubNode = false;//同一层有子节点。
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
		System.out.println(Problem_15_IsBSTAndCBT_redo.isCBT(head));
		head.right.left.left = new Node(5);
		printTree(head);
		System.out.println(isBST(head));
		System.out.println(isCBT(head));
		System.out.println(Problem_15_IsBSTAndCBT_redo.isCBT(head));


	}
}
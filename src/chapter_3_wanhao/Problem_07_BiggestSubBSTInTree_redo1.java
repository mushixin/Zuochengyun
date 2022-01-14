package chapter_3_wanhao;

import chapter_3_binarytreeproblem.Problem_07_BiggestSubBSTInTree;
import utils.InputUtils;
import utils.OutputUtils;
import utils.InputUtils.Node;


public class Problem_07_BiggestSubBSTInTree_redo1 {


    public static Node getMaxBST(Node head) {
        return process(head).bst;
    }

    /**
     * 某个结点的最大子树信息。
     * 若是左右子树，都是
     */
    public static class ReturnType {
        public int max;
        public int min;
        public int num;
        public Node bst;

        public ReturnType(int max, int min, int num, Node bst) {
            this.max = max;
            this.min = min;
            this.num = num;
            this.bst = bst;
        }
    }

    public static ReturnType process(Node head) {
        if (head == null) {
            return new ReturnType(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, null);
        } else if (head.left == null && head.right == null) {
            return new ReturnType(head.value, head.value, 1, head);
        }
        ReturnType left = process(head.left);
        ReturnType right = process(head.right);
        //left.bst == head.left && right.bst == head.right 这个细节比较重要不然的话，会把不是直接子树的，也会当作子树。
        if ((left.max < head.value || left.max == Integer.MAX_VALUE) &&
                (head.value < right.min || right.min == Integer.MIN_VALUE) && left.bst == head.left && right.bst == head.right) {
            ReturnType result = new ReturnType(right.max != Integer.MAX_VALUE ? right.max : head.value,
                    left.min != Integer.MIN_VALUE ? left.min : head.value, 1 + left.num + right.num, head);
            return result;
        }
        return left.num > right.num ? left : right;
    }

    public static void main(String[] args) {

        Node head = new Node(6);
        head.left = new Node(1);
        head.left.left = new Node(0);
        head.left.right = new Node(3);
        head.right = new Node(12);
        head.right.left = new Node(10);
        head.right.left.left = new Node(4);
        head.right.left.left.left = new Node(2);
        head.right.left.left.right = new Node(5);
        head.right.left.right = new Node(14);
        head.right.left.right.left = new Node(11);
        head.right.left.right.right = new Node(15);
        head.right.right = new Node(13);
        head.right.right.left = new Node(20);
        head.right.right.right = new Node(16);

//        OutputUtils.printTree(head);
        Node bst = getMaxBST(head);
//        OutputUtils.printTree(bst);
//        bst = Problem_07_BiggestSubBSTInTree.getMaxBST(head);
//        OutputUtils.printTree(bst);

        head = InputUtils.generateTree(InputUtils.generateIntArray(1000, false, InputUtils.SortType.increase, InputUtils.RandomRatio.some));
        bst = getMaxBST(head);
        OutputUtils.printTree(bst);
        bst = Problem_07_BiggestSubBSTInTree.getMaxBST(head);
        OutputUtils.printTree(bst);

    }

}

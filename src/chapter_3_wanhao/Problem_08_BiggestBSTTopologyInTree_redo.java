package chapter_3_wanhao;

import chapter_3_binarytreeproblem.Problem_08_BiggestBSTTopologyInTree;
import utils.InputUtils;
import utils.InputUtils.*;

/**
 * 12:05开始
 * 这里还是要dp才可以实现。每个结点的取舍，对于子树的影响都是不同的
 */
public class Problem_08_BiggestBSTTopologyInTree_redo {


    /**
     * 这种解法是存在问题的，因为没有考虑到中间结点为根结点的可能
     *
     * @param head
     * @return
     */
    public static int bstTopoSize1(Node head) {
        if (head == null) {
            return 0;
        }

        /**
         * 分为几种情况
         * 1.左子树的结点小于根节点。
         * 2.右子树的结点大于根结点。
         */
        return bstTopoSize1(head, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static int bstTopoSize1(Node head, int min, int max) {
        if (head == null) {
            return 0;
        }
        int left = bstTopoSize1(head.left, Integer.MIN_VALUE, Integer.MAX_VALUE);
        int right = bstTopoSize1(head.right, Integer.MIN_VALUE, Integer.MAX_VALUE);
        left = Math.abs(left);
        right = Math.abs(right);
        int subMax = left > right ? left : right;

        int val = head.value;
        if (val <= min || val >= max) {
            return -(subMax);
        }
        int result = 1;
        left = bstTopoSize1(head.left, min, val);
        right = bstTopoSize1(head.right, val, max);
        if (left > 0) {
            result = result + left;
        }
        if (right > 0) {
            result = result + right;
        }
        return result > subMax ? result : subMax;
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

        System.out.println(bstTopoSize1(head));
        System.out.println(Problem_08_BiggestBSTTopologyInTree.bstTopoSize1(head));

//        head = InputUtils.generateTree(InputUtils.generateIntArray(1000, false, InputUtils.SortType.increase, InputUtils.RandomRatio.much));
//        System.out.println(bstTopoSize1(head));
//        System.out.println(Problem_08_BiggestBSTTopologyInTree.bstTopoSize1(head));
//

//        Node head2 = new Node(1);
//        head2.left = new Node(17);
//        head2.left.left = new Node(31);
//        head2.left.right = new Node(35);
//
//        head2.right = new Node(19);
//        head2.right.left = new Node(39);
//        head2.right.right = new Node(32);
//        head2.right.right.left = new Node(23);
//        head2.right.right.right = new Node(15);
//        System.out.println(bstTopoSize1(head2));
//        System.out.println(Problem_08_BiggestBSTTopologyInTree.bstTopoSize1(head2));


    }

}

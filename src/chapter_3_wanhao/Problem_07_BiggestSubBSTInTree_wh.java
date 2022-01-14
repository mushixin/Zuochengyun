package chapter_3_wanhao;

import utils.OutputUtils;
import utils.InputUtils.Node;
/**
 * 这里注意是，二叉子树，而不是二叉树的子集。
 * 例如说， 1
 *      2    3
 *     4 5  6 7
 *  二叉子树，只能是 2
 *              4  5
 *   而不能是，2
 *          4
 *   这里要区分下概念。如果说是树的子集（或者说是树的最大拓扑结构），就可以是下面的。
 */
public class Problem_07_BiggestSubBSTInTree_wh {


    public static Node getMaxBST(Node head) {
        ReturnType returnType = process(head, Integer.MIN_VALUE, Integer.MAX_VALUE);

        if (returnType.bstCurrentNum > returnType.bstSubTreeNum) {
            return returnType.bstCurrent;
        }
        return returnType.bstSubTree;
    }


    public static class ReturnType {
        public Node bstCurrent;
        int bstCurrentNum = 0;
        public Node bstSubTree;
        int bstSubTreeNum = 0;
        /**
         * 以当前结点作为子树根节点的情况下的最大值和最小值
         */
        int currentMin = Integer.MIN_VALUE;
        int currentMax = Integer.MAX_VALUE;

        public ReturnType(Node bstCurrent, int bstCurrentNum, int currentMin, int currentMax, Node bstSubTree, int bstSubTreeNum) {
            this.bstCurrent = bstCurrent;
            this.bstCurrentNum = bstCurrentNum;
            this.bstSubTree = bstSubTree;
            this.bstSubTreeNum = bstSubTreeNum;
            this.currentMin = currentMin;
            this.currentMax = currentMax;
        }

        public ReturnType(Node bstCurrent, int bstCurrentNum, Node bstSubTree, int bstSubTreeNum) {
            this.bstCurrent = bstCurrent;
            this.bstCurrentNum = bstCurrentNum;
            this.bstSubTree = bstSubTree;
            this.bstSubTreeNum = bstSubTreeNum;
        }
    }


    /**
     * 返回的值有两个，以X为根结点的最大子树，不以X为根节点的最大子树。
     *
     * @param head
     * @return
     */
    public static ReturnType process(Node head, int minVal, int maxVal) {
        if (head == null) {
            return new ReturnType(null, 0, null, 0);
        } else if (head.left==null&&head.right==null){
            return new ReturnType(head, 1, null, 0);
        }
        //无论如何，也可以求得左子树和右子树的最大树。
        ReturnType leftR = process(head.left, Integer.MIN_VALUE, Integer.MAX_VALUE);
        ReturnType rightR = process(head.right, Integer.MIN_VALUE, Integer.MAX_VALUE);

        //当head 在（minVal，maxVal）范围内的时候,可以求当前结点的最大BST
        //
        if ((head.value <= minVal && minVal != Integer.MIN_VALUE) || (head.value >= maxVal && maxVal != Integer.MAX_VALUE)) {
            return leftR.bstCurrentNum > rightR.bstCurrentNum ? leftR : rightR;
        }

        //这里还是要求，最大的
        ReturnType left = process(head.left, minVal, head.value);
        ReturnType right = process(head.right, head.value, maxVal);
        //左子树是更多节点的
        boolean leftMore = left.bstSubTreeNum > right.bstSubTreeNum;

        int result = left.bstCurrentNum + right.bstCurrentNum + 1;
        ReturnType current = new ReturnType(head, result, left.currentMin, right.currentMax, (leftMore ? left.bstSubTree : right.bstSubTree), leftMore ? left.bstSubTreeNum : right.bstSubTreeNum);
        return current;
    }

    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
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

        OutputUtils.printTree(head);
        Node bst = getMaxBST(head);
        OutputUtils.printTree(bst);

    }

}

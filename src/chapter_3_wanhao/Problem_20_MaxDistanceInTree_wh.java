package chapter_3_wanhao;

import chapter_3_binarytreeproblem.Problem_20_MaxDistanceInTree;
import utils.InputUtils.*;

import static utils.OutputUtils.printTree;


public class Problem_20_MaxDistanceInTree_wh {

    /**
     * 得到一个树最大的举例
     * 左子树，右子树.分别求深度,然后当前结点的最大距离为 left+right+1
     * 哪边深度更大,则去哪边递归下,求最大距离
     *
     * @param head
     * @return
     */
    public static int getMaxDistance(Node head) {
        return getMaxDepth(head).maxDistance;
    }

    public static class ReturnType {
        int depth;
        int maxDistance;

        public ReturnType(int depth, int maxDistance) {
            this.depth = depth;
            this.maxDistance = maxDistance;
        }
    }

    /**
     * 得到一个树的最大深度
     *
     * @param head
     * @return
     */
    public static ReturnType getMaxDepth(Node head) {
        if (head == null) {
            return new ReturnType(0, 0);
        }
        ReturnType left = getMaxDepth(head.left);
        ReturnType right = getMaxDepth(head.right);
        int depth = Math.max(left.depth, right.depth) + 1;
        int maxDistance = Math.max(left.depth + right.depth + 1, Math.max(left.maxDistance, right.maxDistance));
        return new ReturnType(depth, maxDistance);
    }


    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.left = new Node(2);
        head1.right = new Node(3);
        head1.left.left = new Node(4);
        head1.left.right = new Node(5);
        head1.right.left = new Node(6);
        head1.right.right = new Node(7);
        head1.left.left.left = new Node(8);
        head1.right.left.right = new Node(9);
		printTree(head1);
        System.out.println(getMaxDistance(head1));
        System.out.println(Problem_20_MaxDistanceInTree.getMaxDistance(head1));


        Node head2 = new Node(1);
        head2.left = new Node(2);
        head2.right = new Node(3);
        head2.right.left = new Node(4);
        head2.right.right = new Node(5);
        head2.right.left.left = new Node(6);
        head2.right.right.right = new Node(7);
        head2.right.left.left.left = new Node(8);
        head2.right.right.right.right = new Node(9);
		printTree(head2);
		System.out.println(getMaxDistance(head2));
		System.out.println(Problem_20_MaxDistanceInTree.getMaxDistance(head2));

    }

}

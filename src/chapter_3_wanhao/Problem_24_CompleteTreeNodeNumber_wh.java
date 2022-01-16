package chapter_3_wanhao;

import utils.InputUtils.*;

import java.util.LinkedList;


public class Problem_24_CompleteTreeNodeNumber_wh {

    public static int[] two = new int[100];
    public static int[] fullTree = new int[100];//满二叉树,x层,有多少结点

    static {
        two[0] = 1;
        for (int i = 1; i < 100; i++) {
            two[i] = 2 * two[i - 1];
        }
        fullTree[0] = 1;
        for (int i = 1; i < 100; i++) {
            fullTree[i] = fullTree[i - 1] + two[i];
        }
    }


    /**
     * 返回完全二叉树的数量
     *
     * @param head
     * @return
     */
    public static int nodeNum(Node head) {
        int leftHeight = getLeftHeight(head);
        int rightHeight = getRightHeight(head);
        if (leftHeight == rightHeight) {
            //满二叉树
            return fullTree[leftHeight-1];
        }

        /**
         * 二分查找,从中间结点,找到二叉树有无数据的临界点
         */
        int maxNum = fullTree[leftHeight-1] - 1;
        int minNum = fullTree[rightHeight-1] + 1;
        while (maxNum >= minNum) {
            int mid = (maxNum + minNum) / 2;
            //true为left, false为right
            LinkedList<Boolean> path = new LinkedList<>();
            while (mid > 1) {
                if ((mid % 2 == 0)) {
                    path.addFirst(true);
                } else {
                    path.addFirst(false);
                }
                mid = mid / 2;
            }
            Node tH = head;
            while (!path.isEmpty() && tH != null) {
                //是否往左走
                boolean p = path.removeFirst();
                if (p) {
                    tH = tH.left;
                } else {
                    tH = tH.right;
                }
            }
            //左边部分
            if (tH == null) {
                maxNum = (maxNum + minNum) / 2 - 1;
            } else {//右边部分
                minNum = (maxNum + minNum) / 2 + 1;
            }
        }
        return (maxNum + minNum) / 2;
    }


    //Node head
    public static int getLeftHeight(Node head) {
        if (head == null) {
            return 0;
        }
        return getLeftHeight(head.left) + 1;
    }

    //右边高度
    public static int getRightHeight(Node head) {
        if (head == null) {
            return 0;
        }
        return getRightHeight(head.right) + 1;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        System.out.println(nodeNum(head));//6

		head.right.right = new Node(7);
		System.out.println(nodeNum(head));//7

		head.left.left.left = new Node(8);
		System.out.println(nodeNum(head));//8

	}

}

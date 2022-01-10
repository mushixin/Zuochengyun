package chapter_3_wanhao;

import chapter_3_binarytreeproblem.Problem_08_BiggestBSTTopologyInTree;

/**
 * 12:05开始
 */
public class Problem_08_BiggestBSTTopologyInTree_wh {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

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

	public static int  bstTopoSize1(Node head,int min,int max) {
		if (head == null) {
			return 0;
		}
		int val = head.value;
		if (val <= min || val >= max) {
			return 0;
		}
		int result =  1 +  bstTopoSize1(head.left,min,val);
		result = result + bstTopoSize1(head.right,val,max);
		return result;
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
//		printTree(head);

		System.out.println(bstTopoSize1(head));
		System.out.println(Problem_08_BiggestBSTTopologyInTree.bstTopoSize1(head));
//		System.out.println(bstTopoSize2(head));

	}

}

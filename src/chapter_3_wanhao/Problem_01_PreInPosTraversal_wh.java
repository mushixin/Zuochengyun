package chapter_3_wanhao;

import utils.InputUtils.Node;
import utils.OutputUtils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Problem_01_PreInPosTraversal_wh {

	public static void preOrderRecur(Node head) {
		if (head==null){
			return;
		}
		System.out.print(head.value + " ");
		preOrderRecur(head.left);
		preOrderRecur(head.right);
	}

	public static void inOrderRecur(Node head) {
		if (head==null){
			return;
		}
		inOrderRecur(head.left);
		System.out.print(head.value + " ");
		inOrderRecur(head.right);
	}

	public static void posOrderRecur(Node head) {
		if (head==null){
			return;
		}
		posOrderRecur(head.left);
		posOrderRecur(head.right);
		System.out.print(head.value + " ");
	}

	public static void preOrderUnRecur(Node head) {
		System.out.print("pre-order: ");
		LinkedList<Node> list = new LinkedList<>();
		list.addFirst(head);
		while (!list.isEmpty()) {
			Node temp = list.removeFirst();
			System.out.print(temp.value + " ");
			if (temp.right!=null){
				list.addFirst(temp.right);
			}
			if (temp.left!=null){
				list.addFirst(temp.left);
			}
		}
		System.out.println();
	}

	/**
	 * 18:15~
	 * 打断，然后左右子树放到列表中
	 * @param head
	 */
	public static void inOrderUnRecur(Node head) {
		System.out.print("in-order: ");
		LinkedList<Node> list = new LinkedList<>();
		list.addFirst(head);
		while (!list.isEmpty()) {
			Node fir = list.removeFirst();
			if (fir.left==null && fir.right==null){
				System.out.print(fir.value + " ");
				continue;
			}
			Node right = fir.right;
			Node left = fir.left;
			fir.right = null;
			fir.left = null;

			if (right != null) {
				list.addFirst(right);
			}
			list.addFirst(fir);
			if (left != null) {
				list.addFirst(left);
			}

		}
		System.out.println();
	}

	/**
	 * 遍历的时候不可以打断
	 * @param head
	 */
	public static void inOrderUnRecur2(Node head) {
		System.out.print("in-order: ");

		Set<Node>traced = new HashSet<>();
		LinkedList<Node> list = new LinkedList<>();
		list.addFirst(head);
		while (!list.isEmpty()) {
			Node fir = list.removeFirst();
			if (fir.left==null && fir.right==null){
				System.out.print(fir.value + " ");
				continue;
			}
			if (traced.contains(fir)) {
				System.out.print(fir.value + " ");
				continue;
			}
			Node right = fir.right;
			Node left = fir.left;

			if (right != null) {
				list.addFirst(right);
			}
			list.addFirst(fir);
			if (left != null) {
				list.addFirst(left);
			}
			traced.add(fir);
		}

		System.out.println();
	}

	/**
	 * 10min
	 * @param head
	 */
	public static void posOrderUnRecur1(Node head) {
		System.out.print("pos-order: ");

		LinkedList<Node> list = new LinkedList<>();
		list.addFirst(head);
		while (!list.isEmpty()) {
			Node fir = list.removeFirst();
			if (fir.left==null && fir.right==null){
				System.out.print(fir.value + " ");
				continue;
			}
			Node right = fir.right;
			Node left = fir.left;
			fir.right = null;
			fir.left = null;

			list.addFirst(fir);
			if (right != null) {
				list.addFirst(right);
			}
			if (left != null) {
				list.addFirst(left);
			}

		}

		System.out.println();
	}

	public static void main(String[] args) {
		Node head = new Node(5);
		head.left = new Node(3);
		head.right = new Node(8);
		head.left.left = new Node(2);
		head.left.right = new Node(4);
		head.left.left.left = new Node(1);
		head.right.left = new Node(7);
		head.right.left.left = new Node(6);
		head.right.right = new Node(10);
		head.right.right.left = new Node(9);
		head.right.right.right = new Node(11);

		OutputUtils.printTree(head);
		// recursive
		System.out.println("==============recursive==============");
		System.out.print("pre-order: ");
		preOrderRecur(head);
		System.out.println();
		System.out.print("in-order: ");
		inOrderRecur(head);
		System.out.println();
		System.out.print("pos-order: ");
		posOrderRecur(head);
		System.out.println();

		// unrecursive
		System.out.println("============unrecursive=============");
		preOrderUnRecur(head);
//		inOrderUnRecur(InputUtils.cloneNewTreeNode(head));
		inOrderUnRecur2(head);
		posOrderUnRecur1(head);

	}

}
